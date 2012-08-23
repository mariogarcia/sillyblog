package com.github.sillyblog

class BlogEntryService{

   /**
	* This method tries to save a given blogEntry instace and
	* its related tags in the same transaction
	**/
	def saveBlogEntryWithTags(blogEntry,tags){
		if (blogEntry.validate()){
			blogEntry = blogEntry.save()
			if (tags){
				blogEntry?.parseTags(tags)
			}
		}
		blogEntry
	}
}
