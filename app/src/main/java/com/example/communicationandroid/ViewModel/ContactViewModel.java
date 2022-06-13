package com.example.communicationandroid.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

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

    public LiveData<List<Contact>> get() {
        return allContacts;
    }

    public void add(Contact contact) {
        mRepository.add(contact);
    }

    public void delete(Contact contact) {
        mRepository.delete(contact);
    }
    public void update(Contact contact){
        mRepository.update(contact);
    }
//    public void reload() {
//        mRepository.reload();
//    }

}
