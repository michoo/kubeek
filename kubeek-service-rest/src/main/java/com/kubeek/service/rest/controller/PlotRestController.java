package com.kubeek.service.rest.controller;

import com.kubeek.sdk.device.KScreenParam;
import com.kubeek.sdk.message.KMessage;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.sdk.message.factory.KScreenMessageFactory;
import com.kubeek.service.rest.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.awt.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/plot")
public class PlotRestController {

    private KScreenMessageFactory kScreenMessageFactory;
    private KMessageManager kMessageManager;
    private KScreenParam kScreenParam;


    @Autowired
    public PlotRestController(KScreenMessageFactory kScreenMessageFactory, KMessageManager kMessageManager, KScreenParam kScreenParam){
        this.kScreenMessageFactory = kScreenMessageFactory;
        this.kMessageManager = kMessageManager;
        this.kScreenParam = kScreenParam;
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<?> pushTest() {
        log.trace("plot controller test");

        return UtilsRestController.pushTest();

    }


    @RequestMapping(value="/point", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> plot(@RequestBody @Valid PointDTO pointDTO){
        log.trace("post this plot point " + pointDTO.toString());
        ResponseEntity<?> retValue;
        try {
            KMessage kmessage = kScreenMessageFactory.getMessagePlotPoint(pointDTO.getxPosition(),
                pointDTO.getyPosition(),
                new Color(pointDTO.getRedColor(), pointDTO.getGreenColor(), pointDTO.getBlueColor()));
            String retour=kMessageManager.putImportantQueue(kmessage).toString();
            retValue = UtilsRestController.pushOk(retour);
        } catch (InterruptedException e) {
            e.printStackTrace();
            retValue = UtilsRestController.pushBad();
        }


        return retValue;
    }

    @RequestMapping(value="/line", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> plotLine(@RequestBody @Valid LineDTO lineDTO){
        log.trace("post this line " + lineDTO.toString());
        ResponseEntity<?> retValue;
        try {
            KMessage kmessage = kScreenMessageFactory.getMessagePlotLine(
                lineDTO.getX0(),lineDTO.getY0(),
                lineDTO.getX1(),lineDTO.getY1(),
                new Color(lineDTO.getRedColor(),lineDTO.getGreenColor(),lineDTO.getBlueColor())
            );
            String retour=kMessageManager.putImportantQueue(kmessage).toString();
            retValue = UtilsRestController.pushOk(retour);
        } catch (InterruptedException e) {
            e.printStackTrace();
            retValue = UtilsRestController.pushBad();
        }


        return retValue;
    }

    @RequestMapping(value="/ellipse", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> plotEllipse(@RequestBody @Valid EllipseDTO ellipseDTO){
        log.trace("post this ellipse " + ellipseDTO.toString());
        ResponseEntity<?> retValue;
        try {
            KMessage kmessage = kScreenMessageFactory.getMessagePlotEllipse(
                ellipseDTO.getX0(),ellipseDTO.getY0(),
                ellipseDTO.getX1(), ellipseDTO.getY1(),
                new Color(ellipseDTO.getRedColor(), ellipseDTO.getGreenColor(), ellipseDTO.getBlueColor())
            );
            String retour=kMessageManager.putImportantQueue(kmessage).toString();
            retValue = UtilsRestController.pushOk(retour);
        } catch (InterruptedException e) {
            e.printStackTrace();
            retValue = UtilsRestController.pushBad();
        }


        return retValue;
    }


    @RequestMapping(value="/circle", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> plotCircle(@RequestBody @Valid CircleDTO circleDTO){
        log.trace("post this circle " + circleDTO.toString());
        ResponseEntity<?> retValue;
        try {
            KMessage kmessage = kScreenMessageFactory.getMessagePlotCircle(
                circleDTO.getXm(), circleDTO.getYm(),
                circleDTO.getR(),
                new Color(circleDTO.getRedColor(), circleDTO.getGreenColor(), circleDTO.getBlueColor())
            );
            String retour=kMessageManager.putImportantQueue(kmessage).toString();
            retValue = UtilsRestController.pushOk(retour);
        } catch (InterruptedException e) {
            e.printStackTrace();
            retValue = UtilsRestController.pushBad();
        }


        return retValue;
    }

    @RequestMapping(value="/bezier", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> plotBezier(@RequestBody @Valid BezierDTO bezierDTO){
        log.trace("post this Bezier " + bezierDTO.toString());
        ResponseEntity<?> retValue;
        try {
            KMessage kmessage = kScreenMessageFactory.getMessagePlotBezier(
                bezierDTO.getX0(), bezierDTO.getY0(),
                bezierDTO.getX1(), bezierDTO.getY1(),
                bezierDTO.getX2(), bezierDTO.getY2(),
                new Color(bezierDTO.getRedColor(), bezierDTO.getGreenColor(), bezierDTO.getBlueColor())
            );
            String retour=kMessageManager.putImportantQueue(kmessage).toString();
            retValue = UtilsRestController.pushOk(retour);
        } catch (InterruptedException e) {
            e.printStackTrace();
            retValue = UtilsRestController.pushBad();
        }


        return retValue;
    }

    @RequestMapping(value="/all", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> plotAll(@RequestBody @Valid AllDTO allDTO){
        log.trace("post this All " + allDTO.toString());
        ResponseEntity<?> retValue;
        try {
            KMessage kmessage = kScreenMessageFactory.getMessagePlotAllPoints(
                new Color(allDTO.getRedColor(), allDTO.getGreenColor(), allDTO.getBlueColor())
            );
            String retour=kMessageManager.putImportantQueue(kmessage).toString();
            retValue = UtilsRestController.pushOk(retour);
        } catch (InterruptedException e) {
            e.printStackTrace();
            retValue = UtilsRestController.pushBad();
        }


        return retValue;
    }


}
