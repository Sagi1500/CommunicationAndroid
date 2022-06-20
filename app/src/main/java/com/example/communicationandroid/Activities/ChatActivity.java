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

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.communicationandroid.Api.ContactListApi;
import com.example.communicationandroid.Api.MessagesApi;
import com.example.communicationandroid.Api.NotificationTokenApi;
import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.Message;
import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.R;

import com.example.communicationandroid.ViewModel.MessagesViewModel;
import com.example.communicationandroid.ViewModel.UserViewModel;
import com.example.communicationandroid.adapter.ChatAdapter;
import com.example.communicationandroid.databinding.ActivityChatBinding;


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
        ImageView imageView = binding.chatContactImage;
        handleImage(imageView,currentContact.getId());
    }
    void handleImage(ImageView imageView,String contactId) {
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        User u = userViewModel.getUser(contactId);
        if(u!=null){
            byte[] bitmapdata = u.getImage();
            if (bitmapdata!=null){
                Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapdata, 0, bitmapdata.length);
                imageView.setImageBitmap(getCroppedBitmap(bitmap));
            }
        }
    }
    public Bitmap getCroppedBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2,
                bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        //Bitmap _bmp = Bitmap.createScaledBitmap(output, 60, 60, false);
        //return _bmp;
        return output;
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
        messagesApi.addMessage(viewModel, newMessage);
        binding.chatInputMessage.setText("");

    }

    private int findNextId() {
        return nextId++;
    }
}