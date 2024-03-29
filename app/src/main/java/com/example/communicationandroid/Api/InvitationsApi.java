package com.example.communicationandroid.Api;

import android.widget.Toast;

import androidx.annotation.NonNull;

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
    boolean valid;


    public InvitationsApi(Contact c) {
        try {
            valid = true;
            retrofit = new Retrofit.Builder()
                    .baseUrl(MyApp.context.getString(R.string.ServerStartUrl) + c.getServer() + "/api/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            webInvitationsService = retrofit.create(InvitationsService.class);
        }catch (Exception e){
            valid = false;
            ContactListApi contactListApi = new ContactListApi();
            contactListApi.deleteContact(c.getId());
            Toast.makeText(Global.getContext(), "Contact not saved", Toast.LENGTH_SHORT).show();
        }
    }
    public boolean isValid(){
        return valid;
    }
    public void postInvitation(Invitation invitation, ContactViewModel viewModel, Contact c){
        Call<Contact> call = webInvitationsService.postInvitation(invitation);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(@NonNull Call<Contact> call, @NonNull Response<Contact> response) {
                int code = response.code();
                if (201 == code){
                    //add contact to room
                    c.changeLastdateFormat();
                    viewModel.addContact(c);
                    Toast.makeText(Global.getContext(), "Contact saved", Toast.LENGTH_SHORT).show();
                } else {
                    //post failed - delete contact
                    ContactListApi contactListApi = new ContactListApi();
                    contactListApi.deleteContact(c.getId());
                    Toast.makeText(Global.getContext(), "Contact not saved", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Contact> call, @NonNull Throwable t) {

            }
        });
    }
}
