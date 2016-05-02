package com.kubeek.service.rest.controller;

import com.kubeek.sdk.device.KScreenParam;
import com.kubeek.sdk.message.KMessage;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.sdk.message.factory.KScreenMessageFactory;
import com.kubeek.service.rest.domain.CharDTO;
import com.kubeek.service.rest.domain.MessageDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/text")
public class MessageRestController {

    private KScreenMessageFactory kScreenMessageFactory;
    private KMessageManager kMessageManager;
    private KScreenParam kScreenParam;


    @Autowired
    public MessageRestController (KScreenMessageFactory kScreenMessageFactory, KMessageManager kMessageManager, KScreenParam kScreenParam){
        this.kScreenMessageFactory = kScreenMessageFactory;
        this.kMessageManager = kMessageManager;
        this.kScreenParam = kScreenParam;
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> pushTest() {
        log.trace("message controller test");

        return UtilsRestController.pushTest();

    }



    //exemple
    // http POST 192.168.1.130:8080/api/v1/text/scrolltext yPosition:=12 delayTime:=10000 redColor:=150 greenColor:=200 blueColor:=10 message="               Hello world"
    @RequestMapping(value = "/scrolltext", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> pushMessageScrollText(@RequestBody @Valid MessageDTO messageDTO) {
        log.trace("post this message " + messageDTO.toString());

        ResponseEntity<?> retValue;

        try {
            kMessageManager.putImportantQueue(kScreenMessageFactory.getMessageClear());
            KMessage kmessage = kScreenMessageFactory.getMessageScrollText(messageDTO.getyPosition(),
                    messageDTO.getMessage(), kScreenParam.getDefaultFont(), messageDTO.getDelayTime(),
                    new Color(messageDTO.getRedColor(), messageDTO.getGreenColor(), messageDTO.getBlueColor()));
             String retour=kMessageManager.putImportantQueue(kmessage).toString();
            retValue = UtilsRestController.pushOk(retour);

        } catch (InterruptedException e) {
            e.printStackTrace();
            retValue = UtilsRestController.pushBad();
        }

        return retValue;
    }

    @RequestMapping(value = "/char", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> pushChar(@RequestBody CharDTO messageDTO) {
        log.trace("post this char " + messageDTO.toString());
        ResponseEntity<?> retValue;
        try {
            kMessageManager.putImportantQueue(kScreenMessageFactory.getMessageClear());
            KMessage kMessage = kScreenMessageFactory.getMessagePlotChar(messageDTO.getxPosition(), messageDTO.getyPosition(),
                    messageDTO.getMessage(),kScreenParam.getDefaultFont(),new Color(messageDTO.getRedColor(), messageDTO.getGreenColor(), messageDTO.getBlueColor()));

            String retour = kMessageManager.putImportantQueue(kMessage).toString();
            retValue = UtilsRestController.pushOk(retour);
        } catch (InterruptedException e) {
            e.printStackTrace();
            retValue = UtilsRestController.pushBad();
        }

        return retValue;

    }


}
