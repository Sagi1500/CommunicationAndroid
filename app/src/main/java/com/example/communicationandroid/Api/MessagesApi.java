package com.example.communicationandroid.Api;

import androidx.annotation.NonNull;

import com.example.communicationandroid.Entities.Message;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.MyApp;
import com.example.communicationandroid.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MessagesApi {

    String authorizationToken;
    Retrofit retrofit;
    MessagesService webMessagesServiceAPI;

    public MessagesApi() {
        authorizationToken = "Bearer " + Global.getToken().getValue();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webMessagesServiceAPI = retrofit.create(MessagesService.class);
    }

    public void getAllMessages(String id) {
        Call<List<Message>> call = webMessagesServiceAPI.getAllMessages(id, authorizationToken);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                int code = response.code();//200
            }

            @Override
            public void onFailure(Call<List<Message>> call, Throwable t) {

            }
        });


    }

    /**
     * didn't check with server!
     */
    public void getMessage(String id, String id2) {
        Call<Message> call = webMessagesServiceAPI.getMessage(id, id2, authorizationToken);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(@NonNull Call<Message> call, @NonNull Response<Message> response) {
                Message Message = response.body();
            }

            @Override
            public void onFailure(@NonNull Call<Message> call, Throwable t) {

            }
        });
    }

    public void addMessage(String id, Message message) {
        Call<Message> call = webMessagesServiceAPI.addMessage(id, message, authorizationToken);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(@NonNull Call<Message> call, @NonNull Response<Message> response) {
                int code = response.code();//201
            }

            @Override
            public void onFailure(@NonNull Call<Message> call, @NonNull Throwable t) {

            }
        });
    }
    /**
     * didn't check with server!
     */
    public void deleteMessage(String id, String id2) {
        Call<Void> call = webMessagesServiceAPI.deleteMessage(id, id2, authorizationToken);
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
    /**
     * didn't check with server!
     */
    public void changeMessage(String id, String id2, Message message) {
        Call<Void> call = webMessagesServiceAPI.changeMessage(id, id2, authorizationToken, message);
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
