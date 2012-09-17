package com.github.sillyblog

import groovy.transform.ToString
import groovy.transform.EqualsAndHashCode
import org.grails.taggable.Taggable

/**
 * This class represents every blog entry. Implements Taggable
 * in order to enable entries to be sorted by categories (sort of)
 * 
 * @author marioggar
 * 
**/
@ToString(includeNames=true,includes="entryTitle,entryDate")
@EqualsAndHashCode(includeFields=true,includes="id")
class BlogEntry implements Taggable{

	Long id
	String entryTitle,entryText
	Date entryDate

    static constraints = {
		entryTitle nullable:false,blank:false
		entryText nullable:false,blank:false
		entryDate nullable:true
    }
}
