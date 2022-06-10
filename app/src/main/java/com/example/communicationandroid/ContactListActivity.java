package com.example.communicationandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.communicationandroid.Api.ContactApi;
import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Room.AppDB;
import com.example.communicationandroid.Room.ContactDao;
import com.example.communicationandroid.ViewModel.ContactViewModel;
import com.example.communicationandroid.adapter.ContactsListAdapter;
import com.example.communicationandroid.databinding.ActivityContactListBinding;
import com.example.communicationandroid.databinding.ActivityLoginBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {
    private ContactViewModel viewModel;
//    private ActivityContactListBinding binding;
//    private List<Contact> contacts = new ArrayList<>();
//    private ContactDao contactDao;
//    private AppDB db;
//    ContactsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);


        viewModel = new ViewModelProvider(this).get(ContactViewModel.class);

        ContactApi contactApi = new ContactApi();
        contactApi.get();


//        binding = ActivityContactListBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());


//        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "localDB")
//                .allowMainThreadQueries()
//                .build();
//
//        contactDao = db.contactDaoDao();
//        contacts = contactDao.index();
//
//        FloatingActionButton btnAdd = binding.contactListBtnAdd;
//        btnAdd.setOnClickListener(view -> {
//            Intent intent = new Intent(this, AddContactActivity.class);
//            startActivity(intent);
//        });

        //


        RecyclerView lstContacts = findViewById(R.id.lstContacts);
        //RecyclerView lstContacts = binding.lstContacts;
        ContactsListAdapter adapter = new ContactsListAdapter(this);
        lstContacts.setAdapter(adapter);
        lstContacts.setLayoutManager(new LinearLayoutManager(this));

        viewModel.get().observe(this, contacts -> {
            adapter.setContacts(contacts);
        });

    }
}
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        loadContact();
//    }
//
//    private void loadContact() {
//
//        contacts.clear();
//        List<Contact> dbContacts = contactDao.index();
//        contacts.addAll(dbContacts);
//*******************************************************************************
//        adapter.notifyDataSetChanged();
//        RecyclerView lstContacts = findViewById(R.id.lstContacts);
//        final ContactsListAdapter adapter = new ContactsListAdapter(this);
//        lstContacts.setAdapter(adapter);
//        lstContacts.setLayoutManager(new LinearLayoutManager(this));
//        adapter.setContacts(contacts);
//    }
//}
//
//        super.onCreate(savedInstanceState);
//        binding = ActivityContactListBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "localDB")
//                .allowMainThreadQueries()
//                .build();
//
//        contactDao = db.contactDaoDao();
//        contacts = contactDao.index();
//
//        FloatingActionButton btnAdd = binding.contactListBtnAdd;
//        btnAdd.setOnClickListener(view -> {
//            Intent intent = new Intent(this, AddContactActivity.class);
//            startActivity(intent);
//        });
//
//
//        ListView lvContacts = binding.contactListLvContacts;
//        adapter = new ContactAdapter(this,R.layout.contact_item,contacts);
//        lvContacts.setAdapter(adapter);
//
//    }
//
//    @Override
//    protected void onResume() {
//         super.onResume();
//         loadContact();
//    }
//
//    private void loadContact() {
//        contacts.clear();
//        List<Contact> dbContacts = contactDao.index();
//        for (Contact contact: dbContacts) {
//            contacts.add(contact);
//        }
//        adapter.notifyDataSetChanged();
//    }
//
//}