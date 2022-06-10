package com.example.communicationandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.R;

import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {

    class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvNickname;
        private final TextView tvLastMessage;
        private final TextView tvLastMessageTime;
        //private final ImageView coPic;

        private ContactViewHolder(View itemView) {
            super(itemView);
            tvNickname = itemView.findViewById(R.id.contactItem_tvNickname);
            tvLastMessage = itemView.findViewById(R.id.contactItem_tvLastMessage);
            tvLastMessageTime = itemView.findViewById(R.id.contactItem_tvLastMessageTime);
            //coPic = itemView.findViewById(R.id.tv); ->here we need the img
        }
    }

    private final LayoutInflater mInflater;
    private List<Contact> contacts;

    public ContactsListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.contact_item, parent, false);
        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        if (contacts != null) {
            final Contact current = contacts.get(position);
            holder.tvLastMessage.setText(current.getLast());
            holder.tvLastMessageTime.setText(current.getLastDate());
            holder.tvNickname.setText(current.getName());
        }
    }

    public void setContacts(List<Contact> s) {
        contacts = s;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if (contacts != null) {
            return contacts.size();
        } else return 0;
    }

    public List<Contact> getContacts() {
        return contacts;
    }
}
