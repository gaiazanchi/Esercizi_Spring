package co.develhope.firstapi4.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.InetSocketAddress;


@RestController
public class HeaderController {

    @GetMapping("/headers")
    public String headers(HttpServletRequest request){
        return "host = " + request.getHeader("Host").split(":")[0] +
                ", port = " + request.getHeader("Host").split(":")[1];
    }

    @GetMapping("/headers2")
    public String headers2(@RequestHeader HttpHeaders h) {
        InetSocketAddress host = h.getHost();
        return "host = " + host.getHostName() + ", port = " + host.getPort();
    }

}
