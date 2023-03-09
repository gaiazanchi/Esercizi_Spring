package co.develhope.FirstSpringExercise.controllers;

import co.develhope.FirstSpringExercise.services.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {

    @Autowired
    NameService nameService;

    @GetMapping("/name")
    public String getName(){
        return nameService.getName().toString();
    }

    @PostMapping("/name")
    public String postName(){
        return nameService.getName().reverse().toString();
    }

}
