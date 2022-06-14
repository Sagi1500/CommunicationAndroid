package com.example.communicationandroid.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.communicationandroid.Entities.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class UserDB extends RoomDatabase {
    public abstract ContactDao contactDaoDao();

}
