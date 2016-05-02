package com.kubeek.message.factory;

import com.kubeek.message.mail.MailMessageSimple;
import com.kubeek.sdk.message.factory.KMailMessageFactory;
import com.kubeek.sdk.message.mail.KMailMessage;

public class MailMessageFactory implements KMailMessageFactory {

    @Override
    public KMailMessage getSimpleMessage(String recipient, String subject, String message) {
        return new MailMessageSimple(recipient,subject,message);
    }
}
