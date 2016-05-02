package com.kubeek.message.screen;

import com.kubeek.sdk.message.screen.KScreenMessagePlotCircle;

import java.awt.*;

public class ScreenMessagePlotCircleSimple extends KScreenMessagePlotCircle {
	private int ym;
	private int xm;
	private int r;
	private Color color;


	public ScreenMessagePlotCircleSimple(int xm, int ym, int r, Color color) {
		this.xm=xm;
		this.ym=ym;
		this.r=r;
		this.color=color;

	}

	@Override
	public int getYm() {
		return ym;
	}

	@Override
	public void setYm(int ym) {
		this.ym = ym;
	}

	@Override
	public int getXm() {
		return xm;
	}

	@Override
	public void setXm(int xm) {
		this.xm = xm;
	}

	@Override
	public int getR() {
		return r;
	}

	@Override
	public void setR(int r) {
		this.r = r;
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
