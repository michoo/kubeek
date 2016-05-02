package com.kubeek.app.calculous;

import com.kubeek.utils.Utils;

import java.awt.*;


public class CalculousParams {

    private Color appColor;
    private int speed;
    private String defaultFont;
    private int difficulty;
    private int digits;


    public CalculousParams(String appColor, int speed, String defaultFont, int difficulty, int digits) {
        this.appColor= Utils.hex2Rgb(appColor);
        this.speed = speed;
        this.defaultFont = defaultFont;
        this.difficulty = difficulty;
        this.digits = digits;
    }

    public Color getAppColor() {
        return appColor;
    }

    public int getSpeed() {
        return speed;
    }

    public String getDefaultFont() {
        return defaultFont;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getDigits() {
        return digits;
    }

}
