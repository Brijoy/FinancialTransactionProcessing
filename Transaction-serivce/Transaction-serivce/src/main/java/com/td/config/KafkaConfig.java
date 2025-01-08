package com.td.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConfig {

    // Producer configuration
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(producerConfigs()));
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put("bootstrap.servers", "localhost:9092"); // Kafka server
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);
        return props;
    }

    // Consumer configuration
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> consumerProps = new HashMap<>();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "otp-validation-group");
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return new DefaultKafkaConsumerFactory<>(consumerProps);
    }

    // Message Listener Container (Concurrent)
    @Bean
    public ConcurrentMessageListenerContainer<String, String> messageListenerContainer() {
        // Step 1: Create ContainerProperties
        ContainerProperties containerProps = new ContainerProperties("otp-topic");  // Topic name
        containerProps.setMessageListener(messageListener());  // Set the message listener

        // Step 2: Create and return the ConcurrentMessageListenerContainer
        return new ConcurrentMessageListenerContainer<>(consumerFactory(), containerProps);
    }

    // Step 3: MessageListener for processing OTP messages
    public MessageListener<String, String> messageListener() {
        return record -> {
            String message = record.value();
            System.out.println("Received OTP validation message: " + message);

            // You can process the message here or forward it to your OTP validation service
        };
    }
}
