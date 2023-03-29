package co.develhope.CustomQueries01.services;

import co.develhope.CustomQueries01.entities.Flight;
import co.develhope.CustomQueries01.entities.enums.Status;
import co.develhope.CustomQueries01.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    public void create50Flights(){
        int i = 0;
        while(i < 50){
            flightRepository.save(new Flight(FlightService.generateRandomString(),
                    FlightService.generateRandomString(), FlightService.generateRandomString(), Status.ONTIME));
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
}
