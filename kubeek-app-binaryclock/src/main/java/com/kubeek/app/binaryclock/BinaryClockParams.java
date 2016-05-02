package com.kubeek.app.binaryclock;

import com.kubeek.utils.Utils;

import java.awt.*;

public class BinaryClockParams {

    private Color upColor;
    private Color downColor;
    private int speed;

    public BinaryClockParams(String upColor, String downColor, int speed) {
        this.upColor= Utils.hex2Rgb(upColor);
        this.downColor= Utils.hex2Rgb(downColor);
        this.speed = speed;
    }

    public Color getUpColor(){
        return upColor;
    }


    public Color getDownColor(){
        return downColor;
    }

    public int getSpeed(){
        return speed;
    }



}
