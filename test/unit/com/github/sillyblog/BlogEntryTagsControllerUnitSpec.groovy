package com.github.sillyblog

import org.codehaus.groovy.grails.web.converters.marshaller.json.GroovyBeanMarshaller

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

}
