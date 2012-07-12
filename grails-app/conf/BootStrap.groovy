import com.github.sillyblog.*

class BootStrap {

    def init = { servletContext ->
		createSomeSampleEntries()
    }

    def destroy = {
    }


	def createSomeSampleEntries(){
		[
			new BlogEntry(entryTitle:'First entry',entryText:'Is my first entry',entryDate:new Date()),
			new BlogEntry(entryTitle:'Second entry',entryText:'Is my first entry',entryDate:new Date()),
			new BlogEntry(entryTitle:'Third entry',entryText:'Is my first entry',entryDate:new Date()),
			new BlogEntry(entryTitle:'Fourth entry',entryText:'Is my first entry',entryDate:new Date()),
			new BlogEntry(entryTitle:'Third entry',entryText:'Is my first entry',entryDate:new Date()),
			new BlogEntry(entryTitle:'Fifth entry',entryText:'Is my first entry',entryDate:new Date())

		]*.save()


	}
}
