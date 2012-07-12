package com.github.sillyblog

import spock.lang.Specification

import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 * 
 * @author marioggar
**/
@TestFor(BlogEntry)
class BlogEntryUnitSpec extends Specification{

	def "Saving a valid BlogEntry"(){
		given: "A valid BlogEntry instance"
			def date = new Date()
			def entry = new BlogEntry(
				entryTitle: "First entry",
				entryText: "short text",
				entryDate: date 
			)
		when: "Trying to save it"
			entry = entry.save()
		then: "It should be still available and values should match"
			entry
			entry.entryTitle== 'First entry'
			entry.entryText== 'short text'
			entry.entryDate == date
	}

	def "Trying to save a non valid BlogEntry"(){
		given: "A BlogEntry instance without date"
			def result
			def entry = new BlogEntry()
		when: "Trying to save it"
			result = entry.save()
		then: "It should be still available"
		 /* Can't be saved so result is null */
			!result	
		 /* The entry instance now has errors */
			entry.hasErrors()
		 /* It fails because I haven't filled three fields */
			entry.errors.errorCount == 3
	}
}
