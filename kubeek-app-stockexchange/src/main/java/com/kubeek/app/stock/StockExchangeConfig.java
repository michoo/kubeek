package com.kubeek.app.stock;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockExchangeConfig {

    @Value("${kubeek.app.stockexchange.appcolor:00FF00}")
    private String appColor;

    @Value("${kubeek.app.stockexchange.speed:15000}")
    private int speed;

    @Value("${kubeek.app.stockExchange.defaultfont:6x10}")
    private String defaultFont;

    @Bean
    public StockExchangeParams getStockExchangeParams(){
        return new StockExchangeParams(appColor, speed, defaultFont);
    }
}
