package co.develhope.firstapi5.controllers;

import co.develhope.firstapi5.DTO.CarDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/cars")
public class CarController {

    @GetMapping("")
    public CarDTO getCar(@RequestParam String id, @RequestParam String modelName, @RequestParam Double price){
        return new CarDTO(id, modelName, price);
    }

    /*
    @GetMapping("")
    public CarDTO getCar(){
        return new CarDTO();
    }*/

    @PostMapping("")
    public String postCar(@Valid @RequestBody CarDTO car){
        //System.out.println(new ResponseEntity<>(car, HttpStatus.CREATED));
        System.out.println(car);
        return "Creation successful!";
    }

}
