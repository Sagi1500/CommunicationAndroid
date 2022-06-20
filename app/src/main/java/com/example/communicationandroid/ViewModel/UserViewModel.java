package com.example.communicationandroid.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.communicationandroid.Entities.Message;
import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Repositories.ContactsListRepository;
import com.example.communicationandroid.Repositories.UsersListRepository;


public class UserViewModel extends AndroidViewModel {

    private UsersListRepository mRepository;
//    private User currentUser;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UsersListRepository(application);
//        currentUser = mRepository.getCurrentUser();
    }

    public UsersListRepository getmRepository() {
        return mRepository;
    }

    public User getUser(String id) {
        return mRepository.getUser(id);
    }

    public void addUser(User user) {
        mRepository.add(user);
    }
}
