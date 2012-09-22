package com.github.sillyblog

import spock.lang.Specification

import org.grails.taggable.Tag

import grails.test.mixin.*
import grails.test.mixin.support.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 * 
 * @author marioggar
 *
**/
@Mock([BlogEntry,Tag])
@TestFor(BlogEntryController)
class BlogEntryControllerSpec extends Specification{

	def setup(){
	 /* I always want to have an available saved instance */
		new BlogEntry(entryTitle:'t',entryText:'t',entryDate:new Date()).save()
	 /* Taggable dynamic behavior doesn't seem to work in unit testing 
		so it has to be faked */
		BlogEntry.metaClass.parseTags = {s-> s}
	 /* Adding the service to the controller */
		controller.blogEntryService = new BlogEntryService()
	}

	def "Testing index action"(){
		when: "The controller instance is invoked"
			controller.index()	
		then:"It should find one instance to show"	
			view == '/blogEntry/index'
			model.entries.size() == 1	
			model.entries.find{it}.entryDate
			model.entries.find{it}.entryTitle == 't'
			model.entries.find{it}.entryText == 't'
	}

	def "Testing show action of an existent entry"(){
		given: "The id of the previosly saved instance"
			def id = BlogEntry.list().find{it}.id
		when: "Trying to see that entry"
			controller.params.id = id
			controller.show()
		then: "It should show up"
			view == '/blogEntry/show'
			model.entry.entryTitle == 't'
			model.entry.entryText == 't'
			model.entry.entryDate	
	}

	def "Testing show action of a non existent entry"(){
		when: "Trying to retrieve an invalid entry"
			controller.params.id = 999
			controller.show()
		then: "Although the view is the same there's no entry data"
			view == '/blogEntry/show'
			flash.message == 'blog.entry.error.nonValidEntry'
			flash.type == 'error'
			!model.entry
	}

	def "trying to create a new entry"(){
		when: "Clicking on create"
			controller.createEntry()
		then: "We should go to the right view"
			view == '/blogEntry/create'
	}

	def "Testing saving a new blog entry"(){
		given: "Some form params"
			controller.params.entryTitle = 't'
			controller.params.entryText = 't'
		 /* To pass the date value we need to 
			fill three fields */
			controller.params.entryDate_day = '01' 
			controller.params.entryDate_month = '02' 
			controller.params.entryDate_year = '2012' 
		 /* No tags here */
			controller.params.tags = ""
		when: "Trying to save the entry"
			controller.saveEntry()
		then: "A new entry should be available"
			BlogEntry.list().size() == 2
			flash.message == 'blog.entry.create.success'
			response.redirectedUrl == '/blogEntry/index'
	}

	def "Trying to save a non valid blog entry"(){
		given: "Not enough parameters"
			controller.params.entryTitle = 't'
			controller.params.entryText = 'text'
		when: "Trying to save the entry"
			controller.saveEntry()
		then: "Something goes wrong"
			view == '/blogEntry/create'
		 /* Because of entryDate */
			model.entry.errors.errorCount == 1
			model.entry.entryTitle == 't'
			flash.message == 'blog.entry.create.error.validation'
			flash.type == 'error'
	}

}
