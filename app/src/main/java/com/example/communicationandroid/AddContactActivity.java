package com.example.communicationandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Room.AppDB;
import com.example.communicationandroid.Room.ContactDao;
import com.example.communicationandroid.databinding.ActivityAddContactBinding;

public class AddContactActivity extends AppCompatActivity {

    private ActivityAddContactBinding binding;
    private AppDB db;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "localDB")
                .allowMainThreadQueries()
                .build();

        contactDao = db.contactDaoDao();

        Button btnAdd = binding.addContactBtnAdd;
        btnAdd.setOnClickListener(view -> {

            EditText etUsername = binding.addContactEtUsername;
            EditText etNickname = binding.addContactEtNickname;
            EditText etServer = binding.addContactEtServer;
            Contact contact = new Contact(etUsername.getText().toString(),
                    etNickname.getText().toString(),
                    etServer.getText().toString());

            contactDao.insert(contact);
            finish();
        });
    }
}