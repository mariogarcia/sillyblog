package com.github.sillyblog

/**
 * This class represents every blog entry
 * 
 * @author marioggar
 * 
**/
class BlogEntry {

	String entryTitle,entryText
	Date entryDate

    static constraints = {
		entryTitle nullable:false,blank:false
		entryText nullable:false,blank:false
		entryDate nullable:false
    }
}
