package com.example.communicationandroid.Api;

import android.widget.Toast;

import com.example.communicationandroid.Entities.Message;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.MyApp;
import com.example.communicationandroid.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationTokenApi {

    Retrofit retrofit;
    NotificationTokenService webNotificationTokenService;
    String authorizationToken;
    private static final int CODE_OK = 200;

    public NotificationTokenApi() {
        authorizationToken = "Bearer " + Global.getToken().getValue();
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webNotificationTokenService = retrofit.create(NotificationTokenService.class);
    }

    public void post (String notificationToken) {
        Call<Void> call = webNotificationTokenService.postNotificationToken(notificationToken, authorizationToken);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                int code = response.code();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
            }
        });
    }
}
