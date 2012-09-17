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
}
