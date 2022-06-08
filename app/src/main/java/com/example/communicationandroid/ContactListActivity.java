package com.example.communicationandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Room.AppDB;
import com.example.communicationandroid.Room.ContactDao;
import com.example.communicationandroid.adapter.ContactAdapter;
import com.example.communicationandroid.databinding.ActivityContactListBinding;
import com.example.communicationandroid.databinding.ActivityLoginBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ContactListActivity extends AppCompatActivity {

    private ActivityContactListBinding binding;
    private List<Contact> contacts = new ArrayList<>();
    private ContactDao contactDao;
    private AppDB db;
    ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContactListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "localDB")
                .allowMainThreadQueries()
                .build();

        contactDao = db.contactDaoDao();
        contacts = contactDao.index();

        FloatingActionButton btnAdd = binding.contactListBtnAdd;
        btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(this, AddContactActivity.class);
            startActivity(intent);
        });


        ListView lvContacts = binding.contactListLvContacts;
        adapter = new ContactAdapter(this,R.layout.contact_item,contacts);
        lvContacts.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
         super.onResume();
         loadContact();
    }

    private void loadContact() {
        contacts.clear();
        List<Contact> dbContacts = contactDao.index();
        for (Contact contact: dbContacts) {
            contacts.add(contact);
        }
        adapter.notifyDataSetChanged();
    }

}