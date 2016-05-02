/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kubeek.sdk.message.screen;

import java.awt.*;

public abstract class KScreenMessagePlotCircle extends KScreenMessage {

    public abstract int getYm();
    public abstract void setYm(int ym);
    public abstract int getXm();
    public abstract void setXm(int xm);
    public abstract int getR();
    public abstract void setR(int r);
    public abstract Color getColor();
    public abstract void setColor(Color color);

    
}
