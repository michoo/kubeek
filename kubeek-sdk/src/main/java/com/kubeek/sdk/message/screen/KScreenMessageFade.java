/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.kubeek.sdk.message.screen;

public abstract class KScreenMessageFade extends KScreenMessage {

    public abstract int getPwm();

    public abstract void setPwm(int pwm);

    public abstract int getDelayTime();

    public abstract void setDelayTime(int delayTime);

}
