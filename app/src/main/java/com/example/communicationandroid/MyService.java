package com.example.communicationandroid;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyService extends FirebaseMessagingService {

    static final String CHANNEL_ID ="1";
    static final int CHANNEL_ID_Integer = 1;

    public MyService() {
    }


@Override
public void onMessageReceived(RemoteMessage remoteMessage){
        if(remoteMessage.getNotification()!=null){

            createNotificationChannel();

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this,CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_notification)
                    .setContentTitle(remoteMessage.getNotification().getTitle())
                    .setContentText(remoteMessage.getNotification().getBody())
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
            notificationManagerCompat.notify(CHANNEL_ID_Integer,builder.build());
            Intent intent = new Intent("1001").putExtra("MESSAGE", "message");
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
        }
}

private void createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,getString(R.string.channel_name),
                    importance);

            channel.setDescription(getString(R.string.channel_description));

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
}

   //the firebase code of our app is in chatActivity
}

//            CharSequence name = getString(R.string.channel_name);
//            String description = getString(R.string.channel_description);