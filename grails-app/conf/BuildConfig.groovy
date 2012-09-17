grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    checksums true // Whether to verify checksums on resolve

    repositories {
        inherits true // Whether to inherit repository definitions from plugins
        grailsPlugins()
        grailsHome()
        grailsCentral()
        mavenCentral()

        // uncomment these to enable remote dependency resolution from public Maven repositories
        //mavenCentral()
        //mavenLocal()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
        // runtime 'mysql:mysql-connector-java:5.1.16'

    }

    plugins {
	 /* Plugins used during compilation/runtime */
        runtime ":hibernate:$grailsVersion"
        runtime ":jquery:1.7.1"
        runtime ":resources:1.1.6"

		compile ":coffeescript-resources:0.3.2"
		compile ":taggable:1.0.1"
		compile ":jquery-ui:1.8.15"
		compile ":spring-security-core:1.2.7.3"

        // Uncomment these (or add new ones) to enable additional resources capabilities
        //runtime ":zipped-resources:1.0"
        //runtime ":cached-resources:1.0"
        //runtime ":yui-minify-resources:0.1.4"

	 /* Testing plugins */
		test ":spock:0.6"
		compile ":jasmine-resources:0.1.1"
 		compile ":codenarc:0.17" 
	 /* Others */
        build ":tomcat:$grailsVersion"
    }
}

 /* Using Codenarc for code checking purposes */
	codenarc.properties = {
     // Each property definition is of the form:  RULE.PROPERTY-NAME = PROPERTY-VALUE
  	 /* Since Grails 2.0.1 public methods are preffered insteadof closures */
		GrailsPublicControllerMethod.enabled = false
      //EmptyIfStatement.priority = 1
	}

