package co.develhope.Deploy02;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("")
public class BasicController {

    @GetMapping("/get-number")
    public int basicController(){
        Random r = new Random();
        int n1 = r.nextInt();
        int n2 = r.nextInt();
        return n1 + n2;
    }

}
