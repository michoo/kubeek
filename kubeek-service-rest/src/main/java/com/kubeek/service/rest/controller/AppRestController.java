package com.kubeek.service.rest.controller;

import com.kubeek.sdk.device.KScreenParam;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.sdk.message.factory.KScreenMessageFactory;
import com.kubeek.service.rest.service.AppService;
import com.kubeek.service.rest.service.FontService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/app")
public class AppRestController {

    private KScreenMessageFactory kScreenMessageFactory;
    private KMessageManager kMessageManager;
    private KScreenParam kScreenParam;
    private FontService fontService;
    private AppService appService;


    @Autowired
    public AppRestController(KScreenMessageFactory kScreenMessageFactory, KMessageManager kMessageManager,
                               KScreenParam kScreenParam, FontService fontService, AppService appService){
        this.kScreenMessageFactory = kScreenMessageFactory;
        this.kMessageManager = kMessageManager;
        this.kScreenParam = kScreenParam;
        this.fontService = fontService;
        this.appService = appService;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<String>> getApps(){

        return new ResponseEntity<>(appService.findAll(), HttpStatus.OK);
    }



}
