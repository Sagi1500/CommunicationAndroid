package com.example.communicationandroid.Entities;

public class Transfer {
    private final String from;
    private final String to;
    private final String content;


    public Transfer(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }
}
