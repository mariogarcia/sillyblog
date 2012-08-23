import com.github.sillyblog.*
 
class BootStrap {

    def init = { servletContext ->
		createSomeSampleEntries()
    }

    def destroy = { }

	/**
	 * This method saves five BlogEntry instances to have some 
	 * example blog entries
	**/
	def createSomeSampleEntries(){
		["First","Second","Third","Fourth","Fifth"].collect{
			new BlogEntry(
				entryTitle: "${it} entry",
				entryText: "Is my ${it.toLowerCase()} entry",
				entryDate: new Date()
			)
		}*.save()
	}
}
