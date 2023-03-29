package co.develhope.CustomQueries02.services;

import co.develhope.CustomQueries02.entities.Flight;
import co.develhope.CustomQueries02.entities.enums.Status;
import co.develhope.CustomQueries02.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public void createnFlights(Integer n){
        if(n == null || n == 0){
            n = 100;
        }
        int i = 0;
        while(i < n){
            flightRepository.save(new Flight(FlightService.generateRandomString(),
                    FlightService.generateRandomString(), FlightService.generateRandomString(), Status.randomStatus()));
            i++;
        }
    }

    private static String generateRandomString(){
        int leftLimit = 48;
        int rightLimit = 122;
        int targetStringLength = 10;
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public Page<Flight> getAllFlightsPaginated(Integer page, Integer size){
        Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC, "fromAirport"));
        Pageable pageable = PageRequest.of(page,size,sort);
        return flightRepository.findAll(pageable);
    }

    public List<Flight> getAllFlightsOnCertainStatus(Status status){
        return flightRepository.findAllByStatus(status);
    }

}
