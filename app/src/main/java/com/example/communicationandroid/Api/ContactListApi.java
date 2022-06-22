package com.example.communicationandroid.Api;

import androidx.annotation.NonNull;
import androidx.room.Entity;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.Invitation;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.MyApp;
import com.example.communicationandroid.R;
import com.example.communicationandroid.ViewModel.ContactViewModel;

import java.io.Serializable;
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
                .baseUrl(MyApp.context.getString(R.string.ServerStartUrl) + Global.getServer() + "/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        webContactListServiceAPI = retrofit.create(ContactListService.class);
    }

    public void getAllContacts(ContactViewModel viewModel) {
        Call<List<Contact>> call = webContactListServiceAPI.getAllContacts(authorizationToken);
        call.enqueue(new Callback<List<Contact>>() {
            @Override
            public void onResponse(@NonNull Call<List<Contact>> call, @NonNull Response<List<Contact>> response) {
                List<Contact> contactList = response.body();
                viewModel.deleteAllContacts();
                for (Contact contact : contactList) {
                    contact.changeLastdateFormat();
                    viewModel.addContact(contact);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Contact>> call, @NonNull Throwable t) {

            }
        });


    }

    public void getContact(String id) {
        Call<Contact> call = webContactListServiceAPI.getContact(id, authorizationToken);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(@NonNull Call<Contact> call, @NonNull Response<Contact> response) {
                Contact contact = response.body();
            }

            @Override
            public void onFailure(@NonNull Call<Contact> call, @NonNull Throwable t) {

            }
        });
    }

    public void addContact(Contact contact, ContactViewModel viewModel) {
        Call<Contact> call = webContactListServiceAPI.addContact(contact, authorizationToken);
        call.enqueue(new Callback<Contact>() {
            @Override
            public void onResponse(@NonNull Call<Contact> call, @NonNull Response<Contact> response) {
                if (201 == response.code()) {
                    Contact c = response.body();
                    InvitationsApi invitationsApi = new InvitationsApi(c);
                    if (invitationsApi.isValid()) {
                        invitationsApi.postInvitation(
                                new Invitation(Global.getUsername(), c.getId(), "localhost:"+Global.getServer()),
                                viewModel,
                                c);
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Contact> call, @NonNull Throwable t) {

            }
        });
    }

    public void deleteContact(String id) {
        Call<Void> call = webContactListServiceAPI.deleteContact(id, authorizationToken);
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

    @Entity
    public class temp implements Serializable {
        private String name;
        private String server;


        public temp(String name, String server) {
            this.name = name;
            this.server = server;
        }
    }

    public void changeContact(String id, Contact contact) {
        Call<Void> call = webContactListServiceAPI.changeContact(id, authorizationToken, new temp(contact.getName(), "localhost:" + contact.getServer()));
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(@NonNull Call<Void> call, @NonNull Response<Void> response) {
                int code = response.code();//204
                if (204 == code) {
                    Global.getContactViewModel().getmRepository().getDao().update(contact);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Void> call, @NonNull Throwable t) {

            }
        });
    }
}