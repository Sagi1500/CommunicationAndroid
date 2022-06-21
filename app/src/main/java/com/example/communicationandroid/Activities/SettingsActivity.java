package com.example.communicationandroid.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.communicationandroid.Api.ContactListApi;
import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.R;
import com.example.communicationandroid.ViewModel.UserViewModel;
import com.example.communicationandroid.databinding.ActivitySettingsBinding;
import com.example.communicationandroid.databinding.ActivitySignUpBinding;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class SettingsActivity extends AppCompatActivity {

    private ActivitySettingsBinding binding;
    public static final String SELECT = "Choose a contact's name to change";
    private ArrayList<String> arrayListContacts;
    private String selectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        List<Contact> contactList = Global.getContactViewModel().getAllContactsList();
        arrayListContacts = new ArrayList();
        arrayListContacts.add(SELECT);
        for (Contact contact : contactList) {
            arrayListContacts.add(contact.getId());
        }
        Spinner spinner = findViewById(R.id.setting_spinner);
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
            ImageView imageView = binding.settingImage;
            if(imageView.getDrawable()!=null){
                UserViewModel viewModel = new ViewModelProvider(this).get(UserViewModel.class);
                User u = viewModel.getUser(Global.getUsername());
                u.setImage(encodeImage(imageView));
                viewModel.updateUser(u);
            }
            finish();
        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_back);
        actionBar.setDisplayHomeAsUpEnabled(true);

        setTitle("Settings");

        binding.settingAddImageLayout.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            choseImage.launch(intent);
        });


        Log.d("Activity-SignUp", "onCreate");
    }
    private byte[] encodeImage(ImageView imageView) {
        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageInByte = baos.toByteArray();
        try {
            baos.close();
        } catch (Exception e) {
        }
        return imageInByte;

    }

    private final ActivityResultLauncher<Intent> choseImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Uri imageUri = result.getData().getData();
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        binding.settingImage.setImageBitmap(bitmap);
                        binding.settingAddImageText.setVisibility(View.GONE);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
    );

    void changeName(List<Contact> contactList, String newName) {
        if (!Objects.equals(selectedId, SELECT)) {
            Contact contact = null;
            for (Contact c : contactList) {
                if (Objects.equals(c.getId(), selectedId)) {
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

}