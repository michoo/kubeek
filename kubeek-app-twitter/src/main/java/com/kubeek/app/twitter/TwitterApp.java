package com.kubeek.app.twitter;

import com.kubeek.sdk.app.KApp;
import com.kubeek.sdk.app.KAppManager;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.sdk.message.factory.KScreenMessageFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.kubeek.utils.Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@Scope(value = "singleton")
public class TwitterApp extends KApp {

    private ConfigurationBuilder cb;
    private String lastTwitt = "";
    private Twitter twitter;
    private Date date;
    private List<Status> statuses = new ArrayList<>();
    private List<Status> statusesOld = new ArrayList<>();
    private List<Status> statusesTemp = new ArrayList<>();
    private int compteur =0;

    private KMessageManager kMessageManager;
    private KScreenMessageFactory kScreenMessageFactory;
    private KAppManager kAppManager;
    private TwitterParams twitterParams;


    @Autowired
    public TwitterApp(KMessageManager kMessageManager, KScreenMessageFactory kScreenMessageFactory, KAppManager kAppManager, TwitterParams twitterParams){
        this.kMessageManager = kMessageManager;
        this.kScreenMessageFactory = kScreenMessageFactory;
        this.kAppManager = kAppManager;
        this.twitterParams = twitterParams;
        this.setName("Twitter App");

    }

    @PostConstruct
    public void addApp(){
        kAppManager.addApp(this);
        init();
        log.debug("Twitter app is loaded");
    }

    public void init() {

        date = new Date();

        twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(twitterParams.getConsumerKey(), twitterParams.getConsumerSecret());

        AccessToken accessToken = new AccessToken(twitterParams.getAccessToken(),twitterParams.getAccessTokenSecret());
        twitter.setOAuthAccessToken(accessToken);


        try {

            statusesOld.clear();
            statusesOld.addAll(twitter.getHomeTimeline());

        } catch (TwitterException ex) {
            log.error(ex.getMessage());
        }
    }



    @Override
    public void execute() {
        Date lastDate = new Date();
        long diffInMins = Math.abs(lastDate.getTime() - date.getTime()) / 60000;
        compteur++;
        try {

            if ((diffInMins >= 2) || (compteur == 1)) {
                date = lastDate;
                statuses.clear();
                statuses.addAll(twitter.getHomeTimeline());
                statusesTemp.clear();
                statusesTemp.addAll(statuses);

                statuses.removeAll(statusesOld);
                statusesOld.clear();
                statusesOld.addAll(statusesTemp);

                for (int i = 0; i < statuses.size(); i++) {
                    String mess = "@" + statuses.get(i).getUser().getScreenName() + " - " + statuses.get(i).getText();
                    log.trace(mess + " - " + statuses.get(i).getText().length());
                    try {
                        kMessageManager.putNormalQueue(kScreenMessageFactory.getMessageClear());
                        kMessageManager.putNormalQueue(kScreenMessageFactory.getMessageScrollText(12,"                " + mess,"6x12",twitterParams.getSpeed(),twitterParams.getAppColor()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        } catch (TwitterException te) {
            te.printStackTrace();
            log.trace("Failed to get timeline: " + te.getMessage());
            Utils.pause(12000);
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
