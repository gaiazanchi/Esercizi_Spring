package co.develhope.Interceptors02.controllers;

import co.develhope.Interceptors02.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/months")
public class MonthController {

    @GetMapping
    public Month getMonth(HttpServletRequest request){
        Month myMonth = (Month) request.getAttribute("month");
        return myMonth;
    }

}
