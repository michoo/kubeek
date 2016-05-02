package com.kubeek.sdk.device;

public interface KScreenDevice {


    void init(int rows, int chain);

    void close();


    //Graphical functions

    void plot(int x, int y, int red, int green, int blue);

    void plotAll(int red, int green, int blue);

    void _plot(int x, int y, int red, int green, int blue);

    void update();

    //other functions
    void clear();

    void fade(int pwm, int delayTime);

    void setBright();

    void plotLine(int x0, int y0, int x1, int y1, int red, int green, int blue);

    void plotCircle(int xm, int ym, int r, int red, int green, int blue);

    void plotEllipseRect(int x0, int y0, int x1, int y1, int red, int green, int blue);

    void plotBasicBezier(int x0, int y0, int x1, int y1, int x2, int y2, int red, int green, int blue);

    //Letters functions
    void _putChar(int x, int y, char c, String fontName, int red, int green, int blue);

    void putChar(int x, int y, char c, String fontName, int red, int green, int blue);

    void scrollText(int y, String message, String fontName, int delayTime, int red, int green, int blue);

    void scrollPPM(String filename, int scrollJump, int runtimeSeconds, int scrollMs);

}
