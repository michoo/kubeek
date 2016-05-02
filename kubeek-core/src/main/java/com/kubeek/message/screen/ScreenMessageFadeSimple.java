package com.kubeek.message.screen;

import com.kubeek.sdk.message.screen.KScreenMessageFade;

public class ScreenMessageFadeSimple extends KScreenMessageFade {
	private int pwm;
	private int delayTime;


	public ScreenMessageFadeSimple(int pwm, int delayTime) {
		this.pwm=pwm;
		this.delayTime=delayTime;

	}

	@Override
	public int getPwm() {
		return pwm;
	}

	@Override
	public void setPwm(int pwm) {
		this.pwm = pwm;
	}

	@Override
	public int getDelayTime() {
		return delayTime;
	}

	@Override
	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}
}
