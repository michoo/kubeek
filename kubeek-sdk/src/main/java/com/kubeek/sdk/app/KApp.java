package com.kubeek.sdk.app;

public abstract class KApp {

    private String name = "Kubeek App";

    public abstract void execute();
    public abstract void stop();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
