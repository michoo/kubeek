/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kubeek.sdk.message.screen;

import java.awt.*;

public abstract class KScreenMessageMemPoint extends KScreenMessage {

    public abstract  int getY();

    public abstract void setY(int y);

    public abstract int getX();

    public abstract void setX(int x);

    public abstract Color getColor();

    public abstract void setColor(Color color);


}
