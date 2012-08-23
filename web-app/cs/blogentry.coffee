
BlogEntry = exports? and exports or @BlogEntry = {}

class BlogEntry.Instance

 ### Splits terms using the comma character ###
	split: (val) ->
  		val.split(/,\s*/)

 ### Extracts the last term of a given list of termns found after using split ###
	extractLast: (term) ->
  		@split(term).pop()

