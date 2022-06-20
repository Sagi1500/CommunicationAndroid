package com.example.communicationandroid.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.Message;
import com.example.communicationandroid.Repositories.ContactsListRepository;
import com.example.communicationandroid.Repositories.MessagesListRepository;

import java.util.List;

public class MessagesViewModel extends AndroidViewModel {

    private MessagesListRepository mRepository;
    private LiveData<List<Message>> allMessages;

    public MessagesViewModel(@NonNull Application application) {
        super(application);
        mRepository = new MessagesListRepository(application);
        allMessages = mRepository.getAll();
    }

    public LiveData<List<Message>> getAllMessages() {
        return allMessages;
    }

    public List<Message> getAllMessagesList(){
        return mRepository.getAllMessagesList();
    }

    public MessagesListRepository getmRepository() {
        return mRepository;
    }

    public void addMessage(Message message) {
        mRepository.add(message);
    }
    public void deleteAll(){
        mRepository.deleteAll();
    }

}
