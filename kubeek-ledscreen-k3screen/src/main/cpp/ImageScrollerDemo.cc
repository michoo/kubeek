#include "led-matrix.h"
#include "threaded-canvas-manipulator.h"
#include "ImageScroller.h"

#include <assert.h>
#include <getopt.h>
#include <limits.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

#include <algorithm>

using namespace rgb_matrix;
using namespace image_scroller;


int main(int argc, char** argv) {

    int rows = 16;
    int chain = 2;
    int scroll_ms = 30;
    int pwm_bits = 1;
    bool do_luminance_correct = true;
    int scroll_jump=1;
    int runtime_seconds=10;
  
    GPIO io;
    if (!io.Init()){
        return 1;
    }
        
    
    RGBMatrix *matrix = new RGBMatrix(&io, rows, chain);
    matrix->set_luminance_correct(do_luminance_correct);
    if (pwm_bits >= 0 && !matrix->SetPWMBits(pwm_bits)) {
      fprintf(stderr, "Invalid range of pwm-bits\n");
      return 1;
    }

    Canvas *canvas = matrix;

    ThreadedCanvasManipulator *image_gen = NULL;
    
    ImageScroller *scroller = new ImageScroller(canvas, scroll_jump == 1 ? 1 : -1, scroll_ms);
      if (!scroller->LoadPPM("ppm/runtext16.ppm"))
        return 1;
    image_gen = scroller;
      
      
    // Image generating demo is crated. Now start the thread.
    image_gen->Start();

    // Now, the image genreation runs in the background. We can do arbitrary
    // things here in parallel. In this demo, we're essentially just
    // waiting for one of the conditions to exit.

    // Things are set up. Just wait for <RETURN> to be pressed.
    if (runtime_seconds > 0) {
        sleep(runtime_seconds);
    }


  // Stop image generating thread.
  delete image_gen;
  delete canvas;

    
    return 0;
}

