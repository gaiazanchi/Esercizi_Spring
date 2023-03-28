package co.develhope.Interceptors01.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LegacyInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getRequestURL().toString().contains("/legacy")){
            response.setStatus(410);
            //right HTTP code status for Gone
            return false;
        }
        return true;
    }
}
