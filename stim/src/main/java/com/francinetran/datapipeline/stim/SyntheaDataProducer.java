package com.francinetran.datapipeline.stim;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.mitre.synthea.engine.Generator;
import org.mitre.synthea.export.Exporter;
import org.mitre.synthea.helpers.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class SyntheaDataProducer{
	
	private final Logger logger = LoggerFactory.getLogger(SyntheaDataProducer.class);


	Generator.GeneratorOptions options;
	Exporter.ExporterRuntimeOptions ero;
	ExecutorService generatorService;
	Generator generator;
	
	SyntheaDataProducer(){
	}
	
	@PostConstruct
	public void init() {
		options = new Generator.GeneratorOptions();
		options.population = 1;
		Config.set("exporter.fhir.export", "false");
		Config.set("exporter.hospital.fhir.export", "false");
		Config.set("exporter.practitioner.fhir.export", "false");
		Config.set("exporter.years_of_history ",  "1");
		ero = new Exporter.ExporterRuntimeOptions();
		ero.enableQueue(Exporter.SupportedFhirVersion.R4);
		generator = new Generator(options, ero);
		generatorService = Executors.newFixedThreadPool(1);
		
		
	}


	public String produceJson() {
		generatorService.submit(() -> generator.run());
		// Retrieve the generated records
		int recordCount = 0;
		String jsonRecord="";
		while(recordCount < options.population) {
		  try {
		    jsonRecord = ero.getNextRecord();
		    recordCount++;
		    //logger.info(String.format("Sent%s", jsonRecord));
		    
		    //publisher.publish( jsonRecord, "entry");
		    //logger.info(String.format("#### -> Sent-> %s", jsonRecord));
		  } catch (InterruptedException ex) {
		    break;
		  }
		}
		return jsonRecord;
	}

	
	@PreDestroy
	void preDestroy() {
		// Shutdown the generator
		generatorService.shutdownNow();
		
	}

}
