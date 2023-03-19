package co.develhope.firstapi5.controllers;

import co.develhope.firstapi5.DTO.CarDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import javax.validation.Valid;

@RestController
@RequestMapping("/cars")
public class CarController {

    @GetMapping
    public CarDTO getCar(@RequestParam String id, @RequestParam String modelName, @RequestParam Double price){
        return new CarDTO(id, modelName, price);
    }

    @PostMapping
    public ResponseEntity postCar(@Valid @RequestBody CarDTO car){
        System.out.println(car);
        return ResponseEntity.status(HttpStatus.CREATED).body("OK, creation successful!");
    }

}
