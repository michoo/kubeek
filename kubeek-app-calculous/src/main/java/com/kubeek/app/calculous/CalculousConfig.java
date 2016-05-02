package com.kubeek.app.calculous;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CalculousConfig {
    @Value("${kubeek.app.calculous.appcolor:00FF00}")
    private String appColor;

    @Value("${kubeek.app.calculous.speed:15000}")
    private int speed;

    @Value("${kubeek.app.calculous.defaultfont:6x10}")
    private String defaultFont;

    @Value("${kubeek.app.calculous.difficulty:2}")
    private int difficulty;

    @Value("${kubeek.app.calculous.digits:2}")
    private int digits;


    @Bean
    public CalculousParams getExampleParams(){
        return new CalculousParams(appColor, speed, defaultFont, difficulty, digits);
    }



}
