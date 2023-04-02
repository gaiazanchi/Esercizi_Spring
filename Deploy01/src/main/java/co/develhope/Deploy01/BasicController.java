package co.develhope.Deploy01;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class BasicController {

    String name = "Gaia";

    @GetMapping
    public String basicController(HttpServletRequest request){
        request.setAttribute("devName", name);
        return (String)request.getAttribute("devName");
    }

}
