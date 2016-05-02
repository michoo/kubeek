package com.kubeek.sdk.command;

import com.kubeek.sdk.message.screen.KScreenMessage;

public interface KScreenCommand {
    void send(KScreenMessage message);
}
