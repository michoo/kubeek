package com.kubeek.service.rest.config;

import com.kubeek.device.ScreenParam;
import com.kubeek.device.k2screendevice.K2Screen;
import com.kubeek.device.k3screendevice.K3Screen;

import com.kubeek.device.ksimulatorscreendevice.KSimulatorScreen;
import com.kubeek.sdk.device.KScreenDevice;
import com.kubeek.sdk.device.KScreenParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.nio.file.FileSystem;

@Configuration
public class ScreenConfiguration {

    @Value("${kubeek.screen.rows:16}")
    private int rows;

    @Value("${kubeek.screen.chain:2}")
    private int chain;

    @Value("${kubeek.screen.font:font}")
    private String fontDirectory;

    @Value("${kubeek.screen.ppm:ppm}")
    private String ppmDirectory;

    @Value("${kubeek.screen.model:ksimulatorscreen}")
    private String screen;

    @Value("${kubeek.screen.defaultfont:6x10}")
    private String defaultFont;


    @Bean
    public KScreenDevice getScreen(){

        KScreenDevice KScreenDevice =null;

        if(screen.toLowerCase().equals("k3screen")){
            KScreenDevice = new K3Screen(rows, chain);

        } else if(screen.toLowerCase().equals("k2screen")){
            KScreenDevice = new K2Screen(rows, chain);

        }else if(screen.toLowerCase().equals("ksimulatorscreen")){
            KScreenDevice = new KSimulatorScreen(rows,chain);

        }

        return KScreenDevice;
    }

    @Bean
    public KScreenParam getKScreenParam(){

        if(ppmDirectory.endsWith(File.separator)){
            ppmDirectory=ppmDirectory.substring(0,ppmDirectory.length()-1);
        }

        if(fontDirectory.endsWith(File.separator)){
            fontDirectory=fontDirectory.substring(0,fontDirectory.length()-1);
        }
        return new ScreenParam(rows,chain,ppmDirectory,fontDirectory, defaultFont, screen);


        //TODO permettre la gestion de la sauvegarde du paramétrage de la couleur pour chaque application
    }




}
