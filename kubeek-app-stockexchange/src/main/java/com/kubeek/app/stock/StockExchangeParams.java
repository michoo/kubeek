package com.kubeek.app.stock;

import com.kubeek.utils.Utils;

import java.awt.*;

public class StockExchangeParams {

    private Color appColor;
    private int speed;
    private String defaultFont;


    public StockExchangeParams(String appColor, int speed, String defautlFont) {
        this.appColor= Utils.hex2Rgb(appColor);
        this.speed = speed;
        this.defaultFont = defautlFont;
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
