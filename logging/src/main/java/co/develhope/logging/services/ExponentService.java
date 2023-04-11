package co.develhope.logging.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExponentService {

    Logger logger = LoggerFactory.getLogger(ExponentService.class);

    @Value("${customVar1}")
    int int1;

    @Value("${customVar2}")
    int int2;

    public int calculatePower(){
        logger.debug("Method calculatePower starting");
        logger.debug("Starting calculation");
        int power = (int) Math.pow(int1, int2);
        logger.debug("Ending calculation");
        return power;
    }

}
