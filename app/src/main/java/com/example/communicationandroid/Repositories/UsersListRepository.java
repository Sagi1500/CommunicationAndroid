package com.example.communicationandroid.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.Message;
import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.Room.UserDB;
import com.example.communicationandroid.Room.UserDao;
import com.example.communicationandroid.Room.UsersDatabases;

import java.util.List;

public class UsersListRepository {

    private UserDao dao;
    private UserDB db;
    private User currentUser;

    public UsersListRepository(Application application) {
        String username = Global.getUsername();
        db = UsersDatabases.getInstance(application.getApplicationContext(), username).getAppDatabase();
        dao = db.userDao();
        currentUser = dao.get(username);
    }


    public User get() {
        return currentUser;
    }

    public UserDao getDao() {
        return dao;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void add(final User user) {
        dao.insert(user);
    }
}
