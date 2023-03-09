package co.develhope.firstapi5.controllers;

import co.develhope.firstapi5.DTO.CarDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class CarController {

    @GetMapping("/cars")
    public CarDTO getCar(){
        return new CarDTO();
    }

    @PostMapping("/cars")
    public String postCar(@RequestBody String json){
        System.out.println(json);
        return "Creation successful!";
    }

}
