package com.example.communicationandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.widget.Toast;

import com.example.communicationandroid.Api.ContactListApi;
import com.example.communicationandroid.Api.MessagesApi;
import com.example.communicationandroid.Api.NotificationTokenApi;
import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.Message;
import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.R;
import com.example.communicationandroid.Repositories.UsersListRepository;
import com.example.communicationandroid.Room.AppDB;
import com.example.communicationandroid.Room.ImageDatabases;
import com.example.communicationandroid.Room.UserDao;
import com.example.communicationandroid.ViewModel.ContactViewModel;
import com.example.communicationandroid.ViewModel.MessagesViewModel;
import com.example.communicationandroid.ViewModel.UserViewModel;
import com.example.communicationandroid.adapter.ChatAdapter;
import com.example.communicationandroid.adapter.ContactsListAdapter;
import com.example.communicationandroid.databinding.ActivityChatBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;


import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private Contact currentContact;
    private int nextId = 0;
    private MessagesViewModel viewModel;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setTitle("Chat");

        setListeners();
        loadCurrentContactDetails();
        nextId = findNextId();

        RecyclerView lstMessages = binding.chatChatRecyclerView;
        lstMessages.setLayoutManager(new LinearLayoutManager(this));

        //lstContacts.setHasFixedSize(true);

        final ChatAdapter adapter = new ChatAdapter(Global.getUsername());
        lstMessages.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MessagesViewModel.class);
        viewModel.getAllMessages().observe(this, messages -> adapter.setMessages(messages));

        MessagesApi messagesApi = new MessagesApi();
        messagesApi.getAllMessages(viewModel);
        SwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.chat_refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            messagesApi.getAllMessages(viewModel);
            swipeRefreshLayout.setRefreshing(false);
        });
    }

    private void loadCurrentContactDetails() {
        currentContact = (Contact) getIntent().getSerializableExtra(Global.contact_Key);
        binding.chatCurrentContactNickName.setText(currentContact.getName());

//        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
//        User currentUser = userViewModel.getmRepository().getDao().get(currentContact.getId());
//        byte[] bytes = currentUser.getImage();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes,0,bytes.length);
//        binding.chatContactImage.setImageBitmap(bitmap);
    }

    private void setListeners() {
        binding.chatImageBack.setOnClickListener(v -> onBackPressed());
        binding.chatBtnSend.setOnClickListener(v -> sendMessage());
    }

    private void sendMessage() {
        Toast.makeText(this, "in send message", Toast.LENGTH_SHORT).show();
        if (binding.chatInputMessage.getText().toString().trim().isEmpty()) {
            return;
        }
        Message newMessage = new Message(currentContact.getId(), binding.chatInputMessage.getText().toString(), true);
        MessagesApi messagesApi = new MessagesApi();
        messagesApi.addMessage(viewModel,
                Global.getContactViewModel(),
                Global.getContactsListAdapter() ,
                newMessage);
        binding.chatInputMessage.setText("");

    }

    private int findNextId() {
        return nextId++;
    }
}