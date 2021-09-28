package com.ikubinfo.assignment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * Created by sabbir on 9/29/21.
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder.name("authorization")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .defaultValue("Bearer $(em9uZTpteXBhc3N3b3Jk)")
                .required(false)
                .build();
        java.util.List<Parameter> aParameters = new ArrayList<>();
        aParameters.add(aParameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .any())
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData())
                .pathMapping("")
                .globalOperationParameters(aParameters);
    }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Spring Boot Rest API Service")
                .description("Spring Boot Rest API service provides API endpoint")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version("1.0")
                .contact(new Contact("sabbir", "", "sabbir.prog@gmail.com"))
                .build();
    }
}
