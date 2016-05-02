package com.kubeek.app.binaryclock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class BinaryClockConfig {

    @Value("${kubeek.app.binaryclock.upcolor:FF0000}")
    private String upColor;

    @Value("${kubeek.app.binaryclock.downcolor:00FF00}")
    private String downColor;

    @Value("${kubeek.app.binaryclock.speed:15000}")
    private int speed;


    @Bean
    public BinaryClockParams getBinaryClockParams(){
        return new BinaryClockParams(upColor, downColor, speed);
    }
}
