package com.td.notification_service;

import com.td.notification_service.config.KafkaConfigs;
import com.td.notification_service.kafka.JsonSerializer;
import com.td.notification_service.kafka.RunnableProducer;
import com.td.notification_service.kafka.types.FraudTransactionData;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.TimeoutException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class NotificationServiceApplication {

	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		SpringApplication.run(NotificationServiceApplication.class, args);


		logger.info("Creating Kafka Producer...");
		Properties props = new Properties();
		props.put(ProducerConfig.CLIENT_ID_CONFIG, KafkaConfigs.applicationID);
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfigs.bootstrapServers);

		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		KafkaProducer<String, FraudTransactionData> producer = new KafkaProducer<>(props);
		ExecutorService executorService = Executors.newFixedThreadPool(KafkaConfigs.noOfProducers);
		final List<RunnableProducer> runnableProducers = new ArrayList<>();



			// send message to topic
			logger.info("Start sending messages...");
			for (int i = 1; i <= KafkaConfigs.noOfProducers; i++) {
				RunnableProducer runnableProducer = new RunnableProducer(i, producer, KafkaConfigs.topicName, KafkaConfigs.producerSpeed);
				runnableProducers.add(runnableProducer);
				executorService.submit(runnableProducer);

			}

			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				for (RunnableProducer p : runnableProducers) p.shutdown();
				executorService.shutdown();
				logger.info("Closing Executor Service");
				try{
					executorService.awaitTermination(KafkaConfigs.producerSpeed * 2 , TimeUnit.MILLISECONDS);
				}catch(InterruptedException e){
					throw new RuntimeException(e);
				}

		}));


	}

}
