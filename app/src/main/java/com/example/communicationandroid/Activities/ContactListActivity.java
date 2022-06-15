package com.example.communicationandroid.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
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

        RecyclerView lstContacts = binding.lstContacts;
        lstContacts.setLayoutManager(new LinearLayoutManager(this));

        //lstContacts.setHasFixedSize(true);

        final ContactsListAdapter adapter = new ContactsListAdapter(this,this);
        lstContacts.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        viewModel.getAllContacts().observe(this, new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                adapter.setContacts(contacts);
            }
        });


        /**
         * API:
         *
         ContactListApi contactListApi = new ContactListApi();
         contactListApi.addContact(new Contact("s1","s1","s1"));
         contactListApi.changeContact("a1",new Contact("a1","aa1","aa1"));
         contactListApi.deleteContact("sagi");
         contactListApi.getContact("sagi");
         contactListApi.getAllContacts();
         String token = Global.getToken().getValue();
         */

        FloatingActionButton buttonAddContact = binding.contactListBtnAdd;

        ActivityResultLauncher<Intent> launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        String username = data.getStringExtra(AddContactActivity.EXTRA_USERNAME);
                        String nickname = data.getStringExtra(AddContactActivity.EXTRA_NICKNAME);
                        String server = data.getStringExtra(AddContactActivity.EXTRA_SERVER);


                        Contact contact = new Contact(username, nickname, server);
                        viewModel.addContact(contact);

                        Toast.makeText(this, "Contact saved", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(this, "Contact not saved", Toast.LENGTH_SHORT).show();
                    }
                });

        buttonAddContact.setOnClickListener(v -> {
            Intent intent = new Intent(ContactListActivity.this, AddContactActivity.class);
            launcher.launch(intent);
        });



        binding.contactListLogout.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        });

        binding.contactListSettings.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivity(intent);
        });

    }

    /*
    private void loading(Boolean isLoading) {
        if (isLoading) {
            binding.contactListProgressBar.setVisibility(View.VISIBLE);
        }else {
            binding.contactListProgressBar.setVisibility(View.INVISIBLE);
        }
    }

     */


    @Override
    public void onContactClicked(Contact contact) {
        Intent intent = new Intent(getApplicationContext(), ChatActivity.class);
        intent.putExtra(Global.contact_Key, contact);
        startActivity(intent);
    }
}
