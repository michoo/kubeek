package com.kubeek.device.k2screendevice;


import com.kubeek.sdk.device.KScreenDevice;
import com.kubeek.utils.LibraryLoader;

public class K2Screen implements KScreenDevice {


    public K2Screen(int rows, int chain){
        this.init(rows,chain);
        System.out.println("K2Screen created rows: "+rows + " chain: "+chain);
    }

    static {
        try {
            LibraryLoader.loadLibrary("K2ScreenJNIDriver");
        } catch (Exception e) {
            System.err.println(e);
            System.exit(1);
        }
    }

    @Override
    public native void init(int rows, int chain);

    @Override
    public native void close();


    //Graphical functions

    @Override
    public native void plot(int x, int y, int red, int green, int blue);

    @Override
    public native void plotAll(int red, int green, int blue);

    @Override
    public native void _plot(int x, int y, int red, int green, int blue);

    @Override
    public native void update();

    //other functions
    @Override
    public native void clear();

    @Override
    public native void fade(int pwm, int delayTime);

    @Override
    public native void setBright();

    @Override
    public native void plotLine(int x0, int y0, int x1, int y1, int red, int green, int blue);

    @Override
    public native void plotCircle(int xm, int ym, int r, int red, int green, int blue);

    @Override
    public native void plotEllipseRect(int x0, int y0, int x1, int y1, int red, int green, int blue);

    @Override
    public native void plotBasicBezier(int x0, int y0, int x1, int y1, int x2, int y2, int red, int green, int blue);


    //Letters functions
    @Override
    public native void _putChar(int x, int y, char c, String fontname, int red, int green, int blue);

    @Override
    public native void putChar(int x, int y, char c, String fontname, int red, int green, int blue);

    @Override
    public native void scrollText(int y, String message, String fontname, int delaytime, int red, int green, int blue);

    @Override
    public native void scrollPPM(String filename, int scrollJump, int runtimeSeconds, int scrollms);

    protected void finalize() throws Throwable {

        try {
            close();        // delete lib reference
        } finally {
            super.finalize();
        }
    }
}

