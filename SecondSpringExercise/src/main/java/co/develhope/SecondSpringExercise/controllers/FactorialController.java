package co.develhope.SecondSpringExercise.controllers;

import co.develhope.SecondSpringExercise.services.FactorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/factorial")
public class FactorialController {

    @Autowired
    FactorialService factorialService;

    @GetMapping("/{n}")
    public Integer getFactorial(@PathVariable Integer n){
        factorialService.setNumber(n);
        return factorialService.calculateFactorial();
    }

}
