package com.example.communicationandroid.Entities;

import android.annotation.SuppressLint;

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
    private String receiverUsername;
    @NonNull
    private String content;
    private String created;

    @NonNull
    private final Boolean sent;

    @SuppressLint("SimpleDateFormat")
    public Message(@NonNull String receiverUsername, @NonNull String content, @NonNull Boolean sent) {
        this.receiverUsername = receiverUsername;
        this.content = content;
        this.sent = sent;
//        this.created = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
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

    public void setCreated(String created) {
        this.created = created;
    }

    @NonNull
    public String getReceiverUsername() {
        return receiverUsername;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public String getCreated() {
        return created;
    }

    @NonNull
    public Boolean getSent() {
        return sent;
    }

    public void setReceiverUsername(@NonNull String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }
}
