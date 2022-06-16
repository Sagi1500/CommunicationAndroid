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

    public void add(Contact contact) {
        dao.insert(contact);
    }

    public List<Contact> getAllContacts() {
        return dao.getAllContacts();
    }

    public void deleteAll() {
        List<Contact> contacts = getAllContacts();
        if (contacts != null) {
            for (Contact contact : contacts) {
                dao.delete(contact);
            }
        }
    }
}
