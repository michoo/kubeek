package com.kubeek.service.rest.controller;

import com.kubeek.sdk.device.KScreenParam;
import com.kubeek.sdk.message.KMessage;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.sdk.message.factory.KScreenMessageFactory;
import com.kubeek.service.rest.domain.FadeDTO;
import com.kubeek.service.rest.domain.Font;
import com.kubeek.service.rest.service.AppService;
import com.kubeek.service.rest.service.FontService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/utils")
public class UtilsRestController {

    private KScreenMessageFactory kScreenMessageFactory;
    private KMessageManager kMessageManager;
    private KScreenParam kScreenParam;
    private FontService fontService;
    private AppService appService;


    @Autowired
    public UtilsRestController(KScreenMessageFactory kScreenMessageFactory, KMessageManager kMessageManager,
                               KScreenParam kScreenParam, FontService fontService, AppService appService){
        this.kScreenMessageFactory = kScreenMessageFactory;
        this.kMessageManager = kMessageManager;
        this.kScreenParam = kScreenParam;
        this.fontService = fontService;
        this.appService = appService;
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public void clear() {
        log.trace("clear");
        try {
            kMessageManager.putImportantQueue(kScreenMessageFactory.getMessageClear());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/bright", method = RequestMethod.GET)
    public void bright() {
        log.trace("bright");
        try {
            kMessageManager.putImportantQueue(kScreenMessageFactory.getMessageSetBright());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/fade", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> fade(@RequestBody @Valid FadeDTO fadeDTO){
        log.trace("post fade " + fadeDTO.toString());
        String retour = "";
        try {
            KMessage kmessage = kScreenMessageFactory.getMessageFade(fadeDTO.getPwm(),fadeDTO.getDelayTime());
            retour=kMessageManager.putImportantQueue(kmessage).toString();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        return UtilsRestController.pushOk(retour);
    }

    @RequestMapping(value = "/fonts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Font>> getFonts(){

        return new ResponseEntity<>(fontService.findAll(), HttpStatus.OK);
    }


    public static  ResponseEntity<?> pushTest() {
        Map<String, Object> retMap = new HashMap<>();
        retMap.put("status", "success");
        ResponseEntity<Map<String, Object>> retValue = new ResponseEntity<Map<String,Object>>(retMap, HttpStatus.I_AM_A_TEAPOT);

        return retValue;
    }


    public static ResponseEntity<?> pushOk(String uuidReturn) {
        Map<String, Object> retMap = new HashMap<>();

        retMap.put("status", "success");
        retMap.put("id", uuidReturn);


        ResponseEntity<Map<String, Object>> retValue = new ResponseEntity<Map<String,Object>>(retMap, HttpStatus.OK);
    return retValue;
    }

    public static ResponseEntity<?> pushBad() {

        Map<String, Object> retMap = new HashMap<>();
        retMap.put("status", "failed");
        ResponseEntity<Map<String, Object>> retValue = new ResponseEntity<Map<String,Object>>(retMap, HttpStatus.INTERNAL_SERVER_ERROR);

        return retValue;
    }




}
