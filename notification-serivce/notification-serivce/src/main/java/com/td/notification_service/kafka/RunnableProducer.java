package com.td.notification_service.kafka;

import com.td.notification_service.kafka.datagenerator.FraudTransactionGenerator;
import com.td.notification_service.kafka.types.FraudTransactionData;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.atomic.AtomicBoolean;


public class RunnableProducer implements Runnable{

    private static final Logger logger = LogManager.getLogger();
    private final AtomicBoolean stopper = new AtomicBoolean(false);
    private KafkaProducer<String, FraudTransactionData> producer;
    private String topicName;
    private FraudTransactionGenerator fraudTransactionGenerator;
    private int produceSpeed;
    private int id;

    public RunnableProducer(int id, KafkaProducer<String, FraudTransactionData> producer,String topicName ,int produceSpeed) {
        this.id = id;
        this.producer = producer;
        this.topicName = topicName;
        this.produceSpeed = produceSpeed;
        this.fraudTransactionGenerator = FraudTransactionGenerator.getInstance();

    }

    @Override
    public void run() {
        try {
            logger.info("Starting producer thread - " + id);
            while (!stopper.get()) {
                FraudTransactionData fraudTransactionData = fraudTransactionGenerator.getNextFraudTransactionData();

                /*ProducerRecord<String, String> record = new ProducerRecord<>(topicName, fraudTransactionData.getTransactionId(), fraudTransactionData);*/
                producer.send(new ProducerRecord<>
                        (topicName,
                                fraudTransactionData.getTransactionId(),
                                fraudTransactionData));
                logger.info("producer");
                Thread.sleep(produceSpeed);
            }
        }
            catch(Exception e){
                logger.error("Exception in producer thread -"+ id);
                throw new RuntimeException(e);
            }
        }

        public void shutdown(){
        logger.info("Shutting down producer thread - "+ id);
        stopper.set(true);
        }


}
