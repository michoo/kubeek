package com.kubeek.app.gameoflife;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GameOfLifeConfig {

    @Value("${kubeek.app.gameoflife.appcolor:00FF00}")
    private String appColor;

    @Value("${kubeek.app.gameoflife.generation:200}")
    private int generation;

    @Value("${kubeek.app.gameoflife.delay:200}")
    private int delay;

    @Bean
    public GameOfLifeParams getGameOfLifeParams() {
        return new GameOfLifeParams(appColor, generation, delay);
    }
}
