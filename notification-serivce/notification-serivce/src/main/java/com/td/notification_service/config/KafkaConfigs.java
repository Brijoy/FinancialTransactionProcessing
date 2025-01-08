package com.td.notification_service.config;

public class KafkaConfigs {
        public final static String applicationID = "FinancialTransactions";
        public final static String bootstrapServers = "localhost:9092,localhost:9093";
        public final static String topicName = "fraud-detection";
        public final static int numEvents = 500000;

}
