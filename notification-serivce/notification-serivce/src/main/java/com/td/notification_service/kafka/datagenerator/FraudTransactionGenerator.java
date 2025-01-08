package com.td.notification_service.kafka.datagenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.notification_service.kafka.types.FraudTransactionData;

import java.io.File;
import java.util.Random;

public class FraudTransactionGenerator {
    private static final FraudTransactionGenerator ourInstance = new FraudTransactionGenerator();
    private final Random random;
    ClassLoader classLoader = getClass().getClassLoader();
   /* File file = new File(classLoader.getResource("data/fraudtransactiondata.json").getFile());
*/

    private FraudTransactionData[] fraudTransactionData;

    private int getIndex(){
        return random.nextInt(100);
    }

    public static FraudTransactionGenerator getInstance(){
        return ourInstance;
    }

    private FraudTransactionGenerator() {

        final String DATAFILE = "src/main/resources/data/fraudtransactiondata.json";
        final ObjectMapper mapper;
        random = new Random();
        mapper = new ObjectMapper();
        try{
            fraudTransactionData = mapper.readValue(
                    new File(classLoader.getResource
                            ("data/fraudtransactiondata.json").getFile()),
                    FraudTransactionData[].class);
           //fraudTransactionData = mapper.readValue(new File(DATAFILE), FraudTransactionData[].class);
           /* fraudTransactionData = mapper.readValue(file, FraudTransactionData[].class);*/
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public FraudTransactionData getNextFraudTransactionData() {
        return fraudTransactionData[getIndex()];
    }
}
