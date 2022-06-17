package com.example.communicationandroid.Activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.communicationandroid.Api.SignUpApi;
import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.databinding.ActivitySignUpBinding;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private String encodedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signUpBtnSubmit.setOnClickListener(view -> {
            EditText username = binding.signUpEditUsername;
            EditText password = binding.signUpEditPassword;
            EditText confirmPassword = binding.signUpEditConfirmPassword;

            if (!(password.getText().toString().equals(confirmPassword.toString())) &&
                    usernameAndPasswordValidation(username.getText().toString(),
                            password.getText().toString())) {
                Global.setContext(this.getBaseContext());

                SignUpApi signUpApi = new SignUpApi();
                signUpApi.post(new User(username.getText().toString(),
                        password.getText().toString()));
            } else {
                if (password.getText().toString().equals(confirmPassword.getText().toString())) {
                    Toast.makeText(this, "Username and password should contain at least" +
                            " 1 letter and 1 number.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Password and Confirm Password are not equal", Toast.LENGTH_SHORT).show();
                }
            }
        });


        binding.signUpBtnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });


        binding.signUpAddImageLayout.setOnClickListener( v-> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            choseImage.launch(intent);
        });


        Log.d("Activity-SignUp", "onCreate");
    }

    private String encodeImageToString(Bitmap bitmap) {
        int previewWidth = 150;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth,previewHeight,false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG,50,byteArrayOutputStream);
        byte [] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes,Base64.DEFAULT);
    }


    private final ActivityResultLauncher<Intent> choseImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Uri imageUri = result.getData().getData();
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(imageUri);
                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                        binding.signUpImage.setImageBitmap(bitmap);
                        binding.signUpAddImageText.setVisibility(View.GONE);
                        encodedImage = encodeImageToString(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
    );

    private boolean usernameAndPasswordValidation(String username, String password) {
        if (username.matches(".*[a-zA-Z]+.*") && password.matches(".*[a-zA-Z]+.*") &&
                password.matches(".*[0-9]+.*")) {
            return true;
        }
        return false;
    }


    @Override
    protected void onStart() {
        super.onStart();

        Log.d("Activity-SignUp", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Activity-SignUp", "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("Activity-SignUp", "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("Activity-SignUp", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("Activity-SignUp", "onPause");
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("Activity-SignUp", "onRestart");
    }
}

