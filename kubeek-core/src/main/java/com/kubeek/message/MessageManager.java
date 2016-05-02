package com.kubeek.message;

import com.kubeek.sdk.message.KMessage;
import com.kubeek.sdk.message.KMessageManager;
import com.kubeek.utils.Utils;
import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public class MessageManager implements KMessageManager {

    private BlockingQueue<KMessage> kubeekMessagesNormalQueue = new LinkedBlockingQueue<>();
    private BlockingQueue<KMessage> kubeekMessagesImportantQueue = new LinkedBlockingQueue<>();


    @Override
    public UUID putNormalQueue(KMessage k) throws InterruptedException {
        UUID uniqueID = Utils.getUniqueID();
        k.setUniqueID(uniqueID);
        kubeekMessagesNormalQueue.put(k);
        return uniqueID;
    }

    @Override
    public KMessage takeNormalQueue() throws InterruptedException {
        return kubeekMessagesNormalQueue.take();
    }

    @Override
    public boolean isNormalQueueEmpty() {
        return kubeekMessagesNormalQueue.isEmpty();
    }

    @Override
    public int getNormalQueueSize() {
        return kubeekMessagesNormalQueue.size();
    }

    @Override
    public UUID putImportantQueue(KMessage k) throws InterruptedException {
        UUID uniqueID = Utils.getUniqueID();
        k.setUniqueID(uniqueID);
        kubeekMessagesImportantQueue.put(k);
        return uniqueID;
    }

    @Override
    public KMessage takeImportantQueue() throws InterruptedException {
        return kubeekMessagesImportantQueue.take();
    }

    @Override
    public boolean isImportantQueueEmpty() {
        return kubeekMessagesImportantQueue.isEmpty();
    }

    @Override
    public int getImportantQueueSize() {
        return kubeekMessagesImportantQueue.size();
    }


}
