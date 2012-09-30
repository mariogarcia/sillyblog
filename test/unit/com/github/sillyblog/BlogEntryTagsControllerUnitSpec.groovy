package com.github.sillyblog

import grails.converters.JSON
import grails.test.mixin.*
import grails.test.mixin.support.*

import spock.lang.Specification
import org.grails.taggable.*

/**
 * This class tests the BlogEntryTagsController. WARNING! in order to make
 * JSON work while parsing Tag instances, you should mock the Tag class.
 * 
 * @author marioggar
**/
@Mock([BlogEntry,Tag])
@TestFor(BlogEntryTagsController)
class BlogEntryTagsControllerUnitSpec extends Specification{

	def "Listing tags from controller"(){
		setup: "As a unit test the service is mocked"
		 /* Mocking the service */
			def service = [
				findAllTagsByNameLike : {name->
					[new Tag(name:'Javaz')]
				}
			] as BlogEntryTagsService 	
		when: "Trying to get a tag list by name"
		 /* Setting and executing an action from the controller */
			controller.blogEntryTagsService = service
			controller.findAllTagsByNameLike("Ja")
		 /* Parsing the response to traverse it properly */
			def response = controller.response.contentAsString
			def json = JSON.parse(response)
		then: "A list with at least one item should be provided in JSON format"
			json.size() == 1
		 /* By default taggable plugin treat all tags as lowercase */
			json[0].name == 'javaz'
	}

	def "Getting blog entries tag frequency"(){
		setup: "Mocking the service"
			def serviceMock = mockFor(BlogEntryTagsService)
		 /* The service should return a map with the frequencies */
			serviceMock.demand.getTagFrequency(1..1){->
				["java":2,"groovy":1]
			}
			def service = serviceMock.createMock()
		when: "Getting the frequency as JSON"
			controller.blogEntryTagsService = service
			controller.getTagFrequency()
	     /* Parsing the response to check the frequencies */	
			def response = controller.response.contentAsString
			def json = JSON.parse(response)
		then: "It should have the right frequencies"
			json.java == 2 
			json.groovy == 1
	}

}
