#ifndef _K2_SCREEN_JNI_DRIVER_IMPL_H
#define _K2_SCREEN_JNI_DRIVER_IMPL_H
#include "wiringPi.h"
#include "graphics.h"

#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <string>
#include <map>

#define HT1632_ID_CMD 4		/* ID = 100 - Commands */
#define HT1632_ID_RD  6		/* ID = 110 - Read RAM */
#define HT1632_ID_WR  5		/* ID = 101 - Write RAM */
#define HT1632_ID_BITS (1<<2)   /* IDs are 3 bits */


#define HT1632_CMD_SYSDIS 0x00	/* CMD= 0000-0000-x Turn off oscil */
#define HT1632_CMD_SYSON  0x01	/* CMD= 0000-0001-x Enable system oscil */
#define HT1632_CMD_LEDOFF 0x02	/* CMD= 0000-0010-x LED duty cycle gen off */
#define HT1632_CMD_LEDON  0x03	/* CMD= 0000-0011-x LEDs ON */
#define HT1632_CMD_BLOFF  0x08	/* CMD= 0000-1000-x Blink ON */
#define HT1632_CMD_BLON   0x09	/* CMD= 0000-1001-x Blink Off */
#define HT1632_CMD_SLVMD  0x10	/* CMD= 0001-00xx-x Slave Mode */
#define HT1632_CMD_MSTMD  0x14	/* CMD= 0001-01xx-x Master Mode */
#define HT1632_CMD_RCCLK  0x18	/* CMD= 0001-10xx-x Use on-chip clock */
#define HT1632_CMD_EXTCLK 0x1C	/* CMD= 0001-11xx-x Use external clock */
#define HT1632_CMD_COMS00 0x20	/* CMD= 0010-ABxx-x commons options */
#define HT1632_CMD_COMS01 0x24	/* CMD= 0010-ABxx-x commons options */
#define HT1632_CMD_COMS10 0x28	/* CMD= 0010-ABxx-x commons options */
#define HT1632_CMD_COMS11 0x2C	/* CMD= 0010-ABxx-x commons options */
#define HT1632_CMD_PWM    0xA0	/* CMD= 101x-PPPP-x PWM duty cycle */
#define HT1632_CMD_BITS (1<<7)


#define Number_of_Displays 1
#define CHIP_MAX 4*Number_of_Displays //Four HT1632Cs on one board
#define X_MAX 32*Number_of_Displays - 1
#define Y_MAX 15


//For letters command
#define NCOLUMNS 8
#define columncountfont 8
#define rowcountfont 8


// possible values for a pixel;
#define BLACK  0
#define GREEN  1
#define RED    2
#define ORANGE 3

//Special functions
#define calcBit(y) (8>>(y&3))
#define _nop() do { __asm__ __volatile__ ("nop"); } while (0)

#define buffer 128	// each tag ID contains 128 bytes


#define DELAYPAUSESCROLL 400


class K2ScreenJNIDriverImpl : public Canvas{

    private:
        //vars
	int _pinWR;
	int _pinDATA;
	int _pinCLK;
	int _pinCS;
	int _pinDEBUG;
	char _intensity;
	int shadowram[64][8];
	int shadowramBefore[64][8];
	int _waintingLoop;

       
    	
        //low level functions
	void outputCLK_Pulse(void);
	void outputChipSelect(unsigned char x);
	void chipSelect(int select);
	void writeBits (int bits, int firstbit);
	void sendCmd (int chipNo, int command);
	void sendData (int chipNo, int address, int data);
	int xyToIndex(int x, int y);
	int getPixel(int x, int y);
        int getColor(int red, int green, int blue);
       

    public:

    K2ScreenJNIDriverImpl(int rows = 16, int chain =  1);
    virtual ~K2ScreenJNIDriverImpl(void);
    
    void runWaintingLoop(int waitingLoop);
    void begin(void);

    //basic functions
    void clear(void);
    void fade(int intensity, int delayTime = 0);
    void setBright(void);

    //Graphical functions
    void plot(int x, int y, int red, int green, int blue);
    virtual void _plot(int x, int y, int red, int green, int blue);//same as plot but without update
    void plotAll(int red, int green, int blue);
    virtual void update(void);//if you use _plot or _putchar

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