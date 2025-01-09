package com.td.notification_service.kafka.datagenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.td.notification_service.kafka.types.FraudTransactionData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FraudTransactionGenerator {
    private static final Logger logger = LogManager.getLogger();
    private static final FraudTransactionGenerator ourInstance = new FraudTransactionGenerator();
    private final Random random;
    /*ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(classLoader.getResource("data/fraudtransactiondata.json").getFile());
*/
    private List<FraudTransactionData> fraudTransactionData;



    public static FraudTransactionGenerator getInstance(){
        return ourInstance;
    }

    private FraudTransactionGenerator() {

        random = new Random();
        fraudTransactionData = loadFraudTransactionData();
    }

    private List<FraudTransactionData> loadFraudTransactionData() {


    final String DATAFILE = "src/main/resources/data/fraudtransactiondata.json";
        final ObjectMapper mapper;
        mapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getResourceAsStream("/data/fraudtransactiondata.json")) {
            return Arrays.asList(mapper.readValue(
                    inputStream,
                    FraudTransactionData[].class));
           //fraudTransactionData = mapper.readValue(new File(DATAFILE), FraudTransactionData[].class);
           /* fraudTransactionData = mapper.readValue(file, FraudTransactionData[].class);*/
        }catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    public FraudTransactionData getNextFraudTransactionData() {


            int index = random.nextInt(fraudTransactionData.size());
            FraudTransactionData data = fraudTransactionData.get(index);
            logger.info(data);
            return data;


    }
}
