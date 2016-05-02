#include "includes/K3ScreenJNIDriverImpl.h"
#include "includes/led-matrix.h"
#include "includes/graphics.h"
#include "threaded-canvas-manipulator.h"
#include "ImageScroller.h"

#include <iostream>
#include <unistd.h>
#include <cmath>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

using namespace rgb_matrix;
using namespace image_scroller;
using namespace std;


K3ScreenJNIDriverImpl::K3ScreenJNIDriverImpl(int rows, int chain){
    //cout << "K3ScreenJNIDriverImpl::K3ScreenJNIDriverImpl()" << endl;
    //cout << "K3ScreenJNIDriverImpl::K3ScreenJNIDriverImpl() - rows/chain " << rows << "/" << chain << endl;
    if(!io.Init()){
        cout << "K3ScreenJNIDriverImpl::K3ScreenJNIDriverImpl() - need to be root..."<< endl;
    }
    //cout << "K3ScreenJNIDriverImpl::K3ScreenJNIDriverImpl() - Io address " << &io << endl;

    canvas = new RGBMatrix(&io, rows, chain);
    //cout << "K3ScreenJNIDriverImpl::K3ScreenJNIDriverImpl() - RGBMatrix address " << &canvas << endl;
}

K3ScreenJNIDriverImpl::~K3ScreenJNIDriverImpl(void){
    //cout << "K3ScreenJNIDriverImpl::~K3ScreenJNIDriverImpl" << endl;
    
    //cout << "K3ScreenJNIDriverImpl::~K3ScreenJNIDriverImpl() - RGBMatrix address" << &canvas << endl;
    delete canvas;
    
    //cout << "K3ScreenJNIDriverImpl::~K3ScreenJNIDriverImpl() - Io address" << &io << endl;
    cout << "Bye Bye..." << endl;
}

//**************************************************************************************************
//  BASIC FUNCTIONS
//
//**************************************************************************************************

void K3ScreenJNIDriverImpl::clear(void) {
    //cout << "K3ScreenJNIDriverImpl::clear" << endl;

    canvas->Clear();
    
    //cout << "K3ScreenJNIDriverImpl::clear - cleared" << endl;
}

void K3ScreenJNIDriverImpl::fade(int intensity, int delaytime) {
    //cout << "K3ScreenJNIDriverImpl::fade" << endl;
    int pwm = canvas->pwmbits();
    if(intensity>pwm && intensity<=11){
        for(int i = pwm; i<intensity;i++){
           canvas->SetPWMBits(i);
           usleep(delaytime); 
        }
    } else if(intensity<pwm && intensity>=1){
        for(int i = pwm; i>intensity;i--){
           canvas->SetPWMBits(i);
           usleep(delaytime); 
        }
    }

}

void K3ScreenJNIDriverImpl::setBright(void) {
    //cout << "K3ScreenJNIDriverImpl::setBright" << endl;
    canvas->SetPWMBits(10);
}

//**************************************************************************************************
//  GRAPHICAL COMMANDS
//
//**************************************************************************************************

void K3ScreenJNIDriverImpl::plot(int x, int y, int red, int green, int blue) {
    //cout << "K3ScreenJNIDriverImpl::plot" << endl;

    //cout << "K3ScreenJNIDriverImpl::plot - Io address " << &io << endl;

    //cout << "K3ScreenJNIDriverImpl::plot - RGB Matrix address " << &canvas << endl;
    canvas->SetPixel(x, y, red, green, blue);

}

void K3ScreenJNIDriverImpl::_plot(int x, int y, int red, int green, int blue) {
    //cout << "K3ScreenJNIDriverImpl::_plot" << endl;
    canvas->SetPixel(x, y, red, green, blue);

}

void K3ScreenJNIDriverImpl::plotAll(int red, int green, int blue) {
    //cout << "K3ScreenJNIDriverImpl::plotAll" << endl;
    for(int i =0;i<canvas->height();i++){
        for(int j = 0; j < canvas->width();j++){
            plot(i,j,red,green,blue);
        }
    }
}

void K3ScreenJNIDriverImpl::update(void) {
    //cout << "K3ScreenJNIDriverImpl::update" << endl;

}



//**************************************************************************************************
//  GEOMETRICAL FUNCTIONS
//
//**************************************************************************************************



// fonctions from Bresenham's Algorithm

void K3ScreenJNIDriverImpl::plotLine(int x0, int y0, int x1, int y1, int red, int green, int blue) {
    //cout << "K3ScreenJNIDriverImpl::_putChar" << endl;
    int dx = abs(x1 - x0);
    int sx = x0 < x1 ? 1 : -1;
    int dy = -abs(y1 - y0);
    int sy = y0 < y1 ? 1 : -1;
    int err = dx + dy;
    int e2; /* error value e_xy */

    for (;;) { /* loop */
        _plot(x0, y0, red, green,blue);
        if (x0 == x1 && y0 == y1) break;
        e2 = 2 * err;
        if (e2 >= dy) {
            err += dy;
            x0 += sx;
        } /* e_xy+e_x > 0 */
        if (e2 <= dx) {
            err += dx;
            y0 += sy;
        } /* e_xy+e_y < 0 */
    }

    update();
}

void K3ScreenJNIDriverImpl::plotCircle(int xm, int ym, int r, int red, int green, int blue) {
    //cout << "K3ScreenJNIDriverImpl::_putChar" << endl;
    int x = -r;
    int y = 0;
    int err = 2 - 2 * r; /* II. Quadrant */
    do {
        _plot(xm - x, ym + y, red, green,blue); /*   I. Quadrant */
        _plot(xm - y, ym - x, red, green,blue); /*  II. Quadrant */
        _plot(xm + x, ym - y, red, green,blue); /* III. Quadrant */
        _plot(xm + y, ym + x, red, green,blue); /*  IV. Quadrant */
        r = err;
        if (r > x) err += ++x * 2 + 1; /* e_xy+e_x > 0 */
        if (r <= y) err += ++y * 2 + 1; /* e_xy+e_y < 0 */
    } while (x < 0);
    update();
}

void K3ScreenJNIDriverImpl::plotEllipseRect(int x0, int y0, int x1, int y1, int red, int green, int blue) {
    //cout << "K3ScreenJNIDriverImpl::_putChar" << endl;
    int a = abs(x1 - x0);
    int b = abs(y1 - y0);
    int b1 = b & 1; /* values of diameter */
    long dx = 4 * (1 - a) * b*b;
    long dy = 4 * (b1 + 1) * a*a; /* error increment */
    long err = dx + dy + b1 * a*a, e2; /* error of 1.step */

    if (x0 > x1) {
        x0 = x1;
        x1 += a;
    } /* if called with swapped points */
    if (y0 > y1) y0 = y1; /* .. exchange them */
    y0 += (b + 1) / 2;
    y1 = y0 - b1; /* starting pixel */
    a *= 8 * a;
    b1 = 8 * b*b;

    do {
        _plot(x1, y0, red, green,blue); /*   I. Quadrant */
        _plot(x0, y0, red, green,blue); /*  II. Quadrant */
        _plot(x0, y1, red, green,blue); /* III. Quadrant */
        _plot(x1, y1, red, green,blue); /*  IV. Quadrant */
        e2 = 2 * err;
        if (e2 >= dx) {
            x0++;
            x1--;
            err += dx += b1;
        } /* x step */
        if (e2 <= dy) {
            y0++;
            y1--;
            err += dy += a;
        } /* y step */
    } while (x0 <= x1);

    while (y0 - y1 < b) { /* too early stop of flat ellipses a=1 */
        _plot(x0 - 1, y0, red, green,blue); /* -> finish tip of ellipse */
        _plot(x1 + 1, y0++, red, green,blue);
        _plot(x0 - 1, y1, red, green,blue);
        _plot(x1 + 1, y1--, red, green,blue);
    }
    update();
}

void K3ScreenJNIDriverImpl::plotBasicBezier(int x0, int y0, int x1, int y1, int x2, int y2, int red, int green, int blue) {
    //cout << "K3ScreenJNIDriverImpl::_putChar" << endl;
    int sx = x0 < x2 ? 1 : -1;
    int sy = y0 < y2 ? 1 : -1; /* step direction */
    int cur = sx * sy * ((x0 - x1)*(y2 - y1)-(x2 - x1)*(y0 - y1)); /* curvature */
    int x = x0 - 2 * x1 + x2;
    int y = y0 - 2 * y1 + y2;
    int xy = 2 * x * y * sx*sy;
    /* compute error increments of P0 */
    long dx = (1 - 2 * abs(x0 - x1)) * y * y + abs(y0 - y1) * xy - 2 * cur * abs(y0 - y2);
    long dy = (1 - 2 * abs(y0 - y1)) * x * x + abs(x0 - x1) * xy + 2 * cur * abs(x0 - x2);
    /* compute error increments of P2 */
    long ex = (1 - 2 * abs(x2 - x1)) * y * y + abs(y2 - y1) * xy + 2 * cur * abs(y0 - y2);
    long ey = (1 - 2 * abs(y2 - y1)) * x * x + abs(x2 - x1) * xy - 2 * cur * abs(x0 - x2);

    /* sign of gradient must not change */
    //if((x0-x1)*(x2-x1) <= 0 && (y0-y1)*(y2-y1) <= 0){
    //  return;
    //}

    if (cur == 0) {
        plotLine(x0, y0, x2, y2, red, green,blue);
        return;
    } /* straight line */

    x *= 2 * x;
    y *= 2 * y;
    if (cur < 0) { /* negated curvature */
        x = -x;
        dx = -dx;
        ex = -ex;
        xy = -xy;
        y = -y;
        dy = -dy;
        ey = -ey;
    }
    /* algorithm fails for almost straight line, check error values */
    if (dx >= -y || dy <= -x || ex <= -y || ey >= -x) {
        plotLine(x0, y0, x1, y1, red, green,blue); /* simple approximation */
        plotLine(x1, y1, x2, y2, red, green,blue);
        return;
    }
    dx -= xy;
    ex = dx + dy;
    dy -= xy; /* error of 1.step */

    for (;;) { /* plot curve */
        _plot(x0, y0, red, green,blue);
        ey = 2 * ex - dy; /* save value for test of y step */
        if (2 * ex >= dx) { /* x step */
            if (x0 == x2) break;
            x0 += sx;
            dy -= xy;
            ex += dx += y;
        }
        if (ey <= 0) { /* y step */
            if (y0 == y2) break;
            y0 += sy;
            dx -= xy;
            ex += dy += x;
        }
    }
    update();
}


//**************************************************************************************************
//  LETTERS FUNCTIONS
//
//**************************************************************************************************

void K3ScreenJNIDriverImpl::_putChar(int x, int y, char c, const char * fontfilename, int red, int green, int blue) {
    //cout << "K3ScreenJNIDriverImpl::_putChar" << endl;
    putChar(x,y,c,fontfilename,red,green,blue);

}

void K3ScreenJNIDriverImpl::putChar(int x, int y, char c, const char * fontfilename, int red, int green, int blue) {
    //cout << "K3ScreenJNIDriverImpl::putChar" << endl;

    Color color(red, green,blue);
    
    Font font;
    font.LoadFont(fontfilename);


    font.DrawGlyph(canvas, x, y, color, c);
}

void K3ScreenJNIDriverImpl::scrollText(int y, const char *message, const char *fontfilename, int delaytime, int red, int green, int blue ) {
    //cout << "K3ScreenJNIDriverImpl::scrollText" << endl;
    Color color(red, green, blue);
    
    //cout << "K3ScreenJNIDriverImpl::scrollText - RGBMatrix address " << &canvas << endl;
    Font font;
    //cout << "K3ScreenJNIDriverImpl::scrollText - loading font " << fontfilename <<endl;
    font.LoadFont(fontfilename);

    
     rgb_matrix::ScrollText(canvas, font, 0, y,delaytime, color, message);
    //cout << "K3ScreenJNIDriverImpl::scrollText - DrawText " << message <<endl;
 
}

void K3ScreenJNIDriverImpl::scrollPPM(const char * filename, int scroll_jump, int runtime_seconds, int scroll_ms){
    //cout << "K3ScreenJNIDriverImpl::scrollPPM" << endl;
    ThreadedCanvasManipulator *image_gen = NULL;
    
    ImageScroller *scroller = new ImageScroller(canvas, scroll_jump == 1 ? 1 : -1, scroll_ms);
      if (!scroller->LoadPPM(filename)){
          //cout << "K3ScreenJNIDriverImpl::scrollPPM - can't open file " << filename << endl;
          return ;
      }
        
    image_gen = scroller;
      
      
    //start the thread.
    image_gen->Start();

    if (runtime_seconds > 0) {
        sleep(runtime_seconds);
    }
  delete image_gen;
}