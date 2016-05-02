package com.kubeek.sdk.app;

import java.util.List;

public interface KAppManager extends Runnable{


    void addApp(KApp kApp);

    boolean stopAppManager();

    boolean pauseAppManager();

    boolean continueAppManager();

    List<String> getAppList();

}
