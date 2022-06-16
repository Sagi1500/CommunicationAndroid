package com.example.communicationandroid.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.communicationandroid.Api.ContactListApi;
import com.example.communicationandroid.Api.MessagesApi;
import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.Message;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.Listeners.ContactListener;
import com.example.communicationandroid.R;
import com.example.communicationandroid.ViewModel.ContactViewModel;
import com.example.communicationandroid.adapter.ContactsListAdapter;
import com.example.communicationandroid.databinding.ActivityContactListBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ContactListActivity extends AppCompatActivity implements ContactListener {

    private ActivityContactListBinding binding;
    private ContactViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        /***
         * Api
         */
        MessagesApi messagesApi = new MessagesApi();
        messagesApi.addMessage("1b",new Message("1b","bye",true));
        messagesApi.getAllMessages("1b");


        RecyclerView lstContacts = binding.lstContacts;
        lstContacts.setLayoutManager(new LinearLayoutManager(this));

        final ContactsListAdapter adapter = new ContactsListAdapter(this,this);
        lstContacts.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        viewModel.getAllContacts().observe(this, contacts -> adapter.setContacts(contacts));


        FloatingActionButton buttonAddContact = binding.contactListBtnAdd;

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String username = data.getStringExtra(AddContactActivity.EXTRA_USERNAME);
                        String nickname = data.getStringExtra(AddContactActivity.EXTRA_NICKNAME);
                        String server = data.getStringExtra(AddContactActivity.EXTRA_SERVER);
                        Global.setContext(this);
                        ContactListApi contactListApi = new ContactListApi();
                        Contact contact = new Contact(username, nickname, server);
                        contactListApi.addContact(contact,viewModel);

                    }
                });

        buttonAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(ContactListActivity.this, AddContactActivity.class);
            launcher.launch(intent);
        });

        binding.contactListLogout.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        });

        binding.contactListSettings.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });

        ContactListApi contactListApi = new ContactListApi();
        contactListApi.getAllContacts(viewModel);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                contactListApi.getAllContacts(viewModel);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }
    
    @Override
    public void onContactClicked(Contact contact) {
        Global.setCurrentContact(contact.getId());
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra(Global.contact_Key, contact);
        startActivity(intent);
    }
}
