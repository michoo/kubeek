package com.kubeek.app.twitter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwitterConfig {

    @Value("${kubeek.app.twitter.consumerkey}")
    private String consumerKey;

    @Value("${kubeek.app.twitter.consumersecret}")
    private String consumerSecret;

    @Value("${kubeek.app.twitter.accesstoken}")
    private String accessToken;

    @Value("${kubeek.app.twitter.accesstokensecret}")
    private String accessTokenSecret;

    @Value("${kubeek.app.twitter.appcolor:00FF00}")
    private String appColor;

    @Value("${kubeek.app.twitter.speed:15000}")
    private int speed;

    @Value("${kubeek.app.twitter.defaultfont:6x10}")
    private String defaultFont;


    @Bean
    public TwitterParams getTwitterParams(){
        return new TwitterParams(consumerKey,consumerSecret,accessToken,accessTokenSecret, appColor, speed, defaultFont);
    }
}
