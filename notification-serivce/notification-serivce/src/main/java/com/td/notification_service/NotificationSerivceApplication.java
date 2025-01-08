package com.td.notification_service;

import com.td.notification_service.config.KafkaConfigs;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.TimeoutException;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Properties;

@SpringBootApplication
public class NotificationSerivceApplication {

	private static final Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		SpringApplication.run(NotificationSerivceApplication.class, args);
		logger.info("Creating Kafka Producer...");
		Properties props = new Properties();
		props.put(ProducerConfig.CLIENT_ID_CONFIG, KafkaConfigs.applicationID);
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConfigs.bootstrapServers);
		/*props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KafkaConfigs.class.getName());
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaConfigs.class.getName());*/
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

		KafkaProducer<String, String> producer = new KafkaProducer<>(props);

		try {
			// send message to topic
			logger.info("Start sending messages...");
			for (int i = 1; i <= KafkaConfigs.numEvents; i++) {
				producer.send(new ProducerRecord<>(KafkaConfigs.topicName, "Key value"+i, "Simple Message-" + i));
			}
		}
		catch (TimeoutException e) {
			// handle timeout exception
			logger.error("Timeout occurred while sending message to Kafka",e);
		}
		finally {
			logger.info("Finished - Closing Kafka Producer.");
			producer.close();
		}


	}

}
