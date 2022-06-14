//package com.example.communicationandroid.Room;
//
//
//import android.content.Context;
//import android.os.AsyncTask;
//
//import androidx.annotation.NonNull;
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//import androidx.sqlite.db.SupportSQLiteDatabase;
//
//import com.example.communicationandroid.Entities.Contact;
//
//@Database(entities = {Contact.class}, version = 1)
//public abstract class AppDB extends RoomDatabase {
//
//    private static AppDB instance;
//    public abstract ContactDao contactDaoDao();
//
//    public static synchronized AppDB getInstance(Context context) {
//        if(instance==null){
//            instance= Room.databaseBuilder(context.getApplicationContext(),
//                    AppDB.class,"localDB")
//                    .fallbackToDestructiveMigration()
//                    .addCallback(roomCallBack)
//                    .build();
//        }
//        return instance;
//    }
//
//    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
//        @Override
//        public void onCreate(@NonNull SupportSQLiteDatabase db) {
//            super.onCreate(db);
//            new  PopulateDbAsyncTask(instance).execute();
//        }
//    };
//
//
//    private static class  PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
//        private ContactDao contactDao;
//        private PopulateDbAsyncTask(AppDB db){
//            contactDao = db.contactDaoDao();
//        }
//        @Override
//        protected Void doInBackground(Void... voids) {
//            contactDao.insert(new Contact("shoval","shov","local"));
//            contactDao.insert(new Contact("sagi","sagsag","local"));
//            return null;
//        }
//    }
//}

