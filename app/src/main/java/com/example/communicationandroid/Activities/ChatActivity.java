package com.example.communicationandroid.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.Message;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.R;
import com.example.communicationandroid.ViewModel.ContactViewModel;
import com.example.communicationandroid.ViewModel.MessagesViewModel;
import com.example.communicationandroid.adapter.ChatAdapter;
import com.example.communicationandroid.adapter.ContactsListAdapter;
import com.example.communicationandroid.databinding.ActivityChatBinding;

import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ActivityChatBinding binding;
    private Contact currentContact;
    private int nextId = 0;
    private MessagesViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setListeners();
        loadCurrentContactDetails();
        nextId = findNextId();

        RecyclerView lstMessages = binding.chatChatRecyclerView;
        lstMessages.setLayoutManager(new LinearLayoutManager(this));

        //lstContacts.setHasFixedSize(true);

        final ChatAdapter adapter = new ChatAdapter(Global.getUsername());
        lstMessages.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MessagesViewModel.class);

        viewModel.getAllMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                adapter.setMessages(messages);
            }
        });


    }

    private void loadCurrentContactDetails() {
        currentContact = (Contact) getIntent().getSerializableExtra(Global.contact_Key);
        binding.chatCurrentContactNickName.setText(currentContact.getName());
    }

    private void setListeners() {
        binding.chatImageBack.setOnClickListener(v-> onBackPressed());
        binding.chatBtnSend.setOnClickListener(v-> {sendMessage();});
    }

    private void sendMessage() {
        Toast.makeText(this, "in send message", Toast.LENGTH_SHORT).show();
        if (binding.chatInputMessage.getText().toString().trim().isEmpty()) {
            return;
        }
        Message newMessage = new Message(currentContact.getId(),binding.chatInputMessage.getText().toString(),true);
        viewModel.addMessage(newMessage);

    }

    private int findNextId() {
        return nextId++;
    }
}