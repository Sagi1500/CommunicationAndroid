package com.example.communicationandroid.Entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String password;

    private byte[] image;

    @Ignore
    public User(@NonNull String id, @NonNull String password) {
        this.id = id;
        this.password = password;
    }

    public User(@NonNull String id, @NonNull String password, byte[] image) {
        this.id = id;
        this.password = password;
        this.image = image;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
