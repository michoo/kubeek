#include "includes/K2ScreenJNIDriverImpl.h"


#include <iostream>
#include <unistd.h>

//using rgb_matrix::Font;
using namespace rgb_matrix;
using namespace std;



K2ScreenJNIDriverImpl::K2ScreenJNIDriverImpl(int rows, int chain){
//    cout << "K2ScreenJNIDriverImpl::K2ScreenJNIDriverImpl()" << endl;
    
    //Init  of object Kubeek and private value
    _pinDATA = 0; //O gris 
    _pinWR = 1; //1 violet
    _pinCS = 2; //2 bleu
    _pinCLK = 3; //3 noir

    if (wiringPiSetup() == -1) {
        exit(1);
    }

    _intensity = 15;
    _waintingLoop = 500;

    for (int i = 0; i < 64; i++) {
        for (int j = 0; j < 8; j++) {
            shadowram[i][j] = 0;
            shadowramBefore[i][j] = 0;
        }

    }

    begin();
    
}

K2ScreenJNIDriverImpl::~K2ScreenJNIDriverImpl(void){
//    cout << "K2ScreenJNIDriverImpl::~K2ScreenJNIDriverImpl" << endl;
        cout << "Bye Bye..." << endl;

}

void K2ScreenJNIDriverImpl::begin(void){
    pinMode(_pinCS, OUTPUT);
    digitalWrite(_pinCS, HIGH);
    pinMode(_pinWR, OUTPUT);
    pinMode(_pinDATA, OUTPUT);
    pinMode(_pinCLK, OUTPUT);
    pinMode(_pinDEBUG, OUTPUT);


    for (int j = 1; j <= CHIP_MAX; j++) {
        sendCmd(j, HT1632_CMD_SYSDIS);
        sendCmd(j, HT1632_CMD_COMS00);
        sendCmd(j, HT1632_CMD_MSTMD);
        sendCmd(j, HT1632_CMD_RCCLK);
        sendCmd(j, HT1632_CMD_SYSON);
        sendCmd(j, HT1632_CMD_LEDON);
        sendCmd(j, HT1632_CMD_PWM + 15);

        for (int i = 0; i < 96; i++) {
            sendData(j, i, 0); // clear the display!
        }
    }
}

void K2ScreenJNIDriverImpl::outputCLK_Pulse(void) {
    digitalWrite(_pinCLK, HIGH);
    runWaintingLoop(_waintingLoop);
    digitalWrite(_pinCLK, LOW);
    runWaintingLoop(_waintingLoop);

}

void K2ScreenJNIDriverImpl::outputChipSelect(unsigned char x) {
    digitalWrite(_pinCS, (x == 1 ? HIGH : LOW));

}

void K2ScreenJNIDriverImpl::chipSelect(int select) {

    if (select < 0) //Enable all HT1632Cs
    {
        outputChipSelect(0);

        for (int i = 0; i < CHIP_MAX; i++) {
            outputCLK_Pulse();
        }
    } else if (select == 0) //Disable all HT1632Cs
    {
        outputChipSelect(1);

        for (int i = 0; i < CHIP_MAX; i++) {
            outputCLK_Pulse();
        }
    } else {
        outputChipSelect(1);

        for (char i = 0; i < CHIP_MAX; i++) {
            outputCLK_Pulse();
        }
        outputChipSelect(0);

        outputCLK_Pulse();
        outputChipSelect(1);

        for (int i = 1; i < select; i++) {
            outputCLK_Pulse();
        }
    }
}

void K2ScreenJNIDriverImpl::writeBits(int bits, int firstbit) {
    while (firstbit) {
        digitalWrite(_pinWR, LOW);
        if (bits & firstbit) {
            digitalWrite(_pinDATA, HIGH);
        }
        else {
            digitalWrite(_pinDATA, LOW);
        }
        digitalWrite(_pinWR, HIGH);
        firstbit >>= 1;
    }
}

void K2ScreenJNIDriverImpl::sendCmd(int chipNo, int command) {
    chipSelect(chipNo);
    writeBits(HT1632_ID_CMD, 1 << 2);
    writeBits(command, 1 << 7);
    writeBits(0, 1);
    chipSelect(0);
}

void K2ScreenJNIDriverImpl::sendData(int chipNo, int address, int data) {
    chipSelect(chipNo);
    writeBits(HT1632_ID_WR, 1 << 2); // send ID: WRITE to RAM
    writeBits(address, 1 << 6); // Send address

    writeBits(data, 1 << 3); // send 4 bits of data
    chipSelect(0);
}

int K2ScreenJNIDriverImpl::xyToIndex(int x, int y) {
    int addr;

    x = x % 16;
    y = y % 8;
    addr = (x << 1) + (y >> 2);

    return addr;
}

int K2ScreenJNIDriverImpl::getPixel(int x, int y) {
    int addr, bitval, nChip;

    if (x >= 32) {
        nChip = 3 + x / 16 + (y > 7 ? 2 : 0);
    }
    else {
        nChip = 1 + x / 16 + (y > 7 ? 2 : 0);
    }

    addr = xyToIndex(x, y);
    bitval = calcBit(y);

    if ((shadowram[addr][nChip - 1] & bitval) && (shadowram[addr + 32][nChip - 1] & bitval)) {
        return ORANGE;
    }
    else if (shadowram[addr][nChip - 1] & bitval) {
        return GREEN;
    }
    else if (shadowram[addr + 32][nChip - 1] & bitval) {
        return RED;
    }
    else {
        return 0;
    }
}
int K2ScreenJNIDriverImpl::getColor(int red, int green, int blue) {
int color=0;
    if(green>=1 ){
        color++;
    }
    if(red>=1){
        color++;
        color++;
    }
return color;
}


//**************************************************************************************************
//  BASIC FUNCTIONS
//
//**************************************************************************************************

void K2ScreenJNIDriverImpl::clear(void) {
//    cout << "K2ScreenJNIDriverImpl::clear" << endl;

    for (int j = 1; j <= CHIP_MAX; j++) {
        for (int i = 0; i < 96; i++) {
            sendData(j, i, 0); // clear the display!
        }
    }

    for (int i = 0; i < 64; i++) {
        for (int j = 0; j < 8; j++) {
            shadowram[i][j] = 0;
            shadowramBefore[i][j] = 0;
        }

    }
    setBright();
    
//    cout << "K2ScreenJNIDriverImpl::clear - cleared" << endl;
}

void K2ScreenJNIDriverImpl::fade(int intensity, int delayTime) {
//    cout << "K2ScreenJNIDriverImpl::fade" << endl;
    

    char delta = 0;
    if (intensity < _intensity && intensity > 0) {
        delta = _intensity - intensity;
        for (char i = 0; i < delta; i++) {

            for (int j = 1; j <= CHIP_MAX; j++) {
                sendCmd(j, HT1632_CMD_PWM + _intensity - i); //send intensity commands using CS0 for display 0
            }
            usleep(delayTime);
        }
        _intensity = intensity;
    } else if (intensity >= _intensity && intensity <= 15) {
        delta = intensity - _intensity;
        for (char i = 0; i < intensity; i++) {

            for (int j = 1; j <= CHIP_MAX; j++) {
                sendCmd(j, HT1632_CMD_PWM + _intensity + i); //send intensity commands using CS0 for display 0
            }
            usleep(delayTime);
        }
        _intensity = intensity;
    } else {
        setBright();
        _intensity = 15;
    }
    
}

void K2ScreenJNIDriverImpl::runWaintingLoop(int waitingLoop) {
    //cout << "K2ScreenJNIDriverImpl::runWaintingLoop" << endl;
    for (int k = 1; k <= waitingLoop; k++) {
        _nop();
    }
}

void K2ScreenJNIDriverImpl::setBright(void) {
//    cout << "K2ScreenJNIDriverImpl::setBright" << endl;
    for (int j = 1; j <= CHIP_MAX; j++) {
        sendCmd(j, HT1632_CMD_PWM + 15);
    }
}

//**************************************************************************************************
//  GRAPHICAL COMMANDS
//
//**************************************************************************************************

void K2ScreenJNIDriverImpl::plot(int x, int y, int red, int green, int blue) {
    //cout << "K2ScreenJNIDriverImpl::plot" << endl;
    int color = getColor(red, green, blue);
    
    int nChip, addr, bitval;

    if (x < 0 || x > X_MAX || y < 0 || y > Y_MAX)
        return;

    if (color != BLACK && color != GREEN && color != RED && color != ORANGE)
        return;

    if (x >= 32) {
        nChip = 3 + x / 16 + (y > 7 ? 2 : 0);
    }
    else {
        nChip = 1 + x / 16 + (y > 7 ? 2 : 0);
    }

    addr = xyToIndex(x, y);
    bitval = calcBit(y);


    switch (color) {
        case BLACK:
            if (getPixel(x, y) != BLACK) { // compare with memory to only set if pixel is other color
                // clear the bit in both planes;
                shadowram[addr][nChip - 1] &= ~bitval;
                sendData(nChip, addr, shadowram[addr][nChip - 1]);
                shadowram[addr + 32][nChip - 1] &= ~bitval;
                sendData(nChip, addr + 32, shadowram[addr + 32][nChip - 1]);
            }
            break;
        case GREEN:
            if (getPixel(x, y) != GREEN) { // compare with memory to only set if pixel is other color
                // set the bit in the green plane and clear the bit in the red plane;
                shadowram[addr][nChip - 1] |= bitval;
                sendData(nChip, addr, shadowram[addr][nChip - 1]);
                shadowram[addr + 32][nChip - 1] &= ~bitval;
                sendData(nChip, addr + 32, shadowram[addr + 32][nChip - 1]);
            }
            break;
        case RED:
            if (getPixel(x, y) != RED) { // compare with memory to only set if pixel is other color
                // clear the bit in green plane and set the bit in the red plane;
                shadowram[addr][nChip - 1] &= ~bitval;
                sendData(nChip, addr, shadowram[addr][nChip - 1]);
                shadowram[addr + 32][nChip - 1] |= bitval;
                sendData(nChip, addr + 32, shadowram[addr + 32][nChip - 1]);
            }
            break;
        case ORANGE:
            if (getPixel(x, y) != ORANGE) { // compare with memory to only set if pixel is other color
                // set the bit in both the green and red planes;
                shadowram[addr][nChip - 1] |= bitval;
                sendData(nChip, addr, shadowram[addr][nChip - 1]);
                shadowram[addr + 32][nChip - 1] |= bitval;
                sendData(nChip, addr + 32, shadowram[addr + 32][nChip - 1]);
            }
            break;
    }

}

void K2ScreenJNIDriverImpl::_plot(int x, int y, int red, int green, int blue) {
    //cout << "K2ScreenJNIDriverImpl::_plot" << endl;
    int nChip, addr, bitval;
    int color = getColor(red, green, blue);

    if (x < 0 || x > X_MAX || y < 0 || y > Y_MAX)
        return;

    if (color != BLACK && color != GREEN && color != RED && color != ORANGE)
        return;

    if (x >= 32) {
        nChip = 3 + x / 16 + (y > 7 ? 2 : 0);
    }
    else {
        nChip = 1 + x / 16 + (y > 7 ? 2 : 0);
    }

    addr = xyToIndex(x, y);
    bitval = calcBit(y);


    switch (color) {
        case BLACK:
            if (getPixel(x, y) != BLACK) { // compare with memory to only set if pixel is other color
                // clear the bit in both planes;
                shadowram[addr][nChip - 1] &= ~bitval;

                shadowram[addr + 32][nChip - 1] &= ~bitval;

            }
            break;
        case GREEN:
            if (getPixel(x, y) != GREEN) { // compare with memory to only set if pixel is other color
                // set the bit in the green plane and clear the bit in the red plane;
                shadowram[addr][nChip - 1] |= bitval;

                shadowram[addr + 32][nChip - 1] &= ~bitval;

            }
            break;
        case RED:
            if (getPixel(x, y) != RED) { // compare with memory to only set if pixel is other color
                // clear the bit in green plane and set the bit in the red plane;
                shadowram[addr][nChip - 1] &= ~bitval;

                shadowram[addr + 32][nChip - 1] |= bitval;

            }
            break;
        case ORANGE:
            if (getPixel(x, y) != ORANGE) { // compare with memory to only set if pixel is other color
                // set the bit in both the green and red planes;
                shadowram[addr][nChip - 1] |= bitval;

                shadowram[addr + 32][nChip - 1] |= bitval;

            }
            break;
    }

}

void K2ScreenJNIDriverImpl::plotAll(int red, int green, int blue) {
//    cout << "K2ScreenJNIDriverImpl::plotAll" << endl;
    int color = getColor(red, green, blue);
    for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 16; j++) {
                if (getPixel(i, j) != color) {
                    _plot(i,j, red, green, blue);
                }

            }
        }
    
    update();
}

void K2ScreenJNIDriverImpl::update(void) {
    //cout << "K2ScreenJNIDriverImpl::update" << endl;
    for (int i = 0; i < 64; i++) {
        for (int j = 1; j < 8; j++) {
            if (shadowram[i][j - 1] != shadowramBefore[i][j - 1]) {
                sendData(j, i, shadowram[i][j - 1]);
                shadowramBefore[i][j - 1] = shadowram[i][j - 1];

            }
        }
    }
}



//**************************************************************************************************
//  GEOMETRICAL FUNCTIONS
//
//**************************************************************************************************



// fonctions from Bresenham's Algorithm

void K2ScreenJNIDriverImpl::plotLine(int x0, int y0, int x1, int y1, int red, int green, int blue) {
//    cout << "K2ScreenJNIDriverImpl::_putChar" << endl;
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

void K2ScreenJNIDriverImpl::plotCircle(int xm, int ym, int r, int red, int green, int blue) {
//    cout << "K2ScreenJNIDriverImpl::_putChar" << endl;
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

void K2ScreenJNIDriverImpl::plotEllipseRect(int x0, int y0, int x1, int y1, int red, int green, int blue) {
//    cout << "K2ScreenJNIDriverImpl::_putChar" << endl;
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

void K2ScreenJNIDriverImpl::plotBasicBezier(int x0, int y0, int x1, int y1, int x2, int y2, int red, int green, int blue) {
//    cout << "K2ScreenJNIDriverImpl::_putChar" << endl;
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

void K2ScreenJNIDriverImpl::_putChar(int x, int y, char c, const char * fontfilename, int red, int green, int blue) {
//    cout << "K2ScreenJNIDriverImpl::_putChar" << endl;
    
    Color color(red, green,blue);
    
    Font font;
    font.LoadFont(fontfilename);


    font.DrawGlyph(this, x, y, color, c);
    
    

}

void K2ScreenJNIDriverImpl::putChar(int x, int y, char c, const char * fontfilename, int red, int green, int blue) {
//    cout << "K2ScreenJNIDriverImpl::putChar" << endl;
    _putChar(x, y, c, fontfilename, red, green, blue);
    update();
   
}

void K2ScreenJNIDriverImpl::scrollText(int y, const char *message, const char *fontfilename, int delaytime, int red, int green, int blue ) {
//    cout << "K2ScreenJNIDriverImpl::scrollText" << endl;
    Color color(red, green, blue);
    
    
    Font font;
//    cout << "K3ScreenJNIDriverImpl::scrollText - loading font " << fontfilename <<endl;
    font.LoadFont(fontfilename);

    
    ScrollText(this, font, 0, 1+ font.baseline(),delaytime, color, message);

//    cout << "K3ScreenJNIDriverImpl::scrollText - DrawText " << message <<endl;
 
}

void K2ScreenJNIDriverImpl::scrollPPM(const char * filename, int scroll_jump, int runtime_seconds, int scroll_ms){
//    cout << "K2ScreenJNIDriverImpl::scrollPPM" << endl;
    cout << "K2ScreenJNIDriverImpl::scrollPPM - Not available" << endl;
    
}