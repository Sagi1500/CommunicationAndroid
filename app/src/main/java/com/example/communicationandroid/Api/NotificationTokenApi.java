package com.example.communicationandroid.Api;

import androidx.annotation.NonNull;

import com.example.communicationandroid.Global;
import com.example.communicationandroid.MyApp;
import com.example.communicationandroid.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NotificationTokenApi {

    Retrofit retrofit;
    NotificationTokenService webNotificationTokenService;
    String authorizationToken;

    public NotificationTokenApi() {
        authorizationToken = "Bearer " + Global.getToken().getValue();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(R.string.ServerStartUrl)+Global.getServer()+"/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webNotificationTokenService = retrofit.create(NotificationTokenService.class);
    }

    public void post (String notificationToken) {
        Call<Void> call = webNotificationTokenService.postNotificationToken(notificationToken, authorizationToken);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                int code = response.code();
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {
            }
        });
    }
}
