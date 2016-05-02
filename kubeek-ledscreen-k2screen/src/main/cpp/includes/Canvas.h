/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Canvas.h
 * Author: fred
 *
 * Created on 29 février 2016, 23:42
 */

#ifndef CANVAS_H
#define CANVAS_H

class Canvas {
public:
  virtual void _plot(int x, int y, int red, int green, int blue) = 0;
  virtual void update(void) = 0;

  
};


#endif /* CANVAS_H */

