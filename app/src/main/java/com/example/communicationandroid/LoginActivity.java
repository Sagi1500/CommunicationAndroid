package com.example.communicationandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.communicationandroid.Room.AppDB;
import com.example.communicationandroid.Room.ContactDao;
import com.example.communicationandroid.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private AppDB db;
    private ContactDao contactDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Button btnLogin = binding.loginBtnLogin;
        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(this, ContactListActivity.class);
            startActivity(intent);
        });

        // Move to sign-up activity.
        Button btnSignUP = binding.loginBtnSignUp;
        btnSignUP.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });

        Log.d("Activity-Login", "onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("Activity-Login", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("Activity-Login", "onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("Activity-Login", "onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("Activity-Login", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("Activity-Login", "onPause");
    }


    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("Activity-SignUp", "onRestart");
    }
}