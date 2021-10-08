package com.francinetran.datapipeline.stim;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/application.properties")
public class StimConfiguration {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StimConfiguration.class);
	
	@Value("${data.topic}")
	private String fhirTopic;

	@Value("${kafka.producer.bootstrapServersConfig}")
	private String kafkaProducerBootstrapServersConfig;
	
	@Value("${kafka.producer.lingerMsConfig}")
	private int kafkaProducerLingerMsConfig;

	@Value("${kafka.producer.maxRequestSizeConfig}")
	private int kafkaProducerMaxRequestSizeConfig;

	
	public StimConfiguration(){
		
	}

	
	

	public String getFhirTopic() {
		return fhirTopic;
	}




	public void setFhirTopic(String fhirTopic) {
		this.fhirTopic = fhirTopic;
	}




	public String getKafkaProducerBootstrapServersConfig() {
		return kafkaProducerBootstrapServersConfig;
	}


	public void setKafkaProducerBootstrapServersConfig(String kafkaProducerBootstrapServersConfig) {
		this.kafkaProducerBootstrapServersConfig = kafkaProducerBootstrapServersConfig;
	}


	public int getKafkaProducerLingerMsConfig() {
		return kafkaProducerLingerMsConfig;
	}


	public void setKafkaProducerLingerMsConfig(int kafkaProducerLingerMsConfig) {
		this.kafkaProducerLingerMsConfig = kafkaProducerLingerMsConfig;
	}


	public int getKafkaProducerMaxRequestSizeConfig() {
		return kafkaProducerMaxRequestSizeConfig;
	}


	public void setKafkaProducerMaxRequestSizeConfig(int kafkaProducerMaxRequestSizeConfig) {
		this.kafkaProducerMaxRequestSizeConfig = kafkaProducerMaxRequestSizeConfig;
	}
	
	
	
	
	
//	@Autowired
//	KafkaProperties kafkaProps;
//	
////	@Bean
////	StreamExecutionEnvironment getStreamExecutionEnvironment() {
////		return StreamExecutionEnvironment.getExecutionEnvironment();
////	}
////	
////	@Bean
////	FlinkKafkaConsumer011<String> getFlinkKafkaConsumer() {
////		Properties properties = new Properties();
////		properties.setProperty("bootstrap.servers", kafkaProps.getBootstrapAddress());
////		properties.setProperty("group.id", kafkaProps.getGroupid());
////		return new FlinkKafkaConsumer011<String>(kafkaProps.getTopic(), new SimpleStringSchema(), properties);
////	}
//
//	
//
//    @Bean
//    ConcurrentKafkaListenerContainerFactory<String, String>
//                        kafkaListenerContainerFactory(ConsumerFactory<String, String> consumerFactory) {
//        ConcurrentKafkaListenerContainerFactory<String, String> factory =
//                                new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(consumerFactory);
//        return factory;
//    }
//
//    @Bean
//    public ConsumerFactory<String, String> consumerFactory() {
//        return new DefaultKafkaConsumerFactory<>(consumerProps());
//    }
//
//    private Map<String, Object> consumerProps() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProps.getBootstrapAddress());
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProps.getGroupid());
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
//        props.put(ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG, "5000000");
//        props.put(ConsumerConfig.FETCH_MAX_BYTES_CONFIG, "5000000");
//        
//        // ...
//        return props;
//    }
//
//    @Bean
//    public Publisher publisher(KafkaTemplate<String, String> template) {
//        return new Publisher(template);
//    }
//
////    @Bean
////    public Subscriber subscriber() {
////        return new Subscriber();
////    }
//
//    @Bean
//    public ProducerFactory<String, String> producerFactory() {
//        return new DefaultKafkaProducerFactory<>(senderProps());
//    }
//
//    private Map<String, Object> senderProps() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProps.getBootstrapAddress());
//        props.put(ProducerConfig.LINGER_MS_CONFIG, 10);
//        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
//        props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 5000000);
//        //...
//        return props;
//    }
//
//    @Bean
//    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> producerFactory) {
//        return new KafkaTemplate<String, String>(producerFactory);
//    }
    
}
