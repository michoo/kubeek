package com.kubeek.message.screen;

import com.kubeek.sdk.message.screen.KScreenMessageMemPoint;

import java.awt.*;

public class ScreenMessageMemPointSimple extends KScreenMessageMemPoint {
	private int y;
	private int x;
	private Color color;


	public ScreenMessageMemPointSimple(int x, int y, Color color) {
		this.x=x;
		this.y=y;
		this.color=color;

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
	public int getX() {
		return x;
	}

	@Override
	public void setX(int x) {
		this.x = x;
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
