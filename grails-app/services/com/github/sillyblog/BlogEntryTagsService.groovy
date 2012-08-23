package com.github.sillyblog

import org.grails.taggable.Tag

class BlogEntryTagsService{

	/**
	 * This method retrieves all tags starting by the given term
	 * passed as parameter
	**/
	def findAllTagsByNameLike(name){
		Tag.findAllByNameIlike("%${name}%")
	}
}
