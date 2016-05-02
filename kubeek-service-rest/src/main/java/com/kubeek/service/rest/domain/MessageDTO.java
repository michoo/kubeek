package com.kubeek.service.rest.domain;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class MessageDTO {

    @NotNull
    private int xPosition;

    @NotNull
    private int yPosition;

    @NotNull
    private int delayTime;

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

    @NotNull
    @Size(min=1, max = 1000)
    private String message;

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

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "xPosition=" + xPosition +
                ", yPosition=" + yPosition +
                ", delayTime=" + delayTime +
                ", redColor=" + redColor +
                ", greenColor=" + greenColor +
                ", blueColor=" + blueColor +
                ", message='" + message + '\'' +
                '}';
    }
}
