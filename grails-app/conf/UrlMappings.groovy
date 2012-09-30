class UrlMappings {

	static mappings = {
	 /* DEFAULT MAPPINGS */
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}
	
	  /* BLOG ENTRIES URLS */	
		"/entry-list"(controller:"blogEntry",action:"index")
		"/show/$id/$entryTitle"(controller:"blogEntry",action:"show")
		"/new"(controller:"blogEntry",action:"createEntry")
		"/edit/$id/$entryTitle"(controller:"blogEntry",action:"editEntry")
		"/save"(controller:"blogEntry",action:"saveEntry")

	 /* INDEX PAGINATION */	
		"/index/$offset/$max"(controller:"blogEntry",action="index")
	 /* HOME VIEW */
		"/"(controller:"blogEntry",action:"index")
		//"/"(view:"/index")
		"500"(view:'/error')
	}
}
