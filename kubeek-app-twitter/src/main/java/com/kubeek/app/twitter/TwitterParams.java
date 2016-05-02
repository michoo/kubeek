package com.kubeek.app.twitter;

import com.kubeek.utils.Utils;

import java.awt.*;

public class TwitterParams {

    private String consumerKey;

    private String consumerSecret;

    private String accessToken;

    private String accessTokenSecret;

    private Color appColor;

    private int speed;

    private String defaultFont;


    public TwitterParams(String consumerKey, String consumerSecret, String accessToken, String accessTokenSecret , String appColor, int speed, String defaultFont){
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.accessToken = accessToken;
        this.accessTokenSecret = accessTokenSecret;
        this.appColor= Utils.hex2Rgb(appColor);
        this.speed = speed;
        this.defaultFont = defaultFont;
    }


    public String getConsumerKey() {
        return consumerKey;

    }


    public String getConsumerSecret() {
        return consumerSecret;

    }


    public String getAccessToken() {
        return accessToken;

    }


    public String getAccessTokenSecret() {
        return accessTokenSecret;

    }

    public Color getAppColor(){
        return appColor;
    }

    public int getSpeed(){
        return speed;
    }

}
