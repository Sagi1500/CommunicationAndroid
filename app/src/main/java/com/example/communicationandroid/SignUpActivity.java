package com.example.communicationandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.communicationandroid.databinding.ActivityLoginBinding;
import com.example.communicationandroid.databinding.ActivitySignUpBinding;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


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

