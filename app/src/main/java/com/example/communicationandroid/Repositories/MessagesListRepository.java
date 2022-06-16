package com.example.communicationandroid.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import com.example.communicationandroid.Entities.Message;
import com.example.communicationandroid.Global;

import com.example.communicationandroid.Room.MessageDao;
import com.example.communicationandroid.Room.UserDB;
import com.example.communicationandroid.Room.UsersDatabases;

import java.util.List;

public class MessagesListRepository {

    private MessageDao dao;
    private LiveData<List<Message>> allMessages;
    private UserDB db;


    public MessagesListRepository(Application application) {

        String username = Global.getUsername();
        db = UsersDatabases.getInstance(application.getApplicationContext(), username).getAppDatabase();
        dao = db.messageDao();
        allMessages = dao.index(Global.getCurrentContact());
    }

    public MessageDao getDao() {
        return dao;
    }

    public LiveData<List<Message>> getAll() {
        return allMessages;
    }

    public void add(final Message message) {
        dao.insert(message);
    }


}
