package co.develhope.logging.controllers;

import co.develhope.logging.services.ExponentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {
    Logger logger = LoggerFactory.getLogger(BasicController.class);

    @Autowired
    ExponentService exponentService;

    @GetMapping("/")
    public String welcome(){
        logger.info("Welcome method starting");
        return "Welcome!";
    }

    @GetMapping("/exp")
    public int exponentCalculation(){
        logger.info("Method exponentCalculation called");
        return exponentService.calculatePower();
    }

    @GetMapping("/get-errors")
    public void getErrors(){
        logger.info("Method getErrors starting");
        Error e = new Error("Custom error");
        logger.error("New error" + e);
        throw e;
    }

}
