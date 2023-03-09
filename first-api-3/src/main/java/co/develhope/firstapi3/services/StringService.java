package co.develhope.firstapi3.services;

import org.springframework.stereotype.Service;

@Service
public class StringService {

    private String firstString;
    private String secondString = null;

    public String getFirstString() {
        return firstString;
    }

    public void setFirstString(String firstString) {
        this.firstString = firstString;
    }

    public String getSecondString() {
        return secondString;
    }

    public void setSecondString(String secondString) {
        this.secondString = secondString;
    }

    public String concatenate(){
        if(this.secondString == null){
            return this.firstString;
        }
        return this.firstString + " " + this.secondString;
    }

}
