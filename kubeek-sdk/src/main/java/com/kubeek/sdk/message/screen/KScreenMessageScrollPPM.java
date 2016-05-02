package com.kubeek.sdk.message.screen;

public abstract class KScreenMessageScrollPPM extends KScreenMessage {

    public abstract String getFileName();
    public abstract void setFileName(String fileName);
    public abstract int getScrollJump();
    public abstract void setScrollJump(int scrollJump);
    public abstract int getRuntimeSeconds();
    public abstract void setRuntimeSeconds(int runtimeSeconds);
    public abstract int getScrollMs();
    public abstract void setScrollMs(int scrollMs);
}
