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
		render(
			view:'index',
			model:[
			 /* We are using the paginate tag but we want also to sosrt by entryDate */
				entries:BlogEntry.list(
					offset:params.offset,
					max:params.max,
					sort:'entryDate',
					order:'desc'),
				entriesTotal:BlogEntry.count()
			]	
		)
	}

   /**
 	*  Shows a given blog entry
	**/
	def show(){
		def entry2Show = BlogEntry.get(params.id)
		if (!entry2Show){
		 /* Sometimes is important to tell the type of the flash message */
			addFlashErrorMessage("blog.entry.error.nonValidEntry")
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
		def entry = params.id ? BlogEntry.get(params.id) : new BlogEntry()
	 /* It doesn't matter whether it's a new one or an existing one. We always
		replace the existing properties with the ones from the form excluding the
		tags.  */
		bindData(entry,params,[exclude:['tags']])
	 /* Then setting the entry tags */
		def entryTags = params.tags	
	 /* Trying to save the entry with its tags */
		entry = blogEntryService.saveBlogEntryWithTags(entry,entryTags)
	 /* In case the entry had errors it should show them */
		if (entry.hasErrors()){
			addFlashErrorMessage("blog.entry.create.error.validation")
			render view:'create',model:[entry:entry,entryTags:entryTags]
	 /* Otherwise we will redirected to the entry list */
		} else {
			flash.message = 'blog.entry.create.success'
			redirect action:'index'
		}
	}

   /**
	* This action edits the blog entries
	**/
	@Secured(["ROLE_ADMIN","ROLE_USER"])
	def editEntry(){
		def entry = BlogEntry.get(params.id)
	 /* Only redirect if the entry exists */
		if (entry){
		 /* As long as we are using the same view as createEntry we should 
			wrap the tags in the entryTags object */
			def entryTags = entry.tags
		 /* The we show the view create with the created model */
			render view:'edit',model:[entry:entry,entryTags:entryTags]
		} else {
			addFlashErrorMessage("blog.entry.edit.error")
		 /* One we´ve added the error in the flash scope the redirect */
			redirect action:'index'
		}
	}

 /* Shortcut for adding flash error messages */
	def addFlashErrorMessage(message){
		flash.type = "error"
		flash.message = message	
	}

}
