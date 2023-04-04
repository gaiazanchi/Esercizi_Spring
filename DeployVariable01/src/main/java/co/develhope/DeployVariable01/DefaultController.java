package co.develhope.DeployVariable01;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DefaultController {

    @Value("${myCustomVar.authCode}")
    private String authCode;

    @Value("${myCustomVar.devName}")
    private String name;

    @GetMapping("/welcome")
    public String welcomeUser(){
        return "Welcome, authcode: " + authCode + " e devName: " +name;
    }

}
