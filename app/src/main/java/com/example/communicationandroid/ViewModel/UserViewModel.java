package com.example.communicationandroid.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Repositories.UsersListRepository;


public class UserViewModel extends AndroidViewModel {

    private UsersListRepository mRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UsersListRepository(application);
    }

    public User getUser(String id) {
        return mRepository.getUser(id);
    }
    public void updateUser(User user){
        mRepository.updateUser(user);
    }

    public void addUser(User user) {
        mRepository.add(user);
    }
}
