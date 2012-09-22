package com.github.sillyblog

import grails.plugins.springsecurity.Secured
import org.grails.taggable.Tag

/**
 * This controller has all actions over BlogEntry instances
 * 
 * @author marioggar
**/
class BlogEntryController {

	def blogEntryService

   /**
	* Shows all available blog entries
	**/
    def index() {
	 /* Using render() in order to be able to assert views in unit testing */
		render view:'index',model:[entries:BlogEntry.list(sort:"entryDate",order:"desc")]	
	}

   /**
 	*  Shows a given blog entry
	**/
	def show(){
		def entry2Show = BlogEntry.get(params.id)
		if (!entry2Show){
		 /* Sometimes is important to tell the type of the flash message */
			flash.message = "blog.entry.error.nonValidEntry"	
			flash.type = "error"
		}
		render view:'show',model:[entry:entry2Show]
	}

	/**
	 * This  action just show the creation form.
	**/
	@Secured(["ROLE_ADMIN","ROLE_USER"])
	def createEntry(){
		render view:'create'
	}

	/**
	 * It saves blog entries instances
	**/
	@Secured(["ROLE_ADMIN","ROLE_USER"])
	def saveEntry(){
		def entry = new BlogEntry(params)
		def entryTags = params.tags	
	 /* Trying to save the entry with its tags */
		entry = blogEntryService.saveBlogEntryWithTags(entry,entryTags)
	 /* In case the entry had errors it should show them */
		if (entry.hasErrors()){
			flash.message = 'blog.entry.create.error.validation'
			flash.type = 'error'
			render view:'create',model:[entry:entry,entryTags:entryTags]
	 /* Otherwise we will redirected to the entry list */
		} else {
			flash.message = 'blog.entry.create.success'
			redirect action:'index'
		}
	}
}
