package com.kubeek.app;

import com.kubeek.sdk.app.*;
import com.kubeek.sdk.message.KMessageConsumer;
import com.kubeek.sdk.message.KMessageManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringArrayPropertyEditor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Slf4j
public class AppManager implements KAppManager{

    private List<KApp> kApps = Collections.synchronizedList(new LinkedList<>());
    private boolean continueValue=true;

    private boolean pauseValue=false;


    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @Autowired
    private KAppStrategy kAppStrategy;

    @Autowired
    private KMessageConsumer kMessageConsumer;

    @Autowired
    private KMessageManager kMessageManager;


    @PostConstruct
    public void start() {
        log.debug("Starting App Manager....");
        threadPoolTaskExecutor.execute(this);

        log.debug("Starting Message Consumer....");
        threadPoolTaskExecutor.execute(kMessageConsumer);
    }


    @PreDestroy
    public void stop() {
        log.debug("Ending AppManager");

        //stop this thread
        pauseAppManager();
        stopAppManager();

        //stop thread embedded in apps
        for(int i=0; i<kApps.size(); i++){
            log.trace("PreDestroy app " + i);
            kApps.get(i).stop();
        }

        //stop Message Consumer thread
        kMessageConsumer.pauseMessageConsumer();
        kMessageConsumer.stopMessageConsumer();
        //to let thread time to finish
        threadPoolTaskExecutor.shutdown();
        threadPoolTaskExecutor.setAwaitTerminationSeconds(10);

    }


    @Override
    public void addApp(KApp kApp) {
        kApps.add(kApp);
        kMessageConsumer.setStandalone(false);
    }

    @Override
    public boolean stopAppManager() {
        continueValue=false;
        return continueValue;
    }

    @Override
    public boolean pauseAppManager() {
        pauseValue=true;
        return pauseValue;
    }

    @Override
    public boolean continueAppManager() {
        pauseValue = false;
        return pauseValue;
    }

    @Override
    public List<String> getAppList() {
        List<String> appNameList = new ArrayList<>();

        if(kApps.size() != 0) {
            for (int i = 0; i < kApps.size(); i++) {
                appNameList.add(kApps.get(i).getName());
            }
        }


        return appNameList;
    }

    @Override
    public void run() {

        while(continueValue){

            while (!pauseValue) {
                if((kApps.size()>0) && (kMessageManager.getNormalQueueSize()<40) && (kMessageManager.getImportantQueueSize()<40)){
                    //TODO add this 40 in parameter with default parameter set at 40
                    kAppStrategy.getNextApp(kApps).execute();

                } else{
                    log.debug("AppManager pause waiting app");
                    try {
                        Thread.sleep(15000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if(pauseValue){
                log.debug("AppManager strategy Thread in pause");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }



        }
        log.trace("App Manager is stopping...");
    }
}

