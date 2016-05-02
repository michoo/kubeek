package com.kubeek.app;

import com.kubeek.sdk.app.KApp;
import com.kubeek.sdk.app.KAppStrategy;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class AppStrategySimple implements KAppStrategy{

    private int compteur=0;

    @Override
    public KApp getNextApp(List<KApp> kApps) {
        int taille = kApps.size();
        KApp kapp = null;
        if (taille>0){

            if (taille == compteur){
                compteur =0;
                log.trace("Reset compteur at " + compteur);
                log.trace("getNextApp " + compteur);
                kapp = kApps.get(compteur);
            }else{
                log.trace("getNextApp " + compteur);
                kapp = kApps.get(compteur);
                compteur++;
            }
        }

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return kapp;
    }
}
