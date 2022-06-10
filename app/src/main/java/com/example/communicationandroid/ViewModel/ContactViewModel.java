package com.example.communicationandroid.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Repositories.ContactsListRepository;

import java.util.List;

public class ContactViewModel extends ViewModel {
    private ContactsListRepository mRepository;
    private LiveData<List<Contact>> contacts;

    public ContactViewModel() {
        mRepository = new ContactsListRepository();
        contacts = mRepository.getAll();
    }
//
    public LiveData<List<Contact>> get() {
        return contacts;
    }
//
//    public void add(Contact contact) {
//        mRepository.add(contact);
//    }
//
//    public void delete(Contact contact) {
//        mRepository.delete(contact);
//    }
//
//    public void reload() {
//        mRepository.reload();
//    }

}
