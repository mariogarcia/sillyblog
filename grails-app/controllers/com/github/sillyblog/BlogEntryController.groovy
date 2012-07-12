package com.github.sillyblog

/**
 * This controller has all actions over BlogEntry instances
 * 
 * @author marioggar
**/
class BlogEntryController {

   /**
	* Shows all available blog entries
	**/
    def index() {
	 /* Using render() in order to be able to assert views in unit testing */
		render view:'index',model:[entries:BlogEntry.list()]	
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
}
