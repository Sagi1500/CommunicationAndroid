package com.example.communicationandroid.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communicationandroid.Entities.Message;
import com.example.communicationandroid.databinding.ItemReceivedMessageBinding;
import com.example.communicationandroid.databinding.ItemSentMessageBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Message> messageList;

    public ChatAdapter() {
        this.messageList = new ArrayList<>();
    }

    public void setMessages(List<Message> lst,RecyclerView rv) {
        this.messageList = lst;
        if(!lst.isEmpty()){
            rv.smoothScrollToPosition(lst.size()-1);
        }
        notifyDataSetChanged();
    }

//    public void  notifyChanged(){
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == 1) {
            return new SentMessageViewHolder(
                    ItemSentMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),parent,false));
        } else {
            return new ReceivedMessageViewHolder(
                    ItemReceivedMessageBinding.inflate(
                            LayoutInflater.from(parent.getContext()),parent,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == 1) {
            ((SentMessageViewHolder) holder).setMessageData(messageList.get(position));
        } else {
            ((ReceivedMessageViewHolder) holder).setMessageData(messageList.get(position));
        }
    }

    @Override
    public int getItemCount() {
        if (messageList != null) {
            return messageList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if(messageList.get(position).getSent()) {
            return 1;
        } else {
            return 0;
        }
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder {

        private final ItemSentMessageBinding binding;

        SentMessageViewHolder(ItemSentMessageBinding itemSentMessageBinding) {
            super(itemSentMessageBinding.getRoot());
            this.binding = itemSentMessageBinding;
        }

        void setMessageData(Message message) {
            binding.itemSendMessageTextMessage.setText(message.getContent());
            binding.itemSendMessageMessageDateTime.setText(message.getCreated());

        }
    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {

        private final ItemReceivedMessageBinding binding;

        ReceivedMessageViewHolder(ItemReceivedMessageBinding itemReceivedMessageBinding) {
            super(itemReceivedMessageBinding.getRoot());
            this.binding = itemReceivedMessageBinding;
        }

        void setMessageData(Message message) {
            binding.itemReceivedMessageTextMessage.setText(message.getContent());
            binding.itemReceivedMessageMessageDateTime.setText(message.getCreated());
        }
    }

}
