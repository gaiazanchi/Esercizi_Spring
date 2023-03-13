package co.develhope.FirstSpringExercise.services;

import org.springframework.stereotype.Service;

@Service
public class NameService {

    private StringBuilder name = new StringBuilder();

    public StringBuilder getName() {
        return name;
    }

    public void setName(StringBuilder name) {
        this.name = name;
    }
}
