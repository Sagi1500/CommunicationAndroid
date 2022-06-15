package com.example.communicationandroid.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Calendar;

@Entity
public class Message {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private final String receiverUsername;

    @NonNull
    private String content;
    private String date;

    @NonNull
    private final Boolean sent;

    public Message(@NonNull String receiverUsername, @NonNull String content, @NonNull Boolean sent) {
        this.receiverUsername = receiverUsername;
        this.content = content;
        this.sent = sent;
        this.date = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @NonNull
    public String getReceiverUsername() {
        return receiverUsername;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    @NonNull
    public Boolean getSent() {
        return sent;
    }
}
