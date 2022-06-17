package com.example.communicationandroid.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.communicationandroid.R;
import com.example.communicationandroid.databinding.ActivityAddContactBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class AddContactActivity extends AppCompatActivity {
    public static final String EXTRA_USERNAME =
            "com.example.communicationandroid.EXTRA_USERNAME";
    public static final String EXTRA_NICKNAME =
            "com.example.communicationandroid.EXTRA_NICKNAME";
    public static final String EXTRA_SERVER =
            "com.example.communicationandroid.EXTRA_SERVER";

    private EditText editTextUsername;
    private EditText editTextNickname;
    private EditText editTextServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);


        editTextUsername = findViewById(R.id.addContact_etUsername);
        editTextNickname = findViewById(R.id.addContact_etNickname);
        editTextServer = findViewById(R.id.addContact_etServer);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);
        setTitle("Add Contact");

        Button addContact = findViewById(R.id.addContact_btnAdd);
        addContact.setOnClickListener(v -> saveContact());

    }

    private void saveContact() {
        String username = editTextUsername.getText().toString();
        String nickname = editTextNickname.getText().toString();
        String server = editTextServer.getText().toString();

        if (username.trim().isEmpty() || nickname.trim().isEmpty() || server.trim().isEmpty()) {
            Toast.makeText(this, "Please insert username, nickname and server", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_USERNAME, username);
        data.putExtra(EXTRA_NICKNAME, nickname);
        data.putExtra(EXTRA_SERVER, server);
        setResult(RESULT_OK, data);
        finish();

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater menuInflater = getMenuInflater();
//        menuInflater.inflate(R.menu.add_contact_menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.save_contact) {
//            saveContact();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
//************************************************************************************
//    private ActivityAddContactBinding binding;
//    private AppDB db;
//    private ContactDao contactDao;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        binding = ActivityAddContactBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        db = Room.databaseBuilder(getApplicationContext(), AppDB.class, "localDB")
//                .allowMainThreadQueries()
//                .build();
//
//        contactDao = db.contactDaoDao();
//
//        Button btnAdd = binding.addContactBtnAdd;
//        btnAdd.setOnClickListener(view -> {
//
//            EditText etUsername = binding.addContactEtUsername;
//            EditText etNickname = binding.addContactEtNickname;
//            EditText etServer = binding.addContactEtServer;
//            Contact contact = new Contact(etUsername.getText().toString(),
//                    etNickname.getText().toString(),
//                    etServer.getText().toString());
//
//            contactDao.insert(contact);
//            finish();
//        });
//    }