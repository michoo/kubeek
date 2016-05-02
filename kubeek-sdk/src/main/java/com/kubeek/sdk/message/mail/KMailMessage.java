package com.kubeek.sdk.message.mail;

import com.kubeek.sdk.message.KMessage;

import java.util.List;

public abstract class KMailMessage extends KMessage {

    public abstract String getRecipientEmail();

    public abstract void setRecipientEmail(String recipient);

    public abstract String getEmailSubject();

    public abstract void setEmailSubject(String subject);

    public abstract String getEmailMessage();

    public abstract void setEmailMessage(String message);

}
