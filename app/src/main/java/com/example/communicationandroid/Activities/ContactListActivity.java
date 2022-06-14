package com.example.communicationandroid.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.R;
import com.example.communicationandroid.ViewModel.ContactViewModel;
import com.example.communicationandroid.adapter.ContactsListAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactListActivity extends AppCompatActivity {
    public static final int ADD_CONTACT_REQUEST = 1;
    private ContactViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);


        MutableLiveData<String> token = Global.getToken();
        FloatingActionButton buttonAddContact = findViewById(R.id.contactList_btnAdd);

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String username = data.getStringExtra(AddContactActivity.EXTRA_USERNAME);
                        String nickname = data.getStringExtra(AddContactActivity.EXTRA_NICKNAME);
                        String server = data.getStringExtra(AddContactActivity.EXTRA_SERVER);


                        Contact contact = new Contact(username, nickname, server);
                        viewModel.add(contact);

                        Toast.makeText(this, "Contact saved", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Contact not saved", Toast.LENGTH_SHORT).show();
                    }

                });

        buttonAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactListActivity.this, AddContactActivity.class);
                launcher.launch(intent);
            }
        });




        RecyclerView lstContacts = findViewById(R.id.lstContacts);

        lstContacts.setLayoutManager(new LinearLayoutManager(this));

        lstContacts.setHasFixedSize(true);

        final ContactsListAdapter adapter = new ContactsListAdapter(this);

        lstContacts.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        viewModel.get().observe(this, contacts -> adapter.setContacts(contacts));
//        ContactApi contactApi = new ContactApi();
//        contactApi.get();
    }
}