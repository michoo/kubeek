#ifndef _K3_SCREEN_JNI_DRIVER_IMPL_H
#define _K3_SCREEN_JNI_DRIVER_IMPL_H
#include "led-matrix.h"
#include "graphics.h"
#include <string>

using rgb_matrix::GPIO;
using rgb_matrix::RGBMatrix;
using rgb_matrix::Canvas;

class K3ScreenJNIDriverImpl {

    private:
    //vars
    GPIO io;
    RGBMatrix *canvas;

    public:

    K3ScreenJNIDriverImpl(int rows = 16, int chain =  2);
    virtual ~K3ScreenJNIDriverImpl(void);

    //basic functions
    void clear(void);
    void fade(int intensity, int delaytime = 0);
    void setBright(void);

    //Graphical functions
    void plot(int x, int y, int red, int green, int blue);
    void _plot(int x, int y, int red, int green, int blue);//same as plot but without update
    void plotAll(int red, int green, int blue);
    void update(void);//if you use _plot or _putchar

    //Geometrical functions
    void plotLine(int x0, int y0, int x1, int y1, int red, int green, int blue);
    void plotCircle(int xm, int ym, int r, int red, int green, int blue);
    void plotEllipseRect(int x0, int y0, int x1, int y1, int red, int green, int blue);
    void plotBasicBezier(int x0, int y0, int x1, int y1, int x2, int y2, int red, int green, int blue);

    //Letters functions
    void _putChar(int x, int y, char c, const char * fontname, int red, int green, int blue);
    void putChar(int x, int y, char c, const char * fontname, int red, int green, int blue);
    void scrollText(int y, const char * message, const char * fontname, int delaytime, int red, int green, int blue);
    void scrollPPM(const char * filename, int scroll_jump, int runtime_seconds, int scroll_ms);

};

#endif