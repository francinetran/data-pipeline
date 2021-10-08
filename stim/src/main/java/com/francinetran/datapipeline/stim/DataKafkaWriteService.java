package com.francinetran.datapipeline.stim;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataKafkaWriteService {
	
	DataKafkaProducer kafkaProducer;
	SyntheaDataProducer dataProducer;
	StimConfiguration stimConfiguration;
	
	@Autowired
	DataKafkaWriteService(DataKafkaProducer kafkaProducer, SyntheaDataProducer dataProducer, StimConfiguration stimConfiguration){
		this.kafkaProducer = kafkaProducer;
		this.dataProducer = dataProducer;
		this.stimConfiguration = stimConfiguration;
	}
	
	public boolean queueFHIRData(String json) {
			try {
				queueData(stimConfiguration.getFhirTopic(), json);
				return true;
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
	}
	
	
	private void queueData(String topic, String json) throws ExecutionException {
			
		ProducerRecord<String,String> producerRecord = new ProducerRecord<>(topic, json);
		Producer<String,String> producer = kafkaProducer.getProducer();
		producer.send(producerRecord);

		
	}
	

}
