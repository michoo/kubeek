package com.kubeek.device;

import com.kubeek.sdk.device.KScreenParam;

public class ScreenParam implements KScreenParam {

    private int height;
    private int width;
    private int rows;
    private int chain;
    private String ppmDirectory;
    private String fontDirectory;
    private String fontExtension;
    private String defaultFont;
    private String screenName;


    public ScreenParam(int rows, int chain, String ppmDirectory, String fontDirectory, String defaultFont, String screenName ){
        this.rows=rows;
        this.chain=chain;
        this.ppmDirectory=ppmDirectory;
        this.fontDirectory=fontDirectory;
        this.defaultFont=defaultFont;
        this.screenName=screenName;


        this.height=rows;
        this.width=32*chain;
        this.fontExtension=".bdf";
    }


    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getChain() {
        return chain;
    }

    @Override
    public String getPPMDirectory() {
        return ppmDirectory;
    }

    @Override
    public String getFontDirectory() {
        return fontDirectory;
    }

    @Override
    public String getFontExtension() {
        return fontExtension;
    }

    @Override
    public String getDefaultFont() {
        return defaultFont;
    }

    @Override
    public String getScreenName() {
        return screenName;
    }

}
