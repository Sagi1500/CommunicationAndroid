package com.example.communicationandroid.Repositories;

import androidx.lifecycle.LiveData;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.Room.ContactDao;
import com.example.communicationandroid.Room.UserDB;
import com.example.communicationandroid.Room.UsersDatabases;

import java.util.List;

import android.app.Application;

public class ContactsListRepository {
    private ContactDao dao;
    private LiveData<List<Contact>> allContacts;
    private UserDB db;

    public ContactsListRepository(Application application) {

        String username = Global.getUsername();
        db = UsersDatabases.getInstance(application.getApplicationContext(), username).getAppDatabase();
        dao = db.contactDao();
        allContacts = dao.index();
    }

    public ContactDao getDao() {
        return dao;
    }

    public LiveData<List<Contact>> getAll() {
        return allContacts;
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
