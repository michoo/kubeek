package com.kubeek.app.binaryclock;

import com.kubeek.sdk.app.KApp;
import com.kubeek.sdk.app.KAppManager;
import com.kubeek.sdk.device.KScreenParam;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.sdk.message.factory.KScreenMessageFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.Calendar;
import java.util.TimeZone;


@Slf4j
@Component
@Scope(value = "singleton")
public class BinaryClockApp extends KApp {

    private int dizaineHour;
    private int uniteHour;
    private int dizaineMinute;
    private int uniteMinute;
    private int dizaineSecond;
    private int uniteSecond;
    private KMessageManager kMessageManager;
    private KScreenMessageFactory kScreenMessageFactory;
    private KAppManager kAppManager;
    private KScreenParam kScreenParam; //TODO resize with the screen
    private BinaryClockParams binaryClockParams;

    @Autowired
    public BinaryClockApp(KMessageManager kMessageManager,KScreenMessageFactory kScreenMessageFactory, KAppManager kAppManager, KScreenParam kScreenParam, BinaryClockParams binaryClockParams){
        this.kMessageManager = kMessageManager;
        this.kScreenMessageFactory = kScreenMessageFactory;
        this.kAppManager = kAppManager;
        this.kScreenParam = kScreenParam;
        this.binaryClockParams = binaryClockParams;
        this.setName("Binary Clock App");
    }





    @PostConstruct
    public void addApp(){
        kAppManager.addApp(this);
        log.debug("Binary clock app is loaded");
    }

    @Override
    public void execute() {
        int compteur =0;
        while(compteur<10){
            compteur++;
            try {
                getTime();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        stop();
    }

    @Override
    public void stop() {
        //clear();
    }


    public void setTime() {
        TimeZone timeZone = TimeZone.getTimeZone("America/Montreal");
        Calendar now = Calendar.getInstance();
        //log.debug("Time is " + now.toString());
        now.setTimeZone(timeZone);
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        //        int year = now.get(Calendar.YEAR);
        //        int month = now.get(Calendar.MONTH); // Note: zero based!
        //        int day = now.get(Calendar.DAY_OF_MONTH);

        this.dizaineHour = (hour/10)%10;
        this.uniteHour = hour%10;
        this.dizaineMinute = (minute/10)%10;
        this.uniteMinute = minute%10;
        this.dizaineSecond = (second/10)%10;
        this.uniteSecond = second%10;

        //log.trace(dizaineHour + " " + uniteHour + ":" + dizaineMinute + " " + uniteMinute + ":" + dizaineSecond + " " + uniteSecond);
    }

    public void getTime() throws InterruptedException {
        setTime();

        //System.out.println("dizaineHour: " + dizaineHour);
        printBinary(dizaineHour,3);
        //System.out.println("uniteHour: " + uniteHour);
        printBinary(uniteHour,7);
        //System.out.println("dizaineMinute: " + dizaineMinute);
        printBinary(dizaineMinute,13);
        //System.out.println("uniteMinute: " + uniteMinute);
        printBinary(uniteMinute,17);
        //System.out.println("dizaineSecond: " + dizaineSecond);
        printBinary(dizaineSecond,23);
        //System.out.println("uniteSecond: " + uniteSecond);
        printBinary(uniteSecond,27);

    }

    public void printBinary(int time,int x) throws InterruptedException {
        String mess=String.format("%4s", Integer.toBinaryString(time)).replace(' ', '0');
        for(int i=0;i<4;i++){
            if(mess.charAt(i)=='1'){
                //print point rouge à cette position
                printDot(x,4*i+1,binaryClockParams.getUpColor());
            } else{
                //print point vert
                printDot(x,4*i+1,binaryClockParams.getDownColor());
            }
        }
    }

    public void printDot(int x, int y, Color colour) throws InterruptedException {
        kMessageManager.putNormalQueue(kScreenMessageFactory.getMessagePlotPoint(x, y, colour));
        kMessageManager.putNormalQueue(kScreenMessageFactory.getMessagePlotPoint(x+1, y, colour));
        kMessageManager.putNormalQueue(kScreenMessageFactory.getMessagePlotPoint(x, y+1, colour));
        kMessageManager.putNormalQueue(kScreenMessageFactory.getMessagePlotPoint(x+1, y+1, colour));
    }

    public void clear() {
        try {
            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessageClear());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setDizaineHour(int dizaineHour) {
        this.dizaineHour = dizaineHour;
    }

    public void setUniteHour(int uniteHour) {
        this.uniteHour = uniteHour;
    }

    public void setDizaineMinute(int dizaineMinute) {
        this.dizaineMinute = dizaineMinute;
    }

    public void setUniteMinute(int uniteMinute) {
        this.uniteMinute = uniteMinute;
    }

    public void setDizaineSecond(int dizaineSecond) {
        this.dizaineSecond = dizaineSecond;
    }

    public void setUniteSecond(int uniteSecond) {
        this.uniteSecond = uniteSecond;
    }

    public int getDizaineHour() {
        return dizaineHour;
    }

    public int getUniteHour() {
        return uniteHour;
    }

    public int getDizaineMinute() {
        return dizaineMinute;
    }

    public int getUniteMinute() {
        return uniteMinute;
    }

    public int getDizaineSecond() {
        return dizaineSecond;
    }

    public int getUniteSecond() {
        return uniteSecond;
    }
}
