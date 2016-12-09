package io.pivotal.sample.cloud.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.config.client.ConfigClientProperties;
import org.springframework.cloud.config.client.ConfigServicePropertySourceLocator;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.util.Assert;
import org.springframework.web.context.support.XmlWebApplicationContext;

/*
 * This class serves as a means to initialize a Spring cloud config properties source into a Spring MVC app during context initialization
 * It expects the following properties to be set in the property sources such as the environment varaibles
 * spring.cloud.config.name - application name to use when resolving properties from spring cloud config
 * spring.cloud.config.uri - address of spring cloud config server (eg - http://localhost:8888)
 */
public class SpringCloudConfigWebApplicationContextInitializer implements ApplicationContextInitializer<XmlWebApplicationContext> {
	
	protected final Log logger = LogFactory.getLog(getClass());

	public void initialize(XmlWebApplicationContext applicationContext) {
		
		logger.info("Starting Spring Cloud Config Initialization");
		
		ConfigurableEnvironment env =applicationContext.getEnvironment();
		
		ConfigServicePropertySourceLocator locator =  new ConfigServicePropertySourceLocator(new ConfigClientProperties(env));
		
		MutablePropertySources pSources = env.getPropertySources();
		
		org.springframework.core.env.PropertySource<?> springCloudConfigPSource = locator.locate(env);
		
		Assert.notNull(springCloudConfigPSource, "Configure Spring Cloud Service Endpoint must return properties");
		pSources.addLast(springCloudConfigPSource);
		
	};
}
