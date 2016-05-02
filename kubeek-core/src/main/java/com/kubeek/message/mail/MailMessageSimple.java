package com.kubeek.message.mail;

import com.kubeek.sdk.message.mail.KMailMessage;

import java.util.ArrayList;
import java.util.List;


public class MailMessageSimple extends KMailMessage{

    private String recipient;
    private String emailSubject;
    private String emailMessage;


    public MailMessageSimple(String recipient, String emailSubject, String emailMessage){
        this.recipient = recipient;
        this.emailSubject = emailSubject;
        this.emailMessage = emailMessage;
    }

    @Override
    public String getRecipientEmail() {
        return recipient;
    }

    @Override
    public void setRecipientEmail(String recipient) {
        this.recipient = recipient;

    }

    @Override
    public String getEmailSubject() {
        return emailSubject;
    }

    @Override
    public void setEmailSubject(String subject) {
        this.emailSubject = subject;
    }

    @Override
    public String getEmailMessage() {
        return emailMessage;
    }

    @Override
    public void setEmailMessage(String message) {
        emailMessage = message;
    }
}
