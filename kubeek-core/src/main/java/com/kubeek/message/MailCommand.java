package com.kubeek.message;

import com.kubeek.sdk.command.KMailCommand;
import com.kubeek.sdk.mail.KMailParam;
import com.kubeek.sdk.message.mail.KMailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailCommand implements KMailCommand {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private KMailParam kMailParam;

    @Override
    public void send(KMailMessage message) {
        SimpleMailMessage messageMail = new SimpleMailMessage();
        messageMail.setFrom(kMailParam.getFrom());
        messageMail.setTo(message.getRecipientEmail());
        messageMail.setSubject(message.getEmailSubject());
        messageMail.setText(message.getEmailMessage());

        javaMailSender.send(messageMail);

    }
}
