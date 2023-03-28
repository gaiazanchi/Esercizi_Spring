package co.develhope.Interceptors02.configurations;

import co.develhope.Interceptors02.interceptors.MonthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private MonthInterceptor monthInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(monthInterceptor);
    }
}
