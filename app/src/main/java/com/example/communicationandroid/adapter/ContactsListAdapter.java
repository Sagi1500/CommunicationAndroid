package com.example.communicationandroid.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.communicationandroid.Entities.Contact;
import com.example.communicationandroid.Entities.User;
import com.example.communicationandroid.Global;
import com.example.communicationandroid.Listeners.ContactListener;
import com.example.communicationandroid.ViewModel.UserViewModel;
import com.example.communicationandroid.databinding.ContactItemBinding;

import java.util.ArrayList;
import java.util.Collections;
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
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContactItemBinding contactItemBinding = ContactItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new ContactViewHolder(contactItemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.setContactData(contacts.get(position));
    }

    public void setContacts(List<Contact> lst) {
        Collections.sort(lst, (c1, c2) -> -c1.getLastdate().compareToIgnoreCase(c2.getLastdate()));
        contacts = lst;
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        if (contacts != null) {
            return contacts.size();
        } else return 0;
    }

    public void notifyChanged() {
        notifyDataSetChanged();
    }


    class ContactViewHolder extends RecyclerView.ViewHolder {
        //private final ImageView coPic;
        private final ContactItemBinding binding;

        private ContactViewHolder(ContactItemBinding itemBinding) {
            super(itemBinding.getRoot());
            binding = itemBinding;
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
                ImageView imageView = binding.contactItemImageProfile;
                handleImage(imageView, current.getId());

            }
            binding.getRoot().setOnClickListener(v -> contactListener.onContactClicked(current));
        }

        void handleImage(ImageView imageView, String contactId) {
            ViewModelStoreOwner view = Global.getViewModelStoreOwner();
            UserViewModel userViewModel = new ViewModelProvider(view).get(UserViewModel.class);
            User user = userViewModel.getUser(contactId);
            if (user != null) {
                byte[] bitmapData = user.getImage();
                if (bitmapData != null) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bitmapData, 0, bitmapData.length);
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
            canvas.drawCircle((float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2,
                    (float) bitmap.getWidth() / 2, paint);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, rect, rect, paint);
            return output;
        }
    }

}
