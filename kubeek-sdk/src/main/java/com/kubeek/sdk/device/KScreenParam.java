package com.kubeek.sdk.device;

public interface KScreenParam {

    int getHeight();
    int getWidth();
    int getRows();
    int getChain();
    String getPPMDirectory();
    String getFontDirectory();
    String getFontExtension();
    String getDefaultFont();
    String getScreenName();
}
