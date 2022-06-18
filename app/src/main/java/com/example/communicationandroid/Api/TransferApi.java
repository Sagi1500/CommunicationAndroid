package com.example.communicationandroid.Api;

import android.widget.Toast;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TransferApi {


    Retrofit retrofit;
    TransferService webTransferService;

    public TransferApi() {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webTransferService = retrofit.create(TransferService.class);
    }

    public void postTransfer(Transfer transfer,
                             MessagesViewModel viewModelMessage,
                             Message message) {
        Call<Message> call = webTransferService.postTransfer(transfer);
        call.enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                int code = response.code();
                if (201 == code) {
//                    Message m = response.body();
                    message.setReceiverUsername(Global.getCurrentContact());
                    message.changeCreatedFormat();
                    //add Message to room
                    viewModelMessage.addMessage(message);
                    Contact newContact = Global.getContactViewModel().getmRepository().getDao().get(
                            Global.getCurrentContact());
                    newContact.setLast(message.getContent());
                    newContact.setLastdate(message.getCreated());
                    newContact.changeLastdateFormat();
                    Global.getContactViewModel().getmRepository().getDao().update(newContact);
                    Global.getContactsListAdapter().notifyChanged();

                    Toast.makeText(Global.getContext(), "Message saved", Toast.LENGTH_SHORT).show();
                } else {
                    //post failed - delete from server
                    MessagesApi messagesApi = new MessagesApi();
                    messagesApi.deleteMessage(message.getId());
                    Toast.makeText(Global.getContext(), "Message not saved", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {

            }
        });
    }
}
