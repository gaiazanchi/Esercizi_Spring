package co.develhope.SecondSpringExercise.services;

import org.springframework.stereotype.Service;

@Service
public class FactorialService {

    private Integer number;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer calculateFactorial(){
        Integer fact = 1;
        for(int i = 1; i <= this.number; i++){
            fact = fact * i;
        }
        return fact;
    }

}
