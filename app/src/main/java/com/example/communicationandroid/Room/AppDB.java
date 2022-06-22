package com.example.communicationandroid.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.communicationandroid.Entities.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDB extends RoomDatabase {
    public abstract UserDao userDao();
}
