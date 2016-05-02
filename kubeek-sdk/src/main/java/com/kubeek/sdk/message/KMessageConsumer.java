package com.kubeek.sdk.message;

public interface KMessageConsumer extends Runnable{

    boolean stopMessageConsumer();

    boolean pauseMessageConsumer();

    boolean continueMessageConsumer();

    boolean startMessageConsumer();

    boolean setStandalone(boolean standalone);
}
