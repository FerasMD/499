package kau.edu.quran;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class bodRec extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"noti")
        .setSmallIcon(R.drawable.splash)
                .setContentTitle("cont")
                .setContentText("plzzz work")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);


        notificationManagerCompat.notify(200,builder.build());
    }
}
