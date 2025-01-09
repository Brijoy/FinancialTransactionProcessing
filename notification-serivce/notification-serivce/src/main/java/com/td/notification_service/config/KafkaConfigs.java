package com.td.notification_service.config;

public class KafkaConfigs {
        public final static String applicationID = "FinancialTransactions";
        /*public final static String bootstrapServers = "127.0.0.1:9092,localhost:9093";*/
        public final static String bootstrapServers = "127.0.0.1:9092";
        public final static String topicName = "fraud-detection";
        public final static int numEvents = 500000;
        public final static int noOfProducers = 5;
        public final static int producerSpeed = 2;
        public final static String topicName1 = "transaction";
        public final static String[] eventFiles = {"notification-serivce/notification-serivce/data/NSE05NOV2018BHAV.csv","notification-serivce/notification-serivce/data/NSE06NOV2018BHAV.csv"};

}
