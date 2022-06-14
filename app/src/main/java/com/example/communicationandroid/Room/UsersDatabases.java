package com.example.communicationandroid.Room;

import android.content.Context;

import androidx.room.Room;

public class UsersDatabases {

    private Context context;
    private UserDB userDB;
    private static String dbName;
    private static UsersDatabases instance;

    private UsersDatabases(Context context, String username) {
        this.context = context;

        if(dbName == null || !dbName.equalsIgnoreCase(username)) {
            dbName = username;
        }
        userDB = Room.databaseBuilder(context, UserDB.class, dbName)
                .allowMainThreadQueries()
                .build();
    }

    public String getDatabaseName() {
        return dbName;
    }

    public static synchronized UsersDatabases getInstance(Context mCtx, String username) {
        if (instance == null || dbName == null || !dbName.equalsIgnoreCase(username)) {
            instance = new UsersDatabases(mCtx, username);
        }
        return instance;
    }

    public UserDB getAppDatabase() {
        return userDB;
    }
}
