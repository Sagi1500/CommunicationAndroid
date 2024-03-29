package com.example.communicationandroid.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Repositories.ContactsListRepository;

import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private ContactsListRepository mRepository;
    private LiveData<List<Contact>> allContacts;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ContactsListRepository(application);
        allContacts = mRepository.getAll();
    }

    public LiveData<List<Contact>> getAllContacts() {
        return allContacts;
    }

    public ContactsListRepository getmRepository() {
        return mRepository;
    }

    public void addContact(Contact contact) {
        mRepository.add(contact);
    }

    public List<Contact> getAllContactsList(){
        return mRepository.getAllContacts();
    }
    public void deleteAllContacts(){
        mRepository.deleteAll();

    }

}
