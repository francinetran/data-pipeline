package com.francinetran.datapipeline.stream.producers;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.mitre.synthea.engine.Generator;
import org.mitre.synthea.export.Exporter;
import org.mitre.synthea.helpers.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.francinetran.datapipeline.kafka.publishers.Publisher;


@Service
public class DataProducer{
	
	private final Logger logger = LoggerFactory.getLogger(DataProducer.class);
	
	@Autowired
	private Publisher publisher;

	

	Generator.GeneratorOptions options;
	Exporter.ExporterRuntimeOptions ero;
	ExecutorService generatorService;
	Generator generator;
	
	DataProducer(){
		
	}
	
	@PostConstruct
	public void init() {
		options = new Generator.GeneratorOptions();
		options.population = 1000;
		Config.set("exporter.fhir.export", "false");
		Config.set("exporter.hospital.fhir.export", "false");
		Config.set("exporter.practitioner.fhir.export", "false");
		ero = new Exporter.ExporterRuntimeOptions();
		ero.enableQueue(Exporter.SupportedFhirVersion.R4);
		generator = new Generator(options, ero);
		generatorService = Executors.newFixedThreadPool(1);
		generatorService.submit(() -> generator.run());
		
	}


	
	

	@Scheduled(fixedDelay=200000)
	public void produce() {
		
		// Retrieve the generated records
		int recordCount = 0;
		while(recordCount < options.population) {
		  try {
		    String jsonRecord = ero.getNextRecord();
		    recordCount++;
		    //logger.info(String.format("Sent%s", jsonRecord));

		    publisher.publish( jsonRecord, null);
		    //logger.info(String.format("#### -> Sent-> %s", jsonRecord));
		  } catch (InterruptedException ex) {
		    break;
		  }
		}
	}

	
	@PreDestroy
	void shutdown() {
		// Shutdown the generator
		generatorService.shutdownNow();
		
	}

}
