package com.kubeek.app.example;

import com.kubeek.utils.Utils;

import java.awt.*;

public class ExampleParams {

    private Color appColor;
    private int speed;
    private String defaultFont;

    public ExampleParams(String appColor, int speed, String defaultFont) {
        this.appColor= Utils.hex2Rgb(appColor);
        this.speed = speed;
        this.defaultFont = defaultFont;
    }

    public Color getAppColor(){
        return appColor;
    }

    public int getSpeed(){
        return speed;
    }

    public String getDefaultFont() {
        return defaultFont;
    }
}
