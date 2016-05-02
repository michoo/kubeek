package com.kubeek.app.clock;

import com.kubeek.utils.Utils;

import java.awt.*;

public class ClockParams {

    private Color appColor;
    private int speed;
    private String defaultFont;

    public ClockParams(String appColor, int speed, String defaultFont) {
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
