package com.example.communicationandroid.Room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.Message;

@Database(entities = {Contact.class, Message.class}, version = 1)
public abstract class UserDB extends RoomDatabase {
    public abstract ContactDao contactDao();
    public abstract MessageDao messageDao();
}
