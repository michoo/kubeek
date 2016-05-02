package com.kubeek.message.screen;

import com.kubeek.sdk.message.screen.KScreenMessagePlotChar;

import java.awt.*;

public class ScreenMessagePlotCharSimple extends KScreenMessagePlotChar {
	private int x;
	private int y;
	private Color color;
	private char c;
	private String fontName;


	public ScreenMessagePlotCharSimple(int x, int y, char c, String fontName, Color color) {
		this.x=x;
		this.y=y;
		this.fontName=fontName;
		this.c=c;
		this.color=color;

	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public char getC() {
		return c;
	}

	@Override
	public void setC(char c) {
		this.c = c;
	}

	@Override
	public String getFontName() {
		return fontName;
	}

	@Override
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}
}
