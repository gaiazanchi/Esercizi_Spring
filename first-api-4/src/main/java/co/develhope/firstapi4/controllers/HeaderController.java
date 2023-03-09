package co.develhope.firstapi4.controllers;

import co.develhope.firstapi4.services.HeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.UnknownHostException;


@RestController
public class HeaderController {

    @Autowired
    HeaderService headerService;

    @GetMapping("/headers")
    public String headers() throws UnknownHostException {
        return "host = " + headerService.getHost() + ", port = " + headerService.getPort();
    }

}
