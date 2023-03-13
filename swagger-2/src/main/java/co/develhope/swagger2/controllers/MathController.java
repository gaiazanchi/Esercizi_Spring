package co.develhope.swagger2.controllers;

import co.develhope.swagger2.entities.ArithmeticOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/math")
public class MathController {

    @GetMapping("")
    public String welcomeMathMsg(){
        return "Benvenuto!";
    }

    @GetMapping("/division-info")
    public ArithmeticOperation getDivisionInfo(){
        return new ArithmeticOperation("Division", 2, "Division between numbers", new String[]{"invariantiva", "distributiva"});
    }

    @GetMapping("/multiplication")
    public Integer doMultiplication(@RequestParam Integer int1, @RequestParam Integer int2){
        return int1*int2;
    }

    @GetMapping("/square/{n}")
    public Double returnSquare(@PathVariable("n") Double number){
        return Math.sqrt(number);
    }

}
