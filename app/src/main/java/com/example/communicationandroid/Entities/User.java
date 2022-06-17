package com.example.communicationandroid.Entities;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class User {

    @PrimaryKey
    @NonNull
    private String id;
    @NonNull
    private String password;

    private String image;

    @Ignore
    public User(@NonNull String id, @NonNull String password) {
        this.id = id;
        this.password = password;
    }

    public User(@NonNull String id, @NonNull String password, String image) {
        this.id = id;
        this.password = password;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
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
