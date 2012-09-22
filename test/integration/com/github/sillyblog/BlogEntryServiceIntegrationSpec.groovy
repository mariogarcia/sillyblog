package com.github.sillyblog

import grails.plugin.spock.IntegrationSpec

class BlogEntryServiceIntegrationSpec extends IntegrationSpec{

	def blogEntryService

	def "Trying to save a blogEntry with a given set of tags"(){
		given: "A given blog entry with tags"
			def entry = new BlogEntry(entryTitle:'title',entryText:'text',entryDate:new Date())
			def tags = "java,scala,groovy"
		when: "You try to save it"
			def result = blogEntryService.saveBlogEntryWithTags(entry,tags)
		then: "The entry has been saved"
			result
			result.hasErrors() == false
			result.tags == ['java','scala','groovy']
	}
}
