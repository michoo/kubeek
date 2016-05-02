package com.kubeek.app.clock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClockConfig {

    @Value("${kubeek.app.clock.appcolor:00FF00}")
    private String appColor;

    @Value("${kubeek.app.clock.speed:15000}")
    private int speed;

    @Value("${kubeek.app.clock.defaultfont:6x10}")
    private String defaultFont;

    @Bean
    public ClockParams getClockParams(){
        return new ClockParams(appColor, speed, defaultFont);
    }
}
