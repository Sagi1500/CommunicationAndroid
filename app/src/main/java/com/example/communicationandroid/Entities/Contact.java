package com.example.communicationandroid.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Contact implements Serializable {

    @PrimaryKey()
    @NonNull
    private final String id;

    private String name;

    private String server;

    private String last;

    private String lastdate;


    public Contact(@NonNull String id, String name, String server) {
        this.id = id;
        this.name = name;
        this.server = server;
        this.last = null;
        this.lastdate = null;
    }

    @NonNull
    public String getId() {
        return id;
    }

    @NonNull
    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }


    public String getName() {
        return name;
    }

    public String getServer() {
        //localhost:7049
        if (server != null) {
            if (server.length() > 10) {
                //7040
                return server.substring(10);
            }
        }
        return null;
    }

    public void setServer(String server) {

        this.server = server;


    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setLastdate(String lastdate) {
        this.lastdate = lastdate;
    }

    public String getLast() {
        return last;
    }

    public String getLastdate() {
        return lastdate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void changeLastdateFormat() {
        if (lastdate != null) {
            //2022-06-17T11:24:59.3527188+03:00
            String year = this.lastdate.substring(0, 4);
            String month = this.lastdate.substring(5, 7);
            String day = this.lastdate.substring(8, 10);
            String time = this.lastdate.substring(11, 16);
            this.lastdate = time + " " + day + "/" + month + "/" + year;
            //this.lastdate = time;
        } else {
            lastdate = "";
            last = "";
        }
    }
}
