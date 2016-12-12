# spring-mvc-cloud-config
This Project demonstrates a means to enable using spring-cloud config into a class spring-mvc project. 
(Note this is not intended for production use as it bootstraps the configuration as simply as possible, and does not include other Spring Cloud Config functionality like refresh.  You should ensure your implementation follows the preferred configuration initialization of your organization.)  

This simple app provides an ApplicationContextInitializer that when enabled in web.xml adds properties from Spring Cloud Config server into the property sources at startup time.


In order for this to work you must set the following properties somewhere in the property sources. (Such as the environment variables)

spring.cloud.config.name=\<\<name of application from spring-cloud-config to use to resolve properties\>\>
spring.cloud.config.uri=\<\<address of spring cloud config server to use\>\>

You also must have a Spring cloud config server running.  To get one import this spring-boot project and run it locally (your spring cloud config uri will be http://localhost:8888). It will serve properties from this github repo https://github.com/aripka-pivotal/config-repo.  This contains details for an app named "omaha" that has a property named hello.configMessage as configured in the Controller. 
