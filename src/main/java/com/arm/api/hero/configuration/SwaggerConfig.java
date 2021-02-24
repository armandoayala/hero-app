package com.arm.api.hero.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${service-app.apiinfo.namecontact:-}")
    private String namecontact;

    @Value("${service-app.apiinfo.mailcontact:-}")
    private String mailcontact;

    @Value("${service-app.apiinfo.title:-}")
    private String title;

    @Value("${service-app.apiinfo.description:-}")
    private String description;

    @Value("${service-app.apiinfo.version:-}")
    private String version;

    @Value("${service-app.apiinfo.enabledsubmitoperation:-}")
    private String enabledsubmitoperation;

    @Value("${service-app.apiinfo.seemore:-}")
    private String seemore;

    @Value("${service-app.apiinfo.basepackage:-}")
    private String basepackage;

    @Bean
    public Docket api() throws IOException {

        ApiInfo apiInfo = new ApiInfo(title, description,
                version, "", new Contact(namecontact, "", mailcontact), "", "", Collections.emptyList());

        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basepackage))
                .build().apiInfo(apiInfo);
    }

    @Bean
    public UiConfiguration uiConfig() {

        if (enabledsubmitoperation.compareTo("1") == 0) {
            return UiConfigurationBuilder.builder()
                    .supportedSubmitMethods(UiConfiguration.Constants.DEFAULT_SUBMIT_METHODS)
                    .build();
        }

        return UiConfigurationBuilder.builder()
                .supportedSubmitMethods(UiConfiguration.Constants.NO_SUBMIT_METHODS)
                .build();
    }
}

