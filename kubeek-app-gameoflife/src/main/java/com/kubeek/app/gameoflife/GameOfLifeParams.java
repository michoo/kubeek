package com.kubeek.app.gameoflife;

import com.kubeek.utils.Utils;
import java.awt.*;

public class GameOfLifeParams {

    private Color appColor;
    private int generation;
    private int delay;

    public GameOfLifeParams(String appColor, int generation, int delay) {
        this.appColor= Utils.hex2Rgb(appColor);
        this.generation=generation;
        this.delay=delay;
    }


    public Color getAppColor() {
        return appColor;
    }

    public int getGeneration() {
        return generation;
    }

    public int getDelay() {
        return delay;
    }
}
