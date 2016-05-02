package com.kubeek.sdk.command;

import com.kubeek.sdk.message.mail.KMailMessage;

public interface KMailCommand {

    void send(KMailMessage message);
}
