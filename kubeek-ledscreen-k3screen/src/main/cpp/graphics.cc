// -*- mode: c++; c-basic-offset: 2; indent-tabs-mode: nil; -*-
// Copyright (C) 2014 Henner Zeller <h.zeller@acm.org>
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation version 2.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program.  If not, see <http://gnu.org/licenses/gpl-2.0.txt>

#include "graphics.h"
#include "utf8-internal.h"
#include <iostream>
#include <cstring>


#include <unistd.h>

namespace rgb_matrix {
int DrawText(Canvas *c, const Font &font, int x, int y, const Color &color, const char *utf8_text) {
    //std::cout << "text sent: " << utf8_text << std::endl;
    const int start_x = x;
    while (*utf8_text) {
        const uint32_t cp = utf8_next_codepoint(utf8_text);
        ++utf8_text;
        //std::cout << "char sent: " << cp << std::endl;
        x += font.DrawGlyph(c, x, y, color, cp);
    }
    return x - start_x;
}

int ScrollText(Canvas *c, const Font &font, int x, int y, int delaytime, const Color &color, const char *message ) {
    
    
    int nbLetter = strlen(message);
    //std::cout << "Graphics::ScrollText - message size: " << nbLetter << std::endl;
  
    //std::cout << "Graphics::ScrollText - message : " << message << std::endl;
    
    int letterSize[nbLetter];
    uint32_t cp[nbLetter];
    int fullSizeMessage=0;

    for (int i = 0; i<nbLetter;i++){
        cp[i] = utf8_next_codepoint(message);
        letterSize[i]=font.CharacterWidth(cp[i]);
        fullSizeMessage+=letterSize[i];
//        std::cout << "Graphics::ScrollText - Iteration: " << i << std::endl;
//        std::cout << "Graphics::ScrollText - Letter: " << cp[i] << std::endl;
//        std::cout << "Graphics::ScrollText - LetterSize: " << letterSize[i] << std::endl;
    }
    
//    std::cout << "graphics ScrollText - nbLetter: " << nbLetter << std::endl;
//    std::cout << "graphics ScrollText - fullSizeMessage: " << fullSizeMessage << std::endl;
 
    
    
//    while (*utf8_text) {
//        const uint32_t cp = utf8_next_codepoint(utf8_text);
//        messageLength+=font.CharacterWidth(cp);
//        nbLetter++;
//    }
    Color black(0,0,0);
    //const uint32_t cp =
    
    int xpos = 0;
    while (xpos > (-1 * fullSizeMessage)) {
        int xstart = 0;
        for (int i = 0; i < nbLetter; i++) {
//            std::cout << "graphics ScrollText - next letter: " << i << std::endl;
            if(xpos<0){
//                std::cout << "graphics ScrollText - clean letter: " << cp[i] << " at position: " << xpos + xstart + 1 <<std::endl;
                font.DrawGlyph(c, xpos + xstart + 1, y, black, cp[i]);
            }
            if (xpos + xstart + letterSize[i] >= 0 && xpos + xstart < c->width()) {
//                std::cout << "graphics ScrollText - print letter: " << cp[i] << " at position: " << xpos + xstart <<std::endl;
                xstart+=font.DrawGlyph(c, xpos + xstart, y, color, cp[i]);
            }else{
                xstart+=letterSize[i];
//                std::cout << "graphics ScrollText - fail to print letter: " << cp[i] << " because >=0: " << xpos + xstart + letterSize[i] <<std::endl;
//                std::cout << "graphics ScrollText - fail to print letter: " << cp[i] << " because : " << xpos + xstart << " <canvas width : " << c->width() <<std::endl;
            }
//            std::cout << "graphics ScrollText - xstart: " << xstart << std::endl;
        }

        if (xpos == 0) {
            usleep(30*1000);
        }

        usleep(delaytime); // reduce speed of scroll
        xpos--;

    }
    return xpos;

}
};
