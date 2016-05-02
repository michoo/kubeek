package com.kubeek.service.rest.domain;


import javax.validation.constraints.NotNull;

public class FadeDTO  {

    @NotNull
	private int pwm;

    @NotNull
    private int delayTime;


	public FadeDTO(int pwm, int delayTime) {
		this.pwm=pwm;
		this.delayTime=delayTime;

	}


	public int getPwm() {
		return pwm;
	}


	public void setPwm(int pwm) {
		this.pwm = pwm;
	}

	public int getDelayTime() {
		return delayTime;
	}

	public void setDelayTime(int delayTime) {
		this.delayTime = delayTime;
	}

    @Override
    public String toString() {
        return "FadeDTO{" +
            "pwm=" + pwm +
            ", delayTime=" + delayTime +
            '}';
    }
}
