package com.kubeek.service.rest.domain;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AllDTO  {

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

    public AllDTO(int redColor, int greenColor, int blueColor) {
        this.redColor = redColor;
        this.greenColor = greenColor;
        this.blueColor = blueColor;
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
        return "AllDTO{" +
            "redColor=" + redColor +
            ", greenColor=" + greenColor +
            ", blueColor=" + blueColor +
            '}';
    }
}
