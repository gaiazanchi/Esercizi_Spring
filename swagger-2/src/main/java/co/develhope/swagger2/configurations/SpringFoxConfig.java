package co.develhope.swagger2.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfo(
                        "Arithmetic example",
                        "A simple swagger example api documentation",
                        "1.0.0",
                        "http://en.wikipedia.org/wiki/MIT_License",
                        new Contact("Gaia","https://develhope.co" ,"gaia.zanchi2001@gmail.com"),
                        "MIT",
                        "http://en.wikipedia.org/wiki/MIT_License",
                        Collections.emptyList()
                )).tags(
                        new Tag("default-controller", "The default controller"),
                        new Tag("math-controller", "The math controller")
                );
    }

}
