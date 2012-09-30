package com.github.sillyblog

import org.grails.taggable.Tag
import org.grails.taggable.TagLink

class BlogEntryTagsService{

	/**
	 * This method retrieves all tags starting by the given term
	 * passed as parameter
	**/
	def findAllTagsByNameLike(name){
		Tag.findAllByNameIlike("%${name}%")
	}

 	/**
	 * This method returns a map with the frequency of the terms used in the blog entries
	**/
	def getTagFrequency(){
		def resultMap = TagLink.withCriteria{
			projections{
				tag{
					groupProperty "name"
				}
				countDistinct "tagRef"
			}
		}.collectEntries{ [(it[0].toString()):it[1]] }

		resultMap
	}
}
