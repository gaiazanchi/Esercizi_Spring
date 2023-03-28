package co.develhope.Interceptors02.interceptors;

import co.develhope.Interceptors02.entities.Month;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class MonthInterceptor implements HandlerInterceptor {

    static List<Month> months = new ArrayList<>(Arrays.asList(
            new Month(1, "Gennaio", "January", "Januar"),
            new Month(2, "Febbraio", "February", "Februar"),
            new Month(3, "Marzo", "March", "Marsch"),
            new Month(4, "Aprile", "April", "April"),
            new Month(5, "Maggio", "May", "Dürfen"),
            new Month(6, "Giugno", "June", "Juni")
    ));

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String monthNumberString = request.getHeader("monthNumber");
        if(monthNumberString == null){
            response.setStatus(400);
            return false;
        }else{
            Integer monthNumber = Integer.parseInt(monthNumberString);
            Optional<Month> myMonth = months.stream().filter(month ->{
                return month.getMonthName() == monthNumber;
            }).findFirst();
            if(myMonth.isPresent()){
                request.setAttribute("month", myMonth.get());
            }else{
                request.setAttribute("month", new Month(0,"nope","nope","nope"));
            }
            response.setStatus(200);
            return true;
        }
    }
}
