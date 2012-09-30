import com.github.sillyblog.*
import com.github.sillyblog.security.*
import org.apache.commons.lang.StringUtils
 
class BootStrap {

    def init = { servletContext ->
		createAdminUser()
		createSomeSampleEntries()
		createSomeStringUtils()
    }

    def destroy = { }

 /* This method creates an admin user in case it hasn't been created before */
	def createAdminUser(){
		def admin = User.findByUsername("admin")
	 /* Only if there's no admin user... */
		if (!admin){
		 /* If there's no admin user then we create it */
			admin = new User(username:"admin",password:"admin",enabled:"true").save()
		 /* We need to establish the ROLE_USER and ROLE_ADMIN roles */
			def neededRoles = ['ROLE_USER','ROLE_ADMIN'].collect{
			 /* Roles have to be saved because UserRole has no cascade policy */
				new UserRole(user:admin,role:new Role(authority:it).save())
			}*.save() // Saving UserRole instances
		 /* If user can't be created in the end then it throws an exception */
			if (neededRoles.any{it.hasErrors()}){
				throw new Exception("Can't create admin user")
			}
		}
	}

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

   /**
	* Adding the intro method to all String and GString instances.
	* Specially handy for views
	**/
	def createSomeStringUtils(){ 
		String.metaClass.intro = {len ->
  			return StringUtils.abbreviate(delegate, len) ?: ''
		}
		GString.metaClass.intro = {len ->
  			return StringUtils.abbreviate(delegate.toString(), len)
		}
	}
}
