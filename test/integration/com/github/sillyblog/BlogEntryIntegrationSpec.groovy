package com.github.sillyblog

import grails.plugin.spock.IntegrationSpec

/**
 * Testing Taggable plugin behavior over our BlogEntry instances
 * 
 * @author marioggar
 * 
**/
class BlogEntryIntegrationSpec extends IntegrationSpec {

	def "Testing taggable behavior: Adding tags"(){
		given: "A BlogEntry valid instance"
			def entry = new BlogEntry(entryTitle: 'Title',entryText:'Text',entryDate:new Date())
		when: "Adding some tags to the entry before saving"
			def result = entry.
		 	 /* Remember that before adding or removing tags instance should be saved first */
				save().
					addTag("groovy").
					addTag("grails").
					addTag("gradle")
		then: "Retrieving the already saved tags"
			BlogEntry.get(result.id).tags == ['groovy','grails','gradle']
			BlogEntry.findAllByTag('groovy').size() == 1
			BlogEntry.findAllByTag('grails').size() == 1
			BlogEntry.findAllByTag('gradle').size() == 1
	}
	
	def "Testing taggable behavior: Deleting tags"(){
		given: "A BlogEntry valid instance"
			def entry = new BlogEntry(entryTitle: 'Title',entryText:'Text',entryDate:new Date()).save().
				addTag("groovy").
				addTag("grails").
				addTag("gradle")
		when: "Deleting some tag"
		 /* Here you could also use entry.setTags(['groovy'])*/
			entry.removeTag("groovy")
		then: "There should be less tags available"
		 /* How many blog entries are tagged with 'grails' */
			BlogEntry.countByTag("grails") == 1
		 /* Given all tags from a given entry */
			BlogEntry.get(entry.id).tags == ['grails','gradle']
		 /* Find all tags starting with gr% (This type of method call needs both parameters) */
			BlogEntry.findAllTagsWithCriteria([max:5]){ilike('name','gr%')}.size() == 2
	}

}
