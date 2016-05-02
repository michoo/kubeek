package com.kubeek.service.rest.domain;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CircleDTO {
    @NotNull
	private int ym;

    @NotNull
    private int xm;

    @NotNull
    private int r;

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


    public CircleDTO(int ym, int xm, int r, int redColor, int greenColor, int blueColor) {
        this.ym = ym;
        this.xm = xm;
        this.r = r;
        this.redColor = redColor;
        this.greenColor = greenColor;
        this.blueColor = blueColor;
    }

    public int getYm() {
		return ym;
	}


	public void setYm(int ym) {
		this.ym = ym;
	}


	public int getXm() {
		return xm;
	}


	public void setXm(int xm) {
		this.xm = xm;
	}


	public int getR() {
		return r;
	}


	public void setR(int r) {
		this.r = r;
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
        return "CircleDTO{" +
            "ym=" + ym +
            ", xm=" + xm +
            ", r=" + r +
            ", redColor=" + redColor +
            ", greenColor=" + greenColor +
            ", blueColor=" + blueColor +
            '}';
    }
}
