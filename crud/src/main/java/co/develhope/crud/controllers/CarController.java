package co.develhope.crud.controllers;

import co.develhope.crud.entities.Car;
import co.develhope.crud.repositories.CarRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {
    @Autowired
    CarRepository carRepository;

    @PostMapping
    public void createCar(@RequestBody Car car){
        carRepository.save(car);
    }

    @GetMapping
    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Integer id){
        if(!carRepository.existsById(id)){
            return new Car();
        }else{
            Optional<Car> myCar = carRepository.findById(id);
            return myCar.get();
        }
    }
    
    @PatchMapping("/{id}")
    public Car changeCarType(@PathVariable Integer id, @RequestParam String type){
        if(!carRepository.existsById(id)){
            return new Car();
        }else{
            Car myCar = carRepository.findById(id).get();
            myCar.setType(type);
            carRepository.save(myCar);
            return myCar;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCarById(@PathVariable Integer id){
        if(!carRepository.existsById(id)){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Id not found");
        }
        carRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
    }

    @DeleteMapping()
    public void deleteAllCars(){
        carRepository.deleteAll();
    }

}
