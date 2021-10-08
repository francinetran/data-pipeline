package com.francinetran.datapipeline.stim;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@Controller
public class DataController {
	
	DataKafkaWriteService service;
	SyntheaDataProducer dataProducer;
	StimConfiguration stimConfiguration;
	
	@Autowired
	DataController(DataKafkaWriteService service, SyntheaDataProducer dataProducer, StimConfiguration stimConfiguration){
		this.service = service;
		this.dataProducer = dataProducer;
		this.stimConfiguration = stimConfiguration;
	}
	
	
	@Scheduled(fixedDelay=5000)
	private void publish() {
		
		String message = dataProducer.produceJson();
		service.queueFHIRData(message);
		
	}

}
