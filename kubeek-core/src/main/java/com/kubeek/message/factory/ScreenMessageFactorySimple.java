package com.kubeek.message.factory;

import com.kubeek.message.screen.*;
import com.kubeek.sdk.device.KScreenParam;
import com.kubeek.sdk.message.factory.KScreenMessageFactory;
import com.kubeek.sdk.message.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.io.File;

public class ScreenMessageFactorySimple extends KScreenMessageFactory {

    @Autowired
    private KScreenParam kScreenParam;


    @Override
    public KScreenMessagePlotPoint getMessagePlotPoint(int x, int y, Color color) {
        return new ScreenMessagePlotPointSimple(x,y,color);
    }

    @Override
    public KScreenMessagePlotPoint getMessagePlotPoint(int x, int y) {
        return new ScreenMessagePlotPointSimple(x,y,this.getDefaultColor());
    }

    @Override
    public KScreenMessagePlotAllPoints getMessagePlotAllPoints(Color color) {
        return new ScreenMessagePlotAllSimple(color);
    }

    @Override
    public KScreenMessagePlotAllPoints getMessagePlotAllPoints() {
        return new ScreenMessagePlotAllSimple(this.getDefaultColor());
    }

    @Override
    public KScreenMessageMemPoint getMessageMemPoint(int x, int y, Color color) {
        return new ScreenMessageMemPointSimple(x,y,color);
    }

    @Override
    public KScreenMessageMemPoint getMessageMemPoint(int x, int y) {
        return new ScreenMessageMemPointSimple(x,y,this.getDefaultColor());
    }

    @Override
    public KScreenMessageUpdate getMessageUpdate() {
        return new ScreenMessageUpdateSimple();
    }

    @Override
    public KScreenMessageClear getMessageClear() {
        return new ScreenMessageClearSimple();
    }

    @Override
    public KScreenMessageFade getMessageFade(int pwm, int delayTime) {
        return new ScreenMessageFadeSimple(pwm,delayTime);
    }

    @Override
    public KScreenMessageFade getMessageFade(int pwm) {
        return new ScreenMessageFadeSimple(pwm,this.getDefaultDelayTime());
    }

    @Override
    public KScreenMessageSetBright getMessageSetBright() {
        return new ScreenMessageSetBrightSimple();
    }

    @Override
    public KScreenMessagePlotLine getMessagePlotLine(int x0, int y0, int x1, int y1, Color color) {
        return new ScreenMessagePlotLineSimple(x0, y0, x1, y1, color);
    }

    @Override
    public KScreenMessagePlotLine getMessagePlotLine(int x0, int y0, int x1, int y1) {
        return new ScreenMessagePlotLineSimple(x0, y0, x1, y1, this.getDefaultColor());
    }

    @Override
    public KScreenMessagePlotCircle getMessagePlotCircle(int xm, int ym, int r, Color color) {
        return new ScreenMessagePlotCircleSimple(xm, ym, r, color);
    }

    @Override
    public KScreenMessagePlotCircle getMessagePlotCircle(int xm, int ym, int r) {
        return new ScreenMessagePlotCircleSimple(xm, ym, r, this.getDefaultColor());
    }

    @Override
    public KScreenMessagePlotEllipse getMessagePlotEllipse(int x0, int y0, int x1, int y1, Color color) {
        return new ScreenMessagePlotEllipseSimple(x0, y0, x1, y1, color);
    }

    @Override
    public KScreenMessagePlotEllipse getMessagePlotEllipse(int x0, int y0, int x1, int y1) {
        return new ScreenMessagePlotEllipseSimple(x0, y0, x1, y1, this.getDefaultColor());
    }

    @Override
    public KScreenMessagePlotBezier getMessagePlotBezier(int x0, int y0, int x1, int y1, int x2, int y2, Color color) {
        return new ScreenMessagePlotBezierSimple(x0, y0, x1, y1, x2, y2, color);
    }

    @Override
    public KScreenMessagePlotBezier getMessagePlotBezier(int x0, int y0, int x1, int y1, int x2, int y2) {
        return new ScreenMessagePlotBezierSimple(x0, y0, x1, y1, x2, y2, this.getDefaultColor());
    }

    @Override
    public KScreenMessageMemChar getMessageMemChar(int x, int y, char c, String fontName, Color color) {
        return new ScreenMessageMemCharSimple(x, y, c, kScreenParam.getFontDirectory() + File.separator + fontName + kScreenParam.getFontExtension(), color);
    }

    @Override
    public KScreenMessageMemChar getMessageMemChar(int x, int y, char c, String fontName) {
        return new ScreenMessageMemCharSimple(x, y, c, kScreenParam.getFontDirectory() + File.separator + fontName + kScreenParam.getFontExtension(), this.getDefaultColor());
    }

    @Override
    public KScreenMessageMemChar getMessageMemChar(int x, int y, char c, Color color) {
        return new ScreenMessageMemCharSimple(x, y, c,kScreenParam.getFontDirectory() + File.separator + this.getDefaultFontName() + kScreenParam.getFontExtension(), color);
    }

    @Override
    public KScreenMessageMemChar getMessageMemChar(int x, int y, char c) {
        return new ScreenMessageMemCharSimple(x, y, c, kScreenParam.getFontDirectory() + File.separator + this.getDefaultFontName() + kScreenParam.getFontExtension(), this.getDefaultColor());
    }

    @Override
    public KScreenMessagePlotChar getMessagePlotChar(int x, int y, char c, String fontName, Color color) {
        return new ScreenMessagePlotCharSimple(x, y, c, kScreenParam.getFontDirectory() + File.separator + fontName + kScreenParam.getFontExtension(), color);
    }

    @Override
    public KScreenMessagePlotChar getMessagePlotChar(int x, int y, char c, String fontName) {
        return new ScreenMessagePlotCharSimple(x, y, c, kScreenParam.getFontDirectory() + File.separator + fontName + kScreenParam.getFontExtension(), this.getDefaultColor());
    }

    @Override
    public KScreenMessagePlotChar getMessagePlotChar(int x, int y, char c, Color color) {
        return new ScreenMessagePlotCharSimple(x, y, c, kScreenParam.getFontDirectory() + File.separator + this.getDefaultFontName() + kScreenParam.getFontExtension(), color);
    }

    @Override
    public KScreenMessagePlotChar getMessagePlotChar(int x, int y, char c) {
        return new ScreenMessagePlotCharSimple(x, y, c, kScreenParam.getFontDirectory() + File.separator + this.getDefaultFontName() + kScreenParam.getFontExtension(), this.getDefaultColor());
    }

    @Override
    public KScreenMessageScrollText getMessageScrollText(int y, String message, String fontName, int delayTime, Color color) {
        return new ScreenMessageScrollTextSimple(y,message,kScreenParam.getFontDirectory() + File.separator + fontName + kScreenParam.getFontExtension(),delayTime,color);
    }

    @Override
    public KScreenMessageScrollText getMessageScrollText(int y, String message, String fontName) {
        return new ScreenMessageScrollTextSimple(y,message,kScreenParam.getFontDirectory() + File.separator + fontName + kScreenParam.getFontExtension(),this.getDefaultDelayTime(),this.getDefaultColor());
    }

    @Override
    public KScreenMessageScrollText getMessageScrollText(int y, String message, Color color) {
        return new ScreenMessageScrollTextSimple(y,message,kScreenParam.getFontDirectory() + File.separator + this.getDefaultFontName() + kScreenParam.getFontExtension(),this.getDefaultDelayTime(),color);
    }

    @Override
    public KScreenMessageScrollText getMessageScrollText(int y, String message) {
        return new ScreenMessageScrollTextSimple(y,message,kScreenParam.getFontDirectory() + File.separator + this.getDefaultFontName() + kScreenParam.getFontExtension(),this.getDefaultDelayTime(),this.getDefaultColor());
    }

    @Override
    public KScreenMessageScrollPPM getMessageScrollPpm(String filename, int scrollJump, int runtimeSeconds, int scrollMs) {
        return new ScreenMessageScrollPPMSimple(filename,scrollJump,runtimeSeconds,scrollMs);
    }
}
