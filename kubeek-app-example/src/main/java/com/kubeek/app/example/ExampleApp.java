package com.kubeek.app.example;

import com.kubeek.sdk.app.KApp;
import com.kubeek.sdk.app.KAppManager;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.sdk.message.factory.KScreenMessageFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Slf4j
@Component
@Scope(value = "singleton")
public class ExampleApp extends KApp {

    private KAppManager kAppManager;
    private KMessageManager kMessageManager;
    private KScreenMessageFactory kScreenMessageFactory;
    private ExampleParams exampleParams;

    @Autowired
    public ExampleApp(KAppManager kAppManager, KMessageManager kMessageManager, KScreenMessageFactory kScreenMessageFactory, ExampleParams exampleParams){
        this.kAppManager = kAppManager;
        this.kMessageManager = kMessageManager;
        this.kScreenMessageFactory = kScreenMessageFactory;
        this.exampleParams = exampleParams;
        this.setName("Example App");
    }


    @PostConstruct
    public void addApp(){
        kAppManager.addApp(this);
        log.debug("Example app is loaded");
    }



    @Override
    public void execute() {

        log.trace("APP EXECUTE");

        try {
            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessageClear());
            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessageScrollText(12,"                Example","6x12",exampleParams.getSpeed(),exampleParams.getAppColor()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        stop();

    }

    @Override
    public void stop() {
        log.trace("App stopping");
        //clear();
    }

    public void clear()  {
        try {
            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessageClear());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
