package com.kubeek.app.calculous;

import com.kubeek.sdk.app.KApp;
import com.kubeek.sdk.app.KAppManager;
import com.kubeek.sdk.device.KScreenParam;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.sdk.message.factory.KScreenMessageFactory;
import com.kubeek.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.awt.*;
import java.util.Random;

@Slf4j
@Component
@Scope(value = "singleton")
public class CalculousApp extends KApp{

    private double value1, value2;
    private int digit = 1;
    private int difficulty = 3;
    private int operande = 1;
    private String value1Str,value2Str;
    private double result=0;
    private String charOperande = "+";
    private int delay=1000;
    private int delay2=1500;

    private KAppManager kAppManager;
    private KMessageManager kMessageManager;
    private KScreenMessageFactory kScreenMessageFactory;
    private CalculousParams calculousParams;
    private KScreenParam kScreenParam;

    @Autowired
    public CalculousApp(KAppManager kAppManager, KMessageManager kMessageManager, KScreenMessageFactory kScreenMessageFactory, CalculousParams calculousParams, KScreenParam kScreenParam){
        this.kAppManager = kAppManager;
        this.kMessageManager = kMessageManager;
        this.kScreenMessageFactory = kScreenMessageFactory;
        this.calculousParams = calculousParams;
        this.difficulty = calculousParams.getDifficulty();
        this.digit = calculousParams.getDigits();
        this.kScreenParam = kScreenParam;
        this.setName("Calculous App");
    }


    @PostConstruct
    public void addApp(){
        kAppManager.addApp(this);
        log.debug("Calculous app is loaded");
    }


    @Override
    public void execute() {

        clear();

        getNewResult();
        String resultStr = String.valueOf(result);
        if(resultStr.endsWith(".0")){
            resultStr = String.valueOf(result).substring(0, String.valueOf(result).length()-2);
        }

        //AFFICHAGES
        //DeviceManager.getInstance().send(KMessageFactory.getInstance().getMessageScrolledText(value1Str, 4, 1, 2, 100));


        try {
            printMessage(value1Str);
            Utils.pause(delay2);
            clear();
            printMessage(charOperande);
            Utils.pause(delay2);
            clear();
            printMessage(value2Str);
            Utils.pause(delay2);
            clear();
            printMessage("=");
            Utils.pause(delay);
            clear();
            printMessage("?");

            hourglass(delay);
            clear();
            printMessage("=");
            Utils.pause(delay);
            clear();

            printMessage(resultStr);
            Utils.pause(delay);
            clear();
            Utils.pause(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void stop() {
        //clear();
    }


    public void getNewValue1(int digit) {

        int coeff = (int) Math.pow(10, digit);
        this.value1 = (int) (Math.random() * (coeff));
        if(value1==1||value1==0){
            getNewValue1(digit);
        }

    }

    public void getNewValue2(int digit) {
        int coeff = (int) Math.pow(10, digit);
        this.value2 = (int) (Math.random() * (coeff));
        if(value2==1||value2==0){
            getNewValue2(digit);
        }
    }

    public void getNewOperande() {

        int lower = 1;
        int higher = 4;
        Random rand = new Random();
        this.operande = rand.nextInt((higher - lower) + 1) + lower;


    }
    public void getNewDigit() {

        int lower = 1;
        int higher = difficulty;
        Random rand = new Random();
        this.digit = rand.nextInt((higher - lower) + 1) + lower;

    }

    public double getValue1() {
        return value1;
    }

    public double getValue2() {
        return value2;
    }

    public int getOperande() {
        return operande;
    }


    public void getNewResult(){
        //recherche d'une nouvelle valeur pour value1
        getNewDigit();
        getNewValue1(digit);
        value1Str= String.valueOf(value1).substring(0, String.valueOf(value1).length()-2);

        //recherche d'une nouvelle valeur pour value2
        getNewDigit();
        getNewValue2(digit);
        value2Str = String.valueOf(value2).substring(0, String.valueOf(value2).length()-2);


        //recherche d'un nouvel opérande
        getNewOperande();



        //traitement du choix pour affichage
        switch (getOperande()) {
            case 1:
                result = value1 + value2;
                charOperande = "+";
                break;
            case 2:
                result = value1 - value2;
                charOperande = "-";
                break;
            case 3:
                result = value1 * value2;
                charOperande = "*";
                break;
            case 4:
                if(value2 != 0) {
                    result = value1 / value2;
                }else{
                    result = 0;
                }
                charOperande = "/";
                break;
        }

        //pour éviter les calculs trop faciles
        if((charOperande.equals("+")||charOperande.equals("-"))&&((value1<=10)||(value2<=10))){
            getNewResult();
        }

    }

    public void printMessage(String message) throws InterruptedException {
        int taille = message.length();
        if (taille == 1) {
            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessagePlotChar((kScreenParam.getWidth()/2)-3,(kScreenParam.getWidth()/2)+5, message.charAt(0), calculousParams.getDefaultFont(),calculousParams.getAppColor()));

        } else if (taille > 1 && taille <= 4) {

            for (int i = 1; i <= taille; i++) {
                kMessageManager.putNormalQueue(kScreenMessageFactory.getMessagePlotChar((kScreenParam.getWidth()/2) - (3 * taille)+(6*(i-1)), (kScreenParam.getWidth()/2)+5, message.charAt(i-1), calculousParams.getDefaultFont(),calculousParams.getAppColor()));

            }
        } else if (taille > 4) {
            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessageScrollText((kScreenParam.getWidth()/2)+5,"                " + message,calculousParams.getDefaultFont(),calculousParams.getSpeed(),calculousParams.getAppColor()));

        }

    }

    private void hourglass(int j) throws InterruptedException {
        for(int i=0;i<(4*kScreenParam.getWidth()/8);i++){


            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessagePlotLine(0, i, kScreenParam.getWidth(),i, new Color(0,255,0)));
            Utils.pause(delay);
        }
        for(int i=(4*kScreenParam.getWidth()/8);i<(6*kScreenParam.getWidth()/8);i++){
            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessagePlotLine(0, i, kScreenParam.getWidth(),i, new Color(255,255,0)));
            Utils.pause(delay);
        }
        for(int i=(6*kScreenParam.getWidth()/8);i<kScreenParam.getWidth();i++){
            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessagePlotLine(0, i, kScreenParam.getWidth(),i, new Color(255,0,0)));
            Utils.pause(delay);
        }

    }

    public void clear() {
        try {
            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessageClear());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
