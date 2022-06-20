package com.example.communicationandroid.Api;

import androidx.annotation.NonNull;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.Invitation;
import com.example.communicationandroid.Entities.Message;
import com.example.communicationandroid.Entities.Transfer;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.MyApp;
import com.example.communicationandroid.R;
import com.example.communicationandroid.ViewModel.ContactViewModel;
import com.example.communicationandroid.ViewModel.MessagesViewModel;
import com.example.communicationandroid.adapter.ContactsListAdapter;

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

    public void getAllMessages(MessagesViewModel viewModel) {
        Call<List<Message>> call = webMessagesServiceAPI.getAllMessages(Global.getCurrentContact(), authorizationToken);
        call.enqueue(new Callback<List<Message>>() {
            @Override
            public void onResponse(Call<List<Message>> call, Response<List<Message>> response) {
                if(200 == response.code()){
                    List<Message> messageList = response.body();
                    viewModel.deleteAll();
                    for (Message message : messageList) {
                        message.setReceiverUsername(Global.getCurrentContact());
                        message.changeCreatedFormat();
                        viewModel.addMessage(message);
                    }
                } else {
                }
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

    public void addMessage(MessagesViewModel messageViewModel, Message message) {
        Call<Message> call = webMessagesServiceAPI.addMessage(Global.getCurrentContact(), message, authorizationToken);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(@NonNull Call<Message> call, @NonNull Response<Message> response) {
                if(201 == response.code()){
                    Message m = response.body();
                    TransferApi transferApi = new TransferApi();
                    transferApi.postTransfer(new Transfer(Global.getUsername(),
                            Global.getCurrentContact(),
                            m.getContent()),
                            messageViewModel,
                            m);
                } else {

                }
            }

            @Override
            public void onFailure(@NonNull Call<Message> call, @NonNull Throwable t) {

            }
        });
    }

    public void deleteMessage(int messageId) {
        Call<Void> call = webMessagesServiceAPI.deleteMessage(Global.getCurrentContact(), messageId, authorizationToken);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                int code = response.code();//204
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
