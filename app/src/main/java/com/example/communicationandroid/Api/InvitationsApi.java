package com.example.communicationandroid.Api;

import android.widget.Toast;

import com.example.communicationandroid.Activities.ContactListActivity;
import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.Invitation;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.MyApp;
import com.example.communicationandroid.R;
import com.example.communicationandroid.ViewModel.ContactViewModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InvitationsApi {
    Retrofit retrofit;
    InvitationsService webInvitationsService;

    public InvitationsApi(String serverUrl) {
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(R.string.ServerUrl)+serverUrl+"/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webInvitationsService = retrofit.create(InvitationsService.class);
    }

    public void postInvitation(Invitation invitation, ContactViewModel viewModel, Contact c){
        Call<Contact> call = webInvitationsService.postInvitation(invitation);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                int code = response.code();
                if (201 == code){
                    Contact contact = response.body();
                    //add contact to room
                    viewModel.addContact(c);
                    Toast.makeText(Global.getContext(), "Contact saved", Toast.LENGTH_SHORT).show();
                } else {
                    //post failed
                    Toast.makeText(Global.getContext(), "Contact not saved", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {

            }
        });
    }
}