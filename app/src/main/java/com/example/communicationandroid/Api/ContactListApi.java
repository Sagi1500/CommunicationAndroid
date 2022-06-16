package com.example.communicationandroid.Api;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.Invitation;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.MyApp;
import com.example.communicationandroid.R;
import com.example.communicationandroid.ViewModel.ContactViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactListApi {

    String authorizationToken;
    Retrofit retrofit;
    ContactListService webContactListServiceAPI;

    public ContactListApi() {
        authorizationToken = "Bearer " + Global.getToken().getValue();
        retrofit = new Retrofit.Builder()
                .baseUrl(MyApp.context.getString(R.string.BaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webContactListServiceAPI = retrofit.create(ContactListService.class);
    }

    public void getAllContacts(ContactViewModel viewModel) {
        Call<List<Contact>> call = webContactListServiceAPI.getAllContacts(authorizationToken);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
                List<Contact> contactList = response.body();
                viewModel.deleteAllContacts();
                for (Contact contact : contactList) {
                    viewModel.addContact(contact);
                }
            }

            @Override
            public void onFailure(Call<List<Contact>> call, Throwable t) {

            }
        });


    }

    public void getContact(String id) {
        Call<Contact> call = webContactListServiceAPI.getContact(id, authorizationToken);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                Contact contact = response.body();
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {

            }
        });
    }

    public void addContact(Contact contact, ContactViewModel viewModel) {
        Call<Contact> call = webContactListServiceAPI.addContact(contact, authorizationToken);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                if (201 == response.code()) {
                    Contact c = response.body();
                    InvitationsApi invitationsApi = new InvitationsApi(c.getServer());
                    invitationsApi.postInvitation(
                            new Invitation(Global.getUsername(), c.getId(), "7049"),
                            viewModel,
                            c);
                } else {

                }

            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {

            }
        });
    }

    public void deleteContact(String id) {
        Call<Void> call = webContactListServiceAPI.deleteContact(id, authorizationToken);
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

    public void changeContact(String id, Contact contact) {
        Call<Void> call = webContactListServiceAPI.changeContact(id, authorizationToken, contact);
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