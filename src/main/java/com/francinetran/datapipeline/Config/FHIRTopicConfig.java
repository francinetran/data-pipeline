package com.francinetran.datapipeline.Config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration("fhirTopicProperties")
@ConfigurationProperties(prefix="fhir.topic")
public class FHIRTopicConfig {
	
	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	

}
