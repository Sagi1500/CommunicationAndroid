package com.example.communicationandroid.Repositories;

import android.app.Application;

import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Room.AppDB;
import com.example.communicationandroid.Room.ImageDatabases;
import com.example.communicationandroid.Room.UserDao;

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



    public UserDao getDao() {
        return dao;
    }

    public User getUser(String id) {
        return dao.get(id);
    }

    public void updateUser(User user){
        dao.update(user);
    }
    public void add(final User user) {
        User u = dao.get(user.getId());
        if (u==null){
            dao.insert(user);
        }
    }
}
