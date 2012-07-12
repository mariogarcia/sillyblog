package com.github.sillyblog

import spock.lang.Specification

import grails.test.mixin.*
import grails.test.mixin.support.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 * 
 * @author marioggar
 *
**/
@Mock(BlogEntry)
@TestFor(BlogEntryController)
class BlogEntryControllerSpec extends Specification{

	def setup(){
	 /* I always want to have an available saved instance */
		new BlogEntry(entryTitle:'t',entryText:'t',entryDate:new Date()).save()
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
}
