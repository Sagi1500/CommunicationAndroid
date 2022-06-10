//package com.example.communicationandroid.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.example.communicationandroid.Entities.Contact;
//import com.example.communicationandroid.R;
//import com.example.communicationandroid.databinding.ActivityContactListBinding;
//
//import java.util.List;
//
//public class ContactAdapter extends ArrayAdapter<Contact> {
//    private static final String TAG = "ContactAdapter";
//    private Context context;
//    private int resource;
//
//    public ContactAdapter(Context newContext, int contact_item, List<Contact> contacts) {
//        super(newContext, 0, (List<Contact>) contacts);
//        context = newContext;
//        resource= contact_item;
//    }
//
//    @NonNull
//    @Override
//    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
//        String username = getItem(position).getId();
//        String nickname = getItem(position).getName();
//        String server = getItem(position).getServer();
//
//        // create a Contact object with the information.
//        Contact contact = new Contact(username,nickname,server);
//
//        LayoutInflater inflater = LayoutInflater.from(context);
//        convertView = inflater.inflate(resource, parent,false);
//
//        //TextView tvUsername = (TextView) convertView.findViewById(R.id.contactItem_tvUsername);
//        TextView tvNickname = (TextView) convertView.findViewById(R.id.contactItem_tvNickname);
//
//        //tvUsername.setText(username);
//        tvNickname.setText(nickname);
//
//        return convertView;
//    }
//}
