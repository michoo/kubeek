package com.kubeek.app.stock;

import com.kubeek.sdk.app.KApp;
import com.kubeek.sdk.app.KAppManager;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.sdk.message.factory.KScreenMessageFactory;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Slf4j
@Component
@Scope(value = "singleton")
public class StockExchangeApp extends KApp {

    private KAppManager kAppManager;
    private KMessageManager kMessageManager;
    private KScreenMessageFactory kScreenMessageFactory;
    private StockExchangeParams stockExchangeParams;

    @Autowired
    public StockExchangeApp(KAppManager kAppManager, KMessageManager kMessageManager, KScreenMessageFactory kScreenMessageFactory, StockExchangeParams stockExchangeParams){
        this.kAppManager = kAppManager;
        this.kMessageManager = kMessageManager;
        this.kScreenMessageFactory = kScreenMessageFactory;
        this.stockExchangeParams = stockExchangeParams;
        this.setName("Stock Exchange App");
    }


    @PostConstruct
    public void addApp(){
        kAppManager.addApp(this);
        log.debug("StockExchange app is loaded");
    }

    public String getQuoteCAC40() {
        String retour = "";
        Document doc;
        try {
            doc = Jsoup.connect("http://finance.yahoo.com/q?s=EURCAD=X").userAgent("Mozilla").get();
            retour = "EURCAD: " + doc.getElementById("yfs_l10_eurcad=x").text();

        } catch (IOException ex) {

        }

        return retour;


    }

    @Override
    public void execute() {



        String message=getQuoteCAC40();


        try {
            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessageClear());
            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessageScrollText(12,"                " +message, "6x12",stockExchangeParams.getSpeed(),stockExchangeParams.getAppColor()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        stop();

    }

    @Override
    public void stop() {
        //clear();
    }

    public void clear() {
        try {
            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessageClear());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
