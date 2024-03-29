package co.develhope.firstapi3.controllers;

import co.develhope.firstapi3.services.StringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/strings")
public class StringController {

    @Autowired
    StringService stringService;

    @GetMapping
    public String concatenateStrings(@RequestParam String firstString, @RequestParam(required = false) String secondString){
        stringService.setFirstString(firstString);
        stringService.setSecondString(secondString);
        return stringService.concatenate();
    }

}
