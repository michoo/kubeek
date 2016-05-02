package com.kubeek.message.screen;

import com.kubeek.sdk.message.screen.KScreenMessageMemChar;

import java.awt.*;

public class ScreenMessageMemCharSimple extends KScreenMessageMemChar {
	private int x;
	private int y;
	private Color color;
	private char character;
	private String fontName;

	public ScreenMessageMemCharSimple(int x, int y, char c, String fontName, Color color) {
		this.x=x;
		this.y=y;
		this.character=c;
		this.fontName=fontName;
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
	public char getCharacter() {
		return character;
	}

	@Override
	public void setCharacter(char character) {
		this.character = character;
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
