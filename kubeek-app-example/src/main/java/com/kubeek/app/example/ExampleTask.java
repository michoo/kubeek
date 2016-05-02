package com.kubeek.app.example;

import com.kubeek.sdk.app.KTask;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.sdk.message.factory.KScreenMessageFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Date;


@Slf4j
@Component
@Scope(value = "singleton")
public class ExampleTask extends KTask {

    private int compteur=0;

    private KMessageManager kMessageManager;
    private KScreenMessageFactory kScreenMessageFactory;
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    public ExampleTask(KMessageManager kMessageManager, KScreenMessageFactory kScreenMessageFactory, ThreadPoolTaskExecutor threadPoolTaskExecutor){
        this.kMessageManager = kMessageManager;
        this.kScreenMessageFactory = kScreenMessageFactory;
        this.threadPoolTaskExecutor = threadPoolTaskExecutor;
        this.setName("Example Task");
    }

    @PostConstruct
    public void addTask(){
        //kAppManager.addApp(this);
        threadPoolTaskExecutor.execute(this);
    }

    @PreDestroy
    public void stop(){
        this.pauseKTask();
        this.stopKTask();
    }



    @Override
    public void run() {
        while(getContinueValue()) {

            while (!getPauseValue()) {

                compteur++;
                log.debug("TASK RUN:  " + compteur);

                //DO SOMETHING

                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            log.debug("Task is paused!!!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        log.debug("Task is now stopped!!!");



    }


}
