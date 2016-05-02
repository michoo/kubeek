package com.kubeek.message.screen;

import com.kubeek.sdk.message.screen.KScreenMessageScrollPPM;

public class ScreenMessageScrollPPMSimple extends KScreenMessageScrollPPM {
    private String fileName;
    private int scrollJump;
    private int runtimeSeconds;
    private int scrollMs;


    public ScreenMessageScrollPPMSimple(String fileName, int scrollJump, int runtimeSeconds, int scrollMs){
        this.fileName=fileName;
        this.scrollJump=scrollJump;
        this.runtimeSeconds=runtimeSeconds;
        this.scrollMs=scrollMs;
    }

    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int getScrollJump() {
        return scrollJump;
    }

    @Override
    public void setScrollJump(int scrollJump) {
        this.scrollJump = scrollJump;
    }

    @Override
    public int getRuntimeSeconds() {
        return runtimeSeconds;
    }

    @Override
    public void setRuntimeSeconds(int runtimeSeconds) {
        this.runtimeSeconds = runtimeSeconds;
    }

    @Override
    public int getScrollMs() {
        return scrollMs;
    }

    @Override
    public void setScrollMs(int scrollMs) {
        this.scrollMs = scrollMs;
    }
}
