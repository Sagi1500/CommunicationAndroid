package com.example.communicationandroid.Repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.Message;
import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.Room.AppDB;
import com.example.communicationandroid.Room.ImageDatabases;
import com.example.communicationandroid.Room.UserDB;
import com.example.communicationandroid.Room.UserDao;
import com.example.communicationandroid.Room.UsersDatabases;

import java.util.List;

public class UsersListRepository {

    private UserDao dao;
    private AppDB db;

//    private User currentUser;

    public UsersListRepository(Application application) {
        db = ImageDatabases.getInstance(application.getApplicationContext()).getAppDB();
        dao = db.userDao();
//        currentUser = dao.get(username);
//        if(currentUser==null){
//            dao.in
//        }
    }


//    public User get() {
//        return currentUser;
//    }

    public UserDao getDao() {
        return dao;
    }
//
//    public User getCurrentUser() {
//        return currentUser;
//    }

    public void add(final User user) {
        dao.insert(user);
    }
}
