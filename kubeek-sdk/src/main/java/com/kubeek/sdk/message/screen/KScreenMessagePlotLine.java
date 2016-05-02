/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kubeek.sdk.message.screen;

import java.awt.*;

public abstract class KScreenMessagePlotLine extends KScreenMessage {

    public abstract int getY0();
    public abstract void setY0(int y0);
    public abstract int getX0();
    public abstract void setX0(int x0);
    public abstract int getY1();
    public abstract void setY1(int y1);
    public abstract int getX1();
    public abstract void setX1(int x1);
    public abstract Color getColor();
    public abstract void setColor(Color color);

}
