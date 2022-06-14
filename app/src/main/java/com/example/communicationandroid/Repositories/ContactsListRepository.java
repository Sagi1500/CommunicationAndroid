package com.example.communicationandroid.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.example.communicationandroid.Api.ContactApi;
import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Room.UserDB;
import com.example.communicationandroid.Room.ContactDao;
import com.example.communicationandroid.Room.UsersDatabases;

import java.util.LinkedList;
import java.util.List;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;

public class ContactsListRepository {
    private ContactDao dao;
    private LiveData<List<Contact>> allContcats;
    private String username;
//    private ContactListData contactListData;
//    private ContactApi api;


    public ContactsListRepository(Application application,String username) {
        this.username = username;
        UserDB db = UsersDatabases.getInstance(application, username).getAppDatabase();
        dao = db.contactDaoDao();
        allContcats = dao.index();

        //api = new ContactApi(contactListData, dao);
//        api = new ContactApi();
    }


//    class ContactListData extends MutableLiveData<List<Contact>> {
//        public ContactListData() {
//            super();
////            List<Contact> contacts = new LinkedList<>();
////            contacts.add(new Contact("shoval", "shov", "local"));
////            contacts.add(new Contact("sagi", "sagsag", "local"));
////            setValue(contacts);
//            setValue(new LinkedList<>());
//        }
//
//        @Override
//        protected void onActive() {
//            super.onActive();
//
//            new Thread(() -> {
//                    contactListData.postValue(dao.index());
//            }).start();
//        }
//    }

    public LiveData<List<Contact>> getAll() {
        return allContcats;
    }

    public void add(final Contact contact) {
        new AddContactAsyncTask(dao).execute(contact);
//        api.add(contact);
    }

    public void delete(final Contact contact) {
        new DeleteContactAsyncTask(dao).execute(contact);
//        api.delete(contact);
    }
    public void update(final Contact contact) {
        new UpdateContactAsyncTask(dao).execute(contact);
    }
//    public void reload() {
//        new ReloadContactAsyncTask(dao).execute();
////        api.get();
//    }



    private static class AddContactAsyncTask extends AsyncTask<Contact,Void,Void>{
        private ContactDao contactDao;

        private AddContactAsyncTask(ContactDao contactDao){
            this.contactDao = contactDao;
        }
        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.insert(contacts[0]);
            return null;
        }
    }
    private static class DeleteContactAsyncTask extends AsyncTask<Contact,Void,Void>{
        private ContactDao contactDao;

        private DeleteContactAsyncTask(ContactDao contactDao){
            this.contactDao = contactDao;
        }
        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.delete(contacts[0]);
            return null;
        }
    }
    private static class UpdateContactAsyncTask extends AsyncTask<Contact,Void,Void>{
        private ContactDao contactDao;

        private UpdateContactAsyncTask(ContactDao contactDao){
            this.contactDao = contactDao;
        }
        @Override
        protected Void doInBackground(Contact... contacts) {
            contactDao.update(contacts[0]);
            return null;
        }
    }
}
