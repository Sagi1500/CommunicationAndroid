package com.example.communicationandroid.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.communicationandroid.Api.ContactListApi;
import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.R;


import java.util.ArrayList;
import java.util.List;


public class SettingsActivity extends AppCompatActivity {

    public static final String SELECT = "Choose a contact's name to change";
    private ArrayList<String> arrayListContacts;
    private String selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        List<Contact> contactList = Global.getContactViewModel().getAllContactsList();
        arrayListContacts = new ArrayList();
        arrayListContacts.add(SELECT);
        for (Contact contact : contactList) {
            arrayListContacts.add(contact.getId());
        }
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(SettingsActivity.this,
                android.R.layout.simple_list_item_1, arrayListContacts);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedId = arrayListContacts.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        AppCompatButton saveButton = findViewById(R.id.settings_btnSave);
        saveButton.setOnClickListener(v -> {
            int count = 0;
            if (selectedId != SELECT) {
//
                EditText name = findViewById(R.id.setting_spinnerText);
                if (name == null || name.getText().toString().trim().isEmpty()) {
                    Toast.makeText(SettingsActivity.this, "Write new nickname", Toast.LENGTH_SHORT).show();
                } else {
                    count++;
                    changeName(contactList, name.getText().toString());
                    Global.getContactsListAdapter().notifyChanged();
                }
            }
            EditText serverUrl = findViewById(R.id.settings_editServerUrl);
            if (!serverUrl.getText().toString().trim().isEmpty()) {
                count++;
                Global.setServer(serverUrl.getText().toString().trim());
            }
            if (count > 0) {
                Toast.makeText(SettingsActivity.this, "saved successfully", Toast.LENGTH_SHORT).show();
            }
            finish();
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setDisplayHomeAsUpEnabled(true);

        setTitle("Settings");

        setListeners();
    }

    void changeName(List<Contact> contactList, String newName) {
        if (selectedId != SELECT) {
            Contact contact = null;
            for (Contact c : contactList) {
                if (c.getId() == selectedId) {
                    contact = c;
                    break;
                }
            }
            if (contact != null) {
                contact.setName(newName);
                ContactListApi contactListApi = new ContactListApi();
                contactListApi.changeContact(selectedId, contact);
            }
        }
    }

    private void setListeners() {

    }
}