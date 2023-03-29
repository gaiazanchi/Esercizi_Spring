package co.develhope.CustomQueries02.controllers;

import co.develhope.CustomQueries02.entities.Flight;
import co.develhope.CustomQueries02.entities.enums.Status;
import co.develhope.CustomQueries02.repositories.FlightRepository;
import co.develhope.CustomQueries02.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    FlightService flightService;

    @Autowired
    FlightRepository flightRepository;

    @PostMapping
    public ResponseEntity createNFlights(@RequestParam(required = false) Integer number){
        flightService.createnFlights(number);
        return ResponseEntity.status(HttpStatus.CREATED).body("Flights created");
    }

    @GetMapping("/ordered")
    public Page<Flight> getAllFlightsOrdered(@RequestParam Integer page, @RequestParam Integer size){
        return flightService.getAllFlightsPaginated(page, size);
    }

    @GetMapping("/on-time")
    public List<Flight> getAllOnTimeFlights(){
        return flightService.getAllFlightsOnCertainStatus(Status.ONTIME);
    }

    @GetMapping("/status")
    public List<Flight> getAllFlightsInCertainStatuses(@RequestParam Status p1, @RequestParam Status p2){
        return flightRepository.findAllFlightsWithCertainStatuses(p1,p2);
    }

    //metodo alternativo senza custom query
    /*@GetMapping("/status")
    public List<Flight> getAllFlightsInCertainStatuses(@RequestParam Status p1, @RequestParam Status p2){
        List <Flight> myList = flightService.getAllFlightsOnCertainStatus(p1);
        myList.addAll(flightService.getAllFlightsOnCertainStatus(p2));
        return myList;
    }*/

}
