package com.github.sillyblog

import org.grails.taggable.Tag

import grails.plugin.spock.IntegrationSpec
import grails.test.mixin.*
import grails.test.mixin.support.*

/**
 * Testing the BlogEntryTagsService class
 * 
 * @author marioggar
**/
class BlogEntryTagsServiceIntegrationSpec extends IntegrationSpec{

	def blogEntryTagsService

	def "Listing an already saved tag"(){
		setup: "Saving a tag"
	 	/* I always want to have an available saved instance */
			def blogEntry = 
				new BlogEntry(
					entryTitle:'t',
					entryText:'t',
					entryDate:new Date()
				).
			save().
		 /* You can only add a tag in an already saved entry */
			addTag("John")
			assert blogEntry
		when: "Trying to retrieve all tags starting with Joh"
			def result = blogEntryTagsService.findAllTagsByNameLike("Joh")
		then: "There's only one entry with one tag"
			BlogEntry.findByEntryTitle("t")
			result.size() == 1
			result.find().name == 'john'
	}

	def "Getting the frequency of the saved tags"(){
		setup: "Saving a blog entry with a few tags"
			new BlogEntry(
				entryTitle:'t',
				entryText:'t',
				entryDate:new Date()
			).save().
		 /* You can only add a tag in an already saved entry */
			addTag("Java").
			addTag("Ruby").
			addTag("Groovy")
		expect: "To get the right frequency for the given language"
			blogEntryTagsService.getTagFrequency().getAt(language) == frequency
		where: "The parameters are"
			language	|	frequency
			"java"		|	1
			"groovy"	|	1
			"ruby"		|	1	
	}

}
