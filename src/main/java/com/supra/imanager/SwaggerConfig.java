package com.supra.imanager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig  extends WebMvcConfigurationSupport {
    @Bean
    public Docket imanagerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select().apis(RequestHandlerSelectors.basePackage("com.supra.imanager.controller"))
                .build()
                .apiInfo(metaData());
    }
    
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("iManager REST API")
                .description("\"RestFul Apis for Mobile App of IManager\"")
                .version("1.0.0")
                .contact(new Contact("Dreksha Chaudhary", "", "dreksha.chaudhary@supraits.com"))
                .build();
    }
    
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}