package co.develhope.FirstSpringExercise.services;

import org.springframework.stereotype.Service;

@Service
public class NameService {

    private StringBuilder name = new StringBuilder("Gaia");

    public StringBuilder getName() {
        return name;
    }

}
