package com.francinetran.datapipeline.kafka.publishers;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;

import com.francinetran.datapipeline.kafka.config.KafkaConfig;

public class Publisher {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(KafkaConfig.class);
		//context.getBean(Sender.class).send("test", 42);
	}

	private final KafkaTemplate<String, String> template;

	public Publisher(KafkaTemplate<String, String> template) {
		this.template = template;
	}

	public void publish(String toSend, String key) {
		this.template.send("FHIR-topic", key, toSend);
	}

}