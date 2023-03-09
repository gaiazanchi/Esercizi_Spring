package co.develhope.SecondSpringExercise.controllers;

import co.develhope.SecondSpringExercise.services.FactorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FactorialController {

    @Autowired
    FactorialService factorialService;

    @GetMapping("/factorial")
    public Integer getFactorial(@RequestParam Integer number){
        factorialService.setNumber(number);
        return factorialService.calculateFactorial();
    }

}
