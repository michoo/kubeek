package com.kubeek.service.rest.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableSwagger
@EnableAutoConfiguration
public class SwaggerConfig {

    private SpringSwaggerConfig springSwaggerConfig;

    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }

    @Bean
    @Profile("dev")
    public SwaggerSpringMvcPlugin customImplementation() {
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
            //This info will be used in Swagger. See realisation of ApiInfo for more details.
            .apiInfo(new ApiInfo(
                "Kubeek API",
                "Action available on REST API",
                null,
                null,
                null,
                null
            ))
            //Here we disable auto generating of responses for REST-endpoints
            .useDefaultResponseMessages(false)
            //Here we specify URI patterns which will be included in Swagger docs. Use regex for this purpose.
            .includePatterns("/api.*");
    }

}
