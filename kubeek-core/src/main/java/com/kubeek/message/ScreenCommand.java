package com.kubeek.message;

import com.kubeek.message.screen.*;
import com.kubeek.sdk.command.KScreenCommand;
import com.kubeek.sdk.device.KScreenDevice;
import com.kubeek.sdk.message.screen.*;
import org.springframework.beans.factory.annotation.Autowired;

public class ScreenCommand implements KScreenCommand {

    @Autowired
    private KScreenDevice kScreenDevice;


    @Override
    public void send(KScreenMessage message) {

        if(message instanceof KScreenMessageClear) {
            kScreenDevice.clear();
        } else if(message instanceof KScreenMessageFade){

            KScreenMessageFade ikScreenMessageFade = (KScreenMessageFade) message;
            kScreenDevice.fade(ikScreenMessageFade.getPwm(), ikScreenMessageFade.getDelayTime());

        } else if(message instanceof KScreenMessageMemChar){

            KScreenMessageMemChar ikScreenMessageMemChar = (KScreenMessageMemChar) message;
            kScreenDevice._putChar(ikScreenMessageMemChar.getX(),ikScreenMessageMemChar.getY(),ikScreenMessageMemChar.getCharacter(),ikScreenMessageMemChar.getFontName(),ikScreenMessageMemChar.getColor().getRed(), ikScreenMessageMemChar.getColor().getGreen(), ikScreenMessageMemChar.getColor().getBlue());

        } else if(message instanceof KScreenMessageMemPoint){

            KScreenMessageMemPoint ikScreenMessageMemPoint = (KScreenMessageMemPoint) message;
            kScreenDevice._plot(ikScreenMessageMemPoint.getX(),ikScreenMessageMemPoint.getY(), ikScreenMessageMemPoint.getColor().getRed(), ikScreenMessageMemPoint.getColor().getGreen(), ikScreenMessageMemPoint.getColor().getBlue());

        } else if(message instanceof KScreenMessagePlotAllPoints){
            KScreenMessagePlotAllPoints kScreenMessagePlotAllPoints = (KScreenMessagePlotAllPoints) message;
            kScreenDevice.plotAll(kScreenMessagePlotAllPoints.getColor().getRed(), kScreenMessagePlotAllPoints.getColor().getGreen(), kScreenMessagePlotAllPoints.getColor().getBlue());

        } else if(message instanceof KScreenMessagePlotBezier){

            KScreenMessagePlotBezier ikScreenMessagePlotBezier = (KScreenMessagePlotBezier) message;
            kScreenDevice.plotBasicBezier(ikScreenMessagePlotBezier.getX0(),ikScreenMessagePlotBezier.getY0(), ikScreenMessagePlotBezier.getX1(), ikScreenMessagePlotBezier.getY1(), ikScreenMessagePlotBezier.getX2(),
                    ikScreenMessagePlotBezier.getY2(), ikScreenMessagePlotBezier.getColor().getRed(), ikScreenMessagePlotBezier.getColor().getGreen(), ikScreenMessagePlotBezier.getColor().getBlue());

        } else if(message instanceof KScreenMessagePlotChar){

            KScreenMessagePlotChar ikScreenMessagePlotChar = (KScreenMessagePlotChar) message;
            kScreenDevice.putChar(ikScreenMessagePlotChar.getX(), ikScreenMessagePlotChar.getY(),ikScreenMessagePlotChar.getC(),ikScreenMessagePlotChar.getFontName(),
                    ikScreenMessagePlotChar.getColor().getRed(), ikScreenMessagePlotChar.getColor().getGreen(), ikScreenMessagePlotChar.getColor().getBlue());

        } else if(message instanceof KScreenMessagePlotCircle){
            KScreenMessagePlotCircle ikScreenMessagePlotCircle = (KScreenMessagePlotCircle) message;
            kScreenDevice.plotCircle(ikScreenMessagePlotCircle.getXm(), ikScreenMessagePlotCircle.getYm(), ikScreenMessagePlotCircle.getR(),
                    ikScreenMessagePlotCircle.getColor().getRed(), ikScreenMessagePlotCircle.getColor().getGreen(), ikScreenMessagePlotCircle.getColor().getBlue());

        } else if(message instanceof KScreenMessagePlotEllipse){
            KScreenMessagePlotEllipse ikScreenMessagePlotEllipse = (KScreenMessagePlotEllipse) message;
            kScreenDevice.plotEllipseRect(ikScreenMessagePlotEllipse.getX0(), ikScreenMessagePlotEllipse.getY0(), ikScreenMessagePlotEllipse.getX1(), ikScreenMessagePlotEllipse.getY1(),
                    ikScreenMessagePlotEllipse.getColor().getRed(), ikScreenMessagePlotEllipse.getColor().getGreen(), ikScreenMessagePlotEllipse.getColor().getBlue());

        } else if(message instanceof KScreenMessagePlotLine){
            KScreenMessagePlotLine ikScreenMessagePlotLine = (KScreenMessagePlotLine) message;
            kScreenDevice.plotLine(ikScreenMessagePlotLine.getX0(), ikScreenMessagePlotLine.getY0(), ikScreenMessagePlotLine.getX1(), ikScreenMessagePlotLine.getY1(),
                    ikScreenMessagePlotLine.getColor().getRed(), ikScreenMessagePlotLine.getColor().getGreen(), ikScreenMessagePlotLine.getColor().getBlue());

        } else if(message instanceof KScreenMessagePlotPoint){
            KScreenMessagePlotPoint ikScreenMessagePlotPoint = (KScreenMessagePlotPoint) message;
            kScreenDevice.plot(ikScreenMessagePlotPoint.getX(), ikScreenMessagePlotPoint.getY(),
                    ikScreenMessagePlotPoint.getColor().getRed(), ikScreenMessagePlotPoint.getColor().getGreen(), ikScreenMessagePlotPoint.getColor().getBlue());

        } else if(message instanceof KScreenMessageScrollPPM){
            KScreenMessageScrollPPM ikScreenMessageScrollPPM = (KScreenMessageScrollPPM) message;
            kScreenDevice.scrollPPM(ikScreenMessageScrollPPM.getFileName(), ikScreenMessageScrollPPM.getScrollJump(), ikScreenMessageScrollPPM.getRuntimeSeconds(), ikScreenMessageScrollPPM.getRuntimeSeconds());

        } else if(message instanceof KScreenMessageScrollText){
            KScreenMessageScrollText ikScreenMessageScrollText = (KScreenMessageScrollText) message;
            kScreenDevice.scrollText(ikScreenMessageScrollText.getY(), ikScreenMessageScrollText.getMessage(), ikScreenMessageScrollText.getFontName(), ikScreenMessageScrollText.getDelayTime(),
                    ikScreenMessageScrollText.getColor().getRed(), ikScreenMessageScrollText.getColor().getGreen(), ikScreenMessageScrollText.getColor().getBlue());

        } else if(message instanceof ScreenMessageSetBrightSimple){
            kScreenDevice.setBright();
        }

    }

}
