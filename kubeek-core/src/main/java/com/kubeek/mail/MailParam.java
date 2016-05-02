package com.kubeek.mail;

import com.kubeek.sdk.mail.KMailParam;

public class MailParam implements KMailParam {

    private String username;
    private String password;
    private String host;
    private int port;
    private String from;


    public MailParam(String username, String password, String host, int port, String from){
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.from = from;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public String getFrom() {
        return from;
    }
}
