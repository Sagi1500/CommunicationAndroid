package com.example.communicationandroid;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObservable;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.communicationandroid.Activities.ContactListActivity;
import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.ViewModel.ContactViewModel;
import com.example.communicationandroid.adapter.ContactsListAdapter;

public class Global {
    private static String appToken;
    private static String currentContact;
    private static String username;
    private static User currentUser;
    private static MutableLiveData<String> token;
    private static Context context;
    private static ContactViewModel contactViewModel;
    private static ContactsListAdapter contactsListAdapter;
    private static ViewModelStoreOwner viewModelStoreOwner;

    public static void setToken(MutableLiveData<String> token, String name, String errorMessage) {
        if (token == null) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }
        username = name;
        Global.token = token;
        Intent intent = new Intent(context, ContactListActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public static MutableLiveData<String> getToken() {
        return token;
    }

    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        Global.context = context;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        Global.username = username;
    }

    public final static String contact_Key = "contact";

    public static String getCurrentContact() {
        return currentContact;
    }

    public static void setCurrentContact(String currentContact) {
        Global.currentContact = currentContact;
    }

    public static String getAppToken() {
        return appToken;
    }

    public static void setAppToken(String appToken) {
        Global.appToken = appToken;
    }

    public static ContactViewModel getContactViewModel() {
        return contactViewModel;
    }

    public static void setContactViewModel(ContactViewModel contactViewModel) {
        Global.contactViewModel = contactViewModel;
    }

    public static ContactsListAdapter getContactsListAdapter() {
        return contactsListAdapter;
    }

    public static void setContactsListAdapter(ContactsListAdapter contactsListAdapter) {
        Global.contactsListAdapter = contactsListAdapter;
    }

    public static User getCurrentUser() {
        return Global.currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Global.currentUser = currentUser;
    }

    public static ViewModelStoreOwner getViewModelStoreOwner() {
        return viewModelStoreOwner;
    }

    public static void setViewModelStoreOwner(ViewModelStoreOwner viewModelStoreOwner) {
        Global.viewModelStoreOwner = viewModelStoreOwner;
    }
}
