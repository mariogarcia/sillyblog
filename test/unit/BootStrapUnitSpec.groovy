
import com.github.sillyblog.BlogEntry
import com.github.sillyblog.security.*

import grails.test.mixin.*
import grails.test.mixin.support.*

import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges

/**
 * This class tests the functionality of the BootStrap class.
 * 
 * @author marioggar
**/
@Mock([User,UserRole,Role,BlogEntry])
class BootStrapUnitSpec extends Specification{

	def "Test initial blog entries"(){
		when: "The BootStrap is initiated" 
			def bootStrapInstance = new BootStrap()
		and: "the createSomeSampleEntries is called"
			bootStrapInstance.createSomeSampleEntries()
		then: "The entries could be retrieved from the database"
			BlogEntry.count() == 5
	}

   /**
	* I'm using @ConfineMetaClassChanges because I'm modifying the metaClass
	* of all instances of User. I want to be sure further uses of User won't
	* take these changes under consideration
	**/
	@ConfineMetaClassChanges(User)
	def "Testing when there's no initial admin user"(){
		setup: "User instance should have springSecurityService property injected"
			User.metaClass.encodePassword= {"lala"}
		when: "BootStrap is initiated"
			def bootStrapInstance = new BootStrap()
		and: "Checking if the admin user has been created"
			bootStrapInstance.createAdminUser()
		then: "whether it has been created or not it should be available"
			User.findByUsername("admin")
	}

   /**
	* I'm using @ConfineMetaClassChanges because I'm modifying the metaClass
	* of all instances of User. I want to be sure further uses of User won't
	* take these changes under consideration
	**/
	@ConfineMetaClassChanges(User)
	def "Testing init method consequences"(){
		setup: "User instance should have springSecurityService property injected"
			User.metaClass.encodePassword= {"lala"}
		when: "BootStrap is initiated"
			def bootStrapInstance = new BootStrap()
		and: "Checking if the admin user has been created"
			bootStrapInstance.init()
		then: "whether it has been created or not it should be available"
			User.findByUsername("admin")
			BlogEntry.count() == 5
	}
}
