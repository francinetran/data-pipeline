package com.francinetran.datapipeline.stim;

import java.util.Properties;

import javax.annotation.PreDestroy;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataKafkaProducer {

		private Producer<String,String> producer;
		
		StimConfiguration config;
		
		@Autowired
		public DataKafkaProducer(StimConfiguration config) {
			this.config = config;
			
			Properties properties = new Properties();
			
			properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getKafkaProducerBootstrapServersConfig());
			properties.put(ProducerConfig.LINGER_MS_CONFIG, config.getKafkaProducerLingerMsConfig());
			properties.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, config.getKafkaProducerMaxRequestSizeConfig());
			properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
			properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
			
			producer = new KafkaProducer<>(properties);
			
		}
		
		public Producer<String,String> getProducer(){
			return producer;
		}
		
		@PreDestroy
		public void preDestroy() {
			if(producer != null) {
				producer.close();
			}
			
		}
		
		
		
	
}
