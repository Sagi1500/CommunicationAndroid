package com.example.communicationandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.communicationandroid.Api.SignUpApi;
import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.R;
import com.example.communicationandroid.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    private boolean usernameAndPasswordValidation(String username, String password) {
        if (username.matches(".*[a-zA-Z]+.*") && password.matches(".*[a-zA-Z]+.*") &&
                password.matches(".*[0-9]+.*")) {
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button btnSubmit = binding.signUpBtnSubmit;
        btnSubmit.setOnClickListener(view -> {

            EditText username = findViewById(R.id.signUp_EditUsername);
            EditText password = findViewById(R.id.signUp_EditPassword);
            EditText confirmPassword = findViewById(R.id.signUp_EditConfirmPassword);

            if (password.getText().toString().equals(confirmPassword.toString()) &&
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

        Button btnLogin = binding.signUpBtnLogin;
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });

        Log.d("Activity-SignUp", "onCreate");
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

