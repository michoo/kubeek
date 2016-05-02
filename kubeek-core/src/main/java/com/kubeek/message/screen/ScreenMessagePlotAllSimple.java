package com.kubeek.message.screen;

import com.kubeek.sdk.message.screen.KScreenMessagePlotAllPoints;

import java.awt.*;

public class ScreenMessagePlotAllSimple extends KScreenMessagePlotAllPoints {

    private Color color;

    public ScreenMessagePlotAllSimple(Color color){
        this.color=color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }
}
