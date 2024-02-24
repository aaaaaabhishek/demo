package com.example.demo.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Collections;
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfo(
                        "Book_My_Trip Application",
                        "This project is developed by Abhi",
                        "1.0",
                        "Terms of service",
                        new Contact("Abhi", "https://example.com", "aabhishek08@gmail.com"),
                        "App licenses URL",
                        "URL of the license",
                        Collections.emptyList()))
                .select().apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
//    private ApiInfo getInfo() {
//        return new ApiInfo(
//                "Book_My_Trip Application",
//                "This project is developed by Abhi",
//                "1.0",
//                "Terms of service",
//                new Contact("Abhi", "https://example.com", "aabhishek08@gmail.com"),
//                "App licenses URL",
//                "URL of the license",
//                Collections.emptyList());
//    };
//
}
