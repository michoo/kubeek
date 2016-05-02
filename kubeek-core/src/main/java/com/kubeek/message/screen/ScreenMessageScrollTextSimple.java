package com.kubeek.message.screen;

import com.kubeek.sdk.message.screen.KScreenMessageScrollText;

import java.awt.*;

public class ScreenMessageScrollTextSimple extends KScreenMessageScrollText {
	private int y;
	private Color color;
	private String fontName;
	private int delayTime;
	private String message;


	public ScreenMessageScrollTextSimple(int y, String message, String fontName, int delayTime, Color color) {
		this.y=y;
		this.message=message;
		this.fontName=fontName;
		this.delayTime=delayTime;
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
	public Color getColor() {
		return color;
	}

	@Override
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String getFontName() {
		return fontName;
	}

	@Override
	public void setFontName(String fontName) {
		this.fontName = fontName;
	}

	@Override
	public int getDelayTime() {
		return delayTime;
	}

	@Override
	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

	@Override
	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;
	}
}
