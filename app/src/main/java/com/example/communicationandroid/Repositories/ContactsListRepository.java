package com.example.communicationandroid.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.Room.ContactDao;
import com.example.communicationandroid.Room.UserDB;
import com.example.communicationandroid.Room.UsersDatabases;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;

public class ContactsListRepository {
    private ContactDao dao;
    private LiveData<List<Contact>> allContcats;
    private UserDB db;
//    private ContactApi api;

    public ContactsListRepository(Application application) {

        String username = Global.getUsername();
        db = UsersDatabases.getInstance(application.getApplicationContext(), username).getAppDatabase();
        dao = db.contactDao();
        allContcats = dao.index();
        //api = new ContactApi(contactListData, dao);
        //api = new ContactApi();
    }

    public ContactDao getDao() {
        return dao;
    }

    public LiveData<List<Contact>> getAll() {
        return allContcats;
    }


    /*
        class ContactListData extends MutableLiveData<List<Contact>> {
        public ContactListData() {
            super();
            List<Contact> contacts = new LinkedList<>();
            contacts.add(new Contact("shoval", "shov", "local"));
            contacts.add(new Contact("sagi", "sagsag", "local"));
            setValue(contacts);
            setValue(new LinkedList<>());
        }

        @Override
        protected void onActive() {
            super.onActive();

            new Thread(() -> {
                    contactListData.postValue(dao.index());
            }).start();
        }
    }
     */


    public void add(final Contact contact) {
        dao.insert(contact);
    }

//    public void add(final Contact contact) {
//        new AddContactAsyncTask(dao).execute(contact);
////        api.add(contact);
//    }




//    private static class AddContactAsyncTask extends AsyncTask<Contact,Void,Void>{
//        private ContactDao contactDao;
//
//        private AddContactAsyncTask(ContactDao contactDao){
//            this.contactDao = contactDao;
//        }
//        @Override
//        protected Void doInBackground(Contact... contacts) {
//            contactDao.insert(contacts[0]);
//            return null;
//        }
//    }
//    private static class DeleteContactAsyncTask extends AsyncTask<Contact,Void,Void>{
//        private ContactDao contactDao;
//
//        private DeleteContactAsyncTask(ContactDao contactDao){
//            this.contactDao = contactDao;
//        }
//        @Override
//        protected Void doInBackground(Contact... contacts) {
//            contactDao.delete(contacts[0]);
//            return null;
//        }
//    }
//    private static class UpdateContactAsyncTask extends AsyncTask<Contact,Void,Void>{
//        private ContactDao contactDao;
//
//        private UpdateContactAsyncTask(ContactDao contactDao){
//            this.contactDao = contactDao;
//        }
//        @Override
//        protected Void doInBackground(Contact... contacts) {
//            contactDao.update(contacts[0]);
//            return null;
//        }
//    }
}
