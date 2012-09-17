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
		"/entry/$id/$entryTitle"(controller:"blogEntry",action:"show")
		"/entry-new"(controller:"blogEntry",action:"createEntry")
		"/entry-save"(controller:"blogEntry",action:"saveEntry")
			
	 /* HOME VIEW */
		"/"(controller:"blogEntry",action:"index")
		//"/"(view:"/index")
		"500"(view:'/error')
	}
}
