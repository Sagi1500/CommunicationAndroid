package com.example.communicationandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Listeners.ContactListener;
import com.example.communicationandroid.databinding.ContactItemBinding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ContactsListAdapter extends RecyclerView.Adapter<ContactsListAdapter.ContactViewHolder> {

    private final LayoutInflater mInflater;
    private List<Contact> contacts = new ArrayList<>();
    private final ContactListener contactListener;

    public ContactsListAdapter(Context context, ContactListener contactListener) {
        mInflater = LayoutInflater.from(context);
        this.contactListener = contactListener;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType) {
        ContactItemBinding contactItemBinding = ContactItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent,false);

        return new ContactViewHolder(contactItemBinding);
//        View itemView = mInflater.inflate(R.layout.contact_item, parent, false);
//        return new ContactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.setContactData(contacts.get(position));
    }

    public void setContacts(List<Contact> lst) {
        Collections.sort(lst, new Comparator<Contact>() {
            @Override
            public int compare(Contact c1, Contact c2) {
                return -c1.getLastdate().compareToIgnoreCase(c2.getLastdate());
            }
        });
        contacts = lst;
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

    public void notifyChanged() {
        notifyDataSetChanged();
    }


    class ContactViewHolder extends RecyclerView.ViewHolder {
        //private final ImageView coPic;
        private ContactItemBinding binding;

        private ContactViewHolder(ContactItemBinding itemBinding) {
            super(itemBinding.getRoot());
            binding = itemBinding;
            //coPic = itemView.findViewById(R.id.tv); ->here we ened the img
        }

        void setContactData(Contact current) {
            if (contacts != null) {
                if (current.getLast() == null) {
                    binding.contactItemTvLastMessage.setText("");
                } else {
                    binding.contactItemTvLastMessage.setText(current.getLast());
                }
                if (current.getLastdate() == null) {
                    binding.contactItemTvLastMessageTime.setText("");
                } else {
                    binding.contactItemTvLastMessageTime.setText(current.getLastdate());
                }
                binding.contactItemTvNickname.setText(current.getName());
            }
            binding.getRoot().setOnClickListener(v -> contactListener.onContactClicked(current));
        }
    }
}
