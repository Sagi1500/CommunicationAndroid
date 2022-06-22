package com.example.communicationandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.communicationandroid.R;



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
}
