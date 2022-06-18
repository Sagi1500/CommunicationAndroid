package com.example.communicationandroid.Room;

import android.content.Context;

import androidx.room.Room;


public class ImageDatabases {
    private Context context;
    private AppDB appDB;
    //    private static String dbName;
    private static ImageDatabases instance;


    private ImageDatabases(Context context) {
        this.context = context;
//        dbName = username;
        appDB = Room.databaseBuilder(context, AppDB.class,"Users-img" )
                .allowMainThreadQueries()
                .build();
    }

    public static synchronized ImageDatabases getInstance(Context mCtx) {
        if (instance == null) {
            instance = new ImageDatabases(mCtx);
        }
        return instance;
    }


    public AppDB getAppDB() {
        return appDB;
    }

}

