package com.example.communicationandroid.Api;

import android.content.Intent;

import androidx.lifecycle.MutableLiveData;

import com.example.communicationandroid.ContactListActivity;
import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.LoginActivity;
import com.example.communicationandroid.MyApp;
import com.example.communicationandroid.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginApi {
    public static final int CODE_OK =200;


    String token;
    Retrofit retrofit;
    LoginService webServiceAPI;

    public LoginApi(){
        token = null;
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        webServiceAPI = retrofit.create(LoginService.class);
    }


    public void post(User user) {
        Call<String> call = webServiceAPI.postLogin(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.code()==CODE_OK){
                    token = response.body();
                }else{
                    //"Username or password is invalid"
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {}
        });
    }

}
