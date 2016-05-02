/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kubeek.sdk.message.screen;

import java.awt.*;

public abstract class KScreenMessageScrollText extends KScreenMessage {


    public abstract int getY();
    public abstract void setY(int y);
    public abstract Color getColor();
    public abstract void setColor(Color color);
    public abstract String getFontName();
    public abstract void setFontName(String fontName);
    public abstract int getDelayTime();
    public abstract void setDelayTime(int delayTime);
    public abstract String getMessage();
    public abstract void setMessage(String message);


}
