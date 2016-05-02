package com.kubeek.service.rest.domain;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PointDTO {

    @NotNull
    private int xPosition;

    @NotNull
    private int yPosition;

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



    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
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
        return "PointDTO{" +
            "xPosition=" + xPosition +
            ", yPosition=" + yPosition +
            ", redColor=" + redColor +
            ", greenColor=" + greenColor +
            ", blueColor=" + blueColor +
            '}';
    }
}
