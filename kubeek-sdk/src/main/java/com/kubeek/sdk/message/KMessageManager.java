package com.kubeek.sdk.message;

import java.util.UUID;

public interface KMessageManager {


    UUID putNormalQueue(KMessage k) throws InterruptedException;

    KMessage takeNormalQueue() throws InterruptedException;

    boolean isNormalQueueEmpty();

    int getNormalQueueSize();



    UUID putImportantQueue(KMessage k) throws InterruptedException;

    KMessage takeImportantQueue() throws InterruptedException;

    boolean isImportantQueueEmpty();

    int getImportantQueueSize();


}
