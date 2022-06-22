package com.example.communicationandroid.Room;

import android.content.Context;

import androidx.room.Room;

public class UsersDatabases {

    private Context context;
    private UserDB userDB;
    //    private static String dbName;
    private static UsersDatabases instance;


    private UsersDatabases(Context context, String username) {
        this.context = context;
        userDB = Room.databaseBuilder(context, UserDB.class, username)
                .allowMainThreadQueries()
                .build();

    }

    public static synchronized UsersDatabases getInstance(Context mCtx, String username) {
            instance = new UsersDatabases(mCtx, username);
        return instance;
    }

    public UserDB getAppDatabase() {
        return userDB;
    }


}
