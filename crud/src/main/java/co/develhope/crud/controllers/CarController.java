package co.develhope.crud.controllers;

import co.develhope.crud.entities.Car;
import co.develhope.crud.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @PutMapping("/{id}")
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
    public void deleteCarById(@PathVariable Integer id){
        carRepository.deleteById(id);
    }

    @DeleteMapping()
    public void deleteAllCars(){
        carRepository.deleteAll();
    }

}
