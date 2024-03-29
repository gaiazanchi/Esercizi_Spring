package co.develhope.FirstSpringExercise.controllers;

import co.develhope.FirstSpringExercise.services.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/name")
public class NameController {

    @Autowired
    NameService nameService;

    @GetMapping
    public String getName(@RequestParam String name){
        nameService.setName(new StringBuilder(name));
        return nameService.getName().toString();
    }

    @PostMapping
    public String postName(@RequestParam String name){
        nameService.setName(new StringBuilder(name));
        return nameService.getName().reverse().toString();
    }

}
