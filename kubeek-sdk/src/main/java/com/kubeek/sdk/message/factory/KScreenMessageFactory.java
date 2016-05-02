/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kubeek.sdk.message.factory;


import com.kubeek.sdk.message.screen.*;

import java.awt.*;

public abstract class KScreenMessageFactory {

    private Color defaultColor = Color.GREEN;
    private int defaultDelayTime=15000;
    private String defaultFontName="";


    public String getDefaultFontName() {
        return defaultFontName;
    }

    public void setDefaultFontName(String defaultFontName) {
        this.defaultFontName = defaultFontName;
    }

    public int getDefaultDelayTime() {
        return defaultDelayTime;
    }

    public void setDefaultDelayTime(int defaultDelayTime) {
        this.defaultDelayTime = defaultDelayTime;
    }

    public Color getDefaultColor() {
        return defaultColor;
    }

    public void setDefaultColor(Color defaultColor) {
        this.defaultColor = defaultColor;
    }

    //Graphical functions
    // print a point
    public abstract KScreenMessagePlotPoint getMessagePlotPoint(int x, int y, Color color);

    // print a point default color
    public abstract KScreenMessagePlotPoint getMessagePlotPoint(int x, int y);

    //print all screen
    public abstract KScreenMessagePlotAllPoints getMessagePlotAllPoints(Color color);

    //print all screen default color
    public abstract KScreenMessagePlotAllPoints getMessagePlotAllPoints();

    //print mem point
    public abstract KScreenMessageMemPoint getMessageMemPoint(int x, int y, Color color);

    //print mem point default color
    public abstract KScreenMessageMemPoint getMessageMemPoint(int x, int y);

    // update refresh screen
    public abstract KScreenMessageUpdate getMessageUpdate();

    //other functions
    // Clear Screen
    public abstract KScreenMessageClear getMessageClear();


    // Fade Brightness
    public abstract KScreenMessageFade getMessageFade(int pwm, int delayTime);

    // Fade Brightness default delaytime
    public abstract KScreenMessageFade getMessageFade(int pwm);


    // Set Bright to max
    public abstract KScreenMessageSetBright getMessageSetBright();

    // print a line
    public abstract KScreenMessagePlotLine getMessagePlotLine(int x0, int y0, int x1, int y1, Color color);

    // print a line default color
    public abstract KScreenMessagePlotLine getMessagePlotLine(int x0, int y0, int x1, int y1);


    // print a Circle
    public abstract KScreenMessagePlotCircle getMessagePlotCircle(int xm, int ym, int r, Color color);

    // print a Circle default color
    public abstract KScreenMessagePlotCircle getMessagePlotCircle(int xm, int ym, int r);


    // print an Ellipse
    public abstract KScreenMessagePlotEllipse getMessagePlotEllipse(int x0, int y0, int x1, int y1, Color color);

    // print an Ellipse default color
    public abstract KScreenMessagePlotEllipse getMessagePlotEllipse(int x0, int y0, int x1, int y1);


    //print a Bezier curb
    public abstract KScreenMessagePlotBezier getMessagePlotBezier(int x0, int y0, int x1, int y1, int x2, int y2, Color color);

    //print a Bezier curb default color
    public abstract KScreenMessagePlotBezier getMessagePlotBezier(int x0, int y0, int x1, int y1, int x2, int y2);

    //Letters functions
    //printMem char
    public abstract KScreenMessageMemChar getMessageMemChar(int x, int y, char c, String fontName, Color color);

    //printMem char default color
    public abstract KScreenMessageMemChar getMessageMemChar(int x, int y, char c, String fontName);

    //printMem char default font
    public abstract KScreenMessageMemChar getMessageMemChar(int x, int y, char c, Color color);

    //printMem char default font and color
    public abstract KScreenMessageMemChar getMessageMemChar(int x, int y, char c);

    // Print a char
    public abstract KScreenMessagePlotChar getMessagePlotChar(int x, int y, char c, String fontName, Color color);

    //print char default color
    public abstract KScreenMessagePlotChar getMessagePlotChar(int x, int y, char c, String fontName);

    //print char default font
    public abstract KScreenMessagePlotChar getMessagePlotChar(int x, int y, char c, Color color);

    //print char default font and color
    public abstract KScreenMessagePlotChar getMessagePlotChar(int x, int y, char c);

    // Scroll simple
    public abstract KScreenMessageScrollText getMessageScrollText(int y, String message, String fontName, int delaytime, Color color);

    // Scroll simple default color and delay
    public abstract KScreenMessageScrollText getMessageScrollText(int y, String message, String fontName);

    // Scroll simple default font and delay
    public abstract KScreenMessageScrollText getMessageScrollText(int y, String message, Color color);

    // Scroll simple default font and color and delay
    public abstract KScreenMessageScrollText getMessageScrollText(int y, String message);


    //scroll PPM image
    public abstract KScreenMessageScrollPPM getMessageScrollPpm(String filename, int scrollJump, int runtimeSeconds, int scrollMs);



}
