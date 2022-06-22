package com.example.communicationandroid.Entities;

public class Invitation {
    private final String from;
    private final String to;
    private final String server;

    public Invitation(String from, String to, String server) {
        this.from = from;
        this.to = to;
        this.server = server;
    }
}
