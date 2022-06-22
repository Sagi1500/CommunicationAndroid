package com.example.communicationandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Api.LoginApi;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.R;
import com.example.communicationandroid.ViewModel.UserViewModel;
import com.example.communicationandroid.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private UserViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        Button btnLogin = binding.loginBtnLogin;
        btnLogin.setOnClickListener(view -> {


            EditText id = findViewById(R.id.editTextTextPersonName);
            EditText password = findViewById(R.id.editTextTextPassword2);

            Global.setContext(this.getBaseContext());
            Global.setUsername(id.getText().toString());
            viewModel = new ViewModelProvider(this).get(UserViewModel.class);

            LoginApi loginApi = new LoginApi();
            loginApi.post(new User(id.getText().toString(), password.getText().toString()),viewModel);
        });

        // Move to sign-up activity.
        Button btnSignUP = binding.loginBtnSignUp;
        btnSignUP.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

        Log.d("Activity-Login", "onCreate");
    }
}