package com.kubeek.message.screen;

import com.kubeek.sdk.message.screen.KScreenMessagePlotEllipse;

import java.awt.*;

public class ScreenMessagePlotEllipseSimple extends KScreenMessagePlotEllipse {
	private int y0;
	private int x0;
	private int y1;
	private int x1;
	private Color color;


	public ScreenMessagePlotEllipseSimple(int x0, int y0, int x1, int y1, Color color) {
        this.x0=x0;
        this.y0=y0;
        this.x1=x1;
        this.y1=y1;
        this.color=color;

	}

    @Override
    public int getY0() {
        return y0;
    }

    @Override
    public void setY0(int y0) {
        this.y0 = y0;
    }

    @Override
    public int getX0() {
        return x0;
    }

    @Override
    public void setX0(int x0) {
        this.x0 = x0;
    }

    @Override
    public int getY1() {
        return y1;
    }

    @Override
    public void setY1(int y1) {
        this.y1 = y1;
    }

    @Override
    public int getX1() {
        return x1;
    }

    @Override
    public void setX1(int x1) {
        this.x1 = x1;
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
