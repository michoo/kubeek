package com.kubeek.device.ksimulatorscreendevice;

import com.kubeek.sdk.device.KScreenDevice;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class KSimulatorScreen implements KScreenDevice {


    public KSimulatorScreen(int rows, int chain){
        this.init(rows,chain);
    }

    @Override
    public void init(int rows, int chain) {
        log.trace("init rows: " + rows + " chain: " + chain);
    }

    @Override
    public void close() {
        log.trace("close");
    }

    @Override
    public void plot(int x, int y, int red, int green, int blue) {
        log.trace("plot x: " + x + ", y: " + y + ", red: " + red + ", green: " + green + ", blue: " + blue);
    }

    @Override
    public void plotAll(int red, int green, int blue) {
        log.trace("plotAll red: " + red + ", green: " + green + ", blue: " + blue);
    }

    @Override
    public void _plot(int x, int y, int red, int green, int blue) {
        log.trace("_plot x: " + x + ", y: " + y + ", red: " + red + ", green: " + green + ", blue: " + blue);
    }

    @Override
    public void update() {
        log.trace("update");
    }

    @Override
    public void clear() {
        log.trace("clear");
    }

    @Override
    public void fade(int pwm, int delayTime) {
        log.trace("fade pwm: " + pwm + ", delayTime: " + delayTime);
    }

    @Override
    public void setBright() {
        log.trace("setBright");
    }

    @Override
    public void plotLine(int x0, int y0, int x1, int y1, int red, int green, int blue) {
        log.trace("plotLine x0: " + x0 + ", y0: " + y0 + "x1: " + x1 + ", y1: " + y1 + ", red: " + red + ", green: " + green + ", blue: " + blue );
    }

    @Override
    public void plotCircle(int xm, int ym, int r, int red, int green, int blue) {
        log.trace("plotCircle xm: " + xm + ", ym: " + ym + ", r: " + r + ", red: " + red + ", green: " + green + ", blue: " + blue);
    }

    @Override
    public void plotEllipseRect(int x0, int y0, int x1, int y1, int red, int green, int blue) {
        log.trace("plotEllipseRect x0: " + x0 + ", y0: " + y0 + "x1: " + x1 + ", y1: " + y1 + ", red: " + red + ", green: " + green + ", blue: " + blue );
    }

    @Override
    public void plotBasicBezier(int x0, int y0, int x1, int y1, int x2, int y2, int red, int green, int blue) {
        log.trace("plotBasicBezier x0: " + x0 + ", y0: " + y0 + "x1: " + x1 + ", y1: " + y1 +  "x2: " + x2 + ", y2: " + y2 + ", red: " + red + ", green: " + green + ", blue: " + blue );
    }

    @Override
    public void _putChar(int x, int y, char c, String fontName, int red, int green, int blue) {
        log.trace("_putChar x: " + x + ", y: " + y + ", c: " + c + ", fontName: " + fontName + ", red: " + red + ", green: " + green + ", blue: " + blue);
    }

    @Override
    public void putChar(int x, int y, char c, String fontName, int red, int green, int blue) {
        log.trace("putChar x: " + x + ", y: " + y + ", c: " + c + ", fontName: " + fontName + ", red: " + red + ", green: " + green + ", blue: " + blue);
    }

    @Override
    public void scrollText(int y, String message, String fontName, int delayTime, int red, int green, int blue) {
        log.trace("scrollText y: " + y + ", message: " + message + ", fontName: " + fontName + ", delayTime: " + delayTime + ", red: " + red + ", green: " + green + ", blue: " + blue);
    }

    @Override
    public void scrollPPM(String filename, int scrollJump, int runtimeSeconds, int scrollMs) {
        log.trace("scrollPPM filename: " + filename + ", scrollJump: " + scrollJump + ", runtimeSeconds: " + runtimeSeconds + ", scrollMs: " + scrollMs);
    }

    protected void finalize() throws Throwable {
        log.trace("finalize");
        try {
            close();        // delete lib reference
        } finally {
            super.finalize();
        }
    }
}
