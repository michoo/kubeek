package com.kubeek.service.rest.domain;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class BezierDTO {
    @NotNull
	private int y0;
    @NotNull
	private int x0;
    @NotNull
    private int y1;
    @NotNull
    private int x1;
    @NotNull
    private int y2;
    @NotNull
    private int x2;

    @NotNull
    @Max(value = 255)
    @Min(value = 0)
    private int redColor;

    @NotNull
    @Max(value = 255)
    @Min(value = 0)
    private int greenColor;

    @NotNull
    @Max(value = 255)
    @Min(value = 0)
    private int blueColor;


    public BezierDTO(int y0, int x0, int y1, int x1, int y2, int x2, int redColor, int greenColor, int blueColor) {
        this.y0 = y0;
        this.x0 = x0;
        this.y1 = y1;
        this.x1 = x1;
        this.y2 = y2;
        this.x2 = x2;
        this.redColor = redColor;
        this.greenColor = greenColor;
        this.blueColor = blueColor;
    }

    public int getY0() {
		return y0;
	}


	public void setY0(int y0) {
		this.y0 = y0;
	}


	public int getX0() {
		return x0;
	}


	public void setX0(int x0) {
		this.x0 = x0;
	}


	public int getY1() {
		return y1;
	}


	public void setY1(int y1) {
		this.y1 = y1;
	}


	public int getX1() {
		return x1;
	}


	public void setX1(int x1) {
		this.x1 = x1;
	}


	public int getY2() {
		return y2;
	}


	public void setY2(int y2) {
		this.y2 = y2;
	}


	public int getX2() {
		return x2;
	}


	public void setX2(int x2) {
		this.x2 = x2;
	}

    public int getRedColor() {
        return redColor;
    }

    public void setRedColor(int redColor) {
        this.redColor = redColor;
    }

    public int getGreenColor() {
        return greenColor;
    }

    public void setGreenColor(int greenColor) {
        this.greenColor = greenColor;
    }

    public int getBlueColor() {
        return blueColor;
    }

    public void setBlueColor(int blueColor) {
        this.blueColor = blueColor;
    }

    @Override
    public String toString() {
        return "BezierDTO{" +
            "y0=" + y0 +
            ", x0=" + x0 +
            ", y1=" + y1 +
            ", x1=" + x1 +
            ", y2=" + y2 +
            ", x2=" + x2 +
            ", redColor=" + redColor +
            ", greenColor=" + greenColor +
            ", blueColor=" + blueColor +
            '}';
    }
}
