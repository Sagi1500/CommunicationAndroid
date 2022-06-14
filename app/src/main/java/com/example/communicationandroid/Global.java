package com.example.communicationandroid;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.communicationandroid.Activities.ContactListActivity;

public class Global {
    private static MutableLiveData<String>  token;
    private static Context context;

    public static void setToken(MutableLiveData<String> token) {
        if(token==null){
            Toast.makeText(context, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
            return;
        }
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
}
