package com.example.communicationandroid.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.communicationandroid.Api.ContactApi;
import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Room.ContactDao;

import java.util.LinkedList;
import java.util.List;

public class ContactsListRepository {
//    private ContactDao dao;
    private ContactListData contactListData;
//    private ContactApi api;

    public ContactsListRepository() {
//        LocalDatabase db = LocalDatabase.getInstance();
//        dao = db.contactDao();
        contactListData = new ContactListData();
//        api = new ContactApi(contactListData, dao);

    }

    class ContactListData extends MutableLiveData<List<Contact>> {
        public ContactListData() {
            super();
            List<Contact> contacts = new LinkedList<>();
            contacts.add(new Contact("shoval","shov","local"));
            contacts.add(new Contact("sagi","sagsag","local"));
            setValue(contacts);
//            setValue(new LinkedList<>());
        }

        @Override
        protected void onActive() {
            super.onActive();

//            new Thread(() -> {
//                    contactListData.postValue(dao.get());
//            }).start();
        }
    }

    public LiveData<List<Contact>> getAll() {
        return contactListData;
    }

    public void add(final Contact contact) {
//        api.add(contact);
    }

    public void delete(final Contact contact) {
//        api.delete(contact);
    }

    public void reload() {
//        api.get();
    }
}
