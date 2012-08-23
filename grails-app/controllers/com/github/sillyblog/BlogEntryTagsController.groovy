package com.github.sillyblog

import grails.converters.JSON

/**
 * This class permits saving, deleting and listing tags
*/
class BlogEntryTagsController{

	def blogEntryTagsService

	/**
	 * Returns all tags starting with the given name passed as parameter. Response is processed
	 * by javascript function, so it should be rendered as JSON data
	 **/
	def findAllTagsByNameLike(String name){
		def result = blogEntryTagsService.findAllTagsByNameLike(name)

		render result as JSON
	}
}
