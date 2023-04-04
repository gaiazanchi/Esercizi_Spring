package co.develhope.Deploy01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BasicController {

    @Value("${myCustomVar.devName}")
    private String name;

    @GetMapping
    public String basicController(){
        return name;
    }

}
