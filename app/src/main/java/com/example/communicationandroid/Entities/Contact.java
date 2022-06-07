package com.example.communicationandroid.Entities;

public class Contact {

    private String id;
    private String name;
    private String server;
    private String last;
    private String lastDate;


    public Contact(String id, String name, String server) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = null;
        this.lastDate = null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getLast() {
        return last;
    }

    public String getLastDate() {
        return lastDate;
    }
}
