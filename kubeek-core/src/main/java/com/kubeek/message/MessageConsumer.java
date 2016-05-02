package com.kubeek.message;


import com.kubeek.sdk.command.KScreenCommand;
import com.kubeek.sdk.message.KMessage;
import com.kubeek.sdk.message.KMessageConsumer;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.sdk.message.screen.KScreenMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MessageConsumer implements KMessageConsumer {

    private boolean continueValue=true;
    private boolean pauseValue=false;
    private boolean standalone=true;


    @Autowired
    private KMessageManager kMessageManager;

    @Autowired
    private KScreenCommand kScreenCommand;

    @Override
    public void run() {

        while(continueValue){

            while (!pauseValue) {

                //read important message queue then send all messages to switcher
                while (!kMessageManager.isImportantQueueEmpty()) {
                    //log.debug("Important message sending...");

                    try {
                        KMessage messageImportant = kMessageManager.takeImportantQueue();
                        sendMessage(messageImportant);


                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }

                if (!kMessageManager.isNormalQueueEmpty()) {
                    //log.debug("Normal message sending...");
                    try {
                        KMessage messageNormal = kMessageManager.takeNormalQueue();
                        sendMessage(messageNormal);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if(kMessageManager.isImportantQueueEmpty() && kMessageManager.isNormalQueueEmpty() && standalone==true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            if(pauseValue){
                log.trace("Consumer Thread in pause");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
        log.debug("Consumer Thread stopping...");



    }

    @Override
    public boolean stopMessageConsumer() {
        continueValue=false;
        return continueValue;
    }

    @Override
    public boolean pauseMessageConsumer() {
        pauseValue=true;
        return pauseValue;
    }

    @Override
    public boolean continueMessageConsumer(){

        pauseValue = false;
        return pauseValue;

    }

    @Override
    public boolean startMessageConsumer() {
        continueValue=true;
        return continueValue;
    }

    @Override
    public boolean setStandalone(boolean standalone) {
        this.standalone = standalone;
        return standalone;
    }


    private void sendMessage(KMessage kMessage){
        if(kMessage instanceof KScreenMessage){
            KScreenMessage kScreenMessage = (KScreenMessage) kMessage;
            kScreenCommand.send(kScreenMessage);
        }

    }


}
