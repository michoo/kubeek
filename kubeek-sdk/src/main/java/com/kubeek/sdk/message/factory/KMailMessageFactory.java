package com.kubeek.sdk.message.factory;

import com.kubeek.sdk.message.mail.KMailMessage;

public interface KMailMessageFactory {

    KMailMessage getSimpleMessage(String recipient, String subject, String message);
}
