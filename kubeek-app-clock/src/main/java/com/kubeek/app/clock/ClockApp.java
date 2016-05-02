package com.kubeek.app.clock;

import com.kubeek.sdk.app.KApp;
import com.kubeek.sdk.app.KAppManager;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.sdk.message.factory.KScreenMessageFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Slf4j
@Component
@Scope(value = "singleton")
public class ClockApp extends KApp {

    private KAppManager kAppManager;
    private KMessageManager kMessageManager;
    private KScreenMessageFactory kScreenMessageFactory;
    private ClockParams clockParams;


    @Autowired
    public ClockApp(KAppManager kAppManager, KMessageManager kMessageManager, KScreenMessageFactory kScreenMessageFactory, ClockParams clockParams){
        this.kAppManager = kAppManager;
        this.kMessageManager = kMessageManager;
        this.kScreenMessageFactory = kScreenMessageFactory;
        this.clockParams = clockParams;
        this.setName("Clock App");
    }


    @PostConstruct
    public void addApp(){
        kAppManager.addApp(this);
        log.debug("Clock app is loaded");
    }



    @Override
    public void execute() {


        try {


            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");

            //get current date time with Calendar()
            String dateMessage="";
            Calendar cal = Calendar.getInstance();


            int jour=cal.get(Calendar.DAY_OF_WEEK);

            switch(jour){
                case 1:
                    dateMessage="Sunday ";
                    break;
                case 2:
                    dateMessage="Monday ";
                    break;
                case 3:
                    dateMessage="Tuesday ";
                    break;
                case 4:
                    dateMessage="Wednesday ";
                    break;
                case 5:
                    dateMessage="Thursday ";
                    break;
                case 6:
                    dateMessage="Friday ";
                    break;
                case 7:
                    dateMessage="Saturday ";
                    break;
            }

            String message=dateMessage+dateFormat.format(cal.getTime());

            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessageClear());
            kMessageManager.putNormalQueue(kScreenMessageFactory.getMessageScrollText(12,"                " + message,clockParams.getDefaultFont(),clockParams.getSpeed(),clockParams.getAppColor()));
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
