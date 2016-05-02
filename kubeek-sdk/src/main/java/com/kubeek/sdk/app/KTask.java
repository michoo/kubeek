package com.kubeek.sdk.app;

public abstract class KTask implements Runnable {

    private boolean continueValue=true;
    private boolean pauseValue=false;
    private String name = "Kubeek Task";



    public boolean stopKTask() {
        continueValue=false;
        return continueValue;
    }


    public boolean pauseKTask() {
        pauseValue=true;
        return pauseValue;
    }


    public boolean continueKTask(){

        pauseValue = false;
        return pauseValue;

    }


    public boolean startKTask() {
        continueValue=true;
        return continueValue;
    }


    public boolean getContinueValue(){
        return continueValue;
    }

    public boolean getPauseValue(){
        return pauseValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
