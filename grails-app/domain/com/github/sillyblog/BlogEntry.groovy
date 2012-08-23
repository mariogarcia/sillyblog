package com.github.sillyblog

import org.grails.taggable.Taggable

/**
 * This class represents every blog entry. Implements Taggable
 * in order to enable entries to be sorted by categories (sort of)
 * 
 * @author marioggar
 * 
**/
class BlogEntry implements Taggable{

	String entryTitle,entryText
	Date entryDate

    static constraints = {
		entryTitle nullable:false,blank:false
		entryText nullable:false,blank:false
		entryDate nullable:true
    }
}
