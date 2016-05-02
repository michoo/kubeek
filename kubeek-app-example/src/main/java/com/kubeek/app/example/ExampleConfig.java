package com.kubeek.app.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExampleConfig {
    @Value("${kubeek.app.example.appcolor:00FF00}")
    private String appColor;

    @Value("${kubeek.app.example.speed:15000}")
    private int speed;

    @Value("${kubeek.app.example.defaultfont:6x10}")
    private String defaultFont;


    @Bean
    public ExampleParams getExampleParams(){
        return new ExampleParams(appColor, speed, defaultFont);
    }
}
