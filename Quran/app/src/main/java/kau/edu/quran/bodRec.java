package kau.edu.quran;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class bodRec extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1=new Intent(context,ManageKhatam.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,200,intent1,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"noti")
        .setSmallIcon(R.drawable.splash)
                .setContentIntent(pendingIntent)
                .setContentTitle("Continue your khatam")
                .setContentText("This is a reminder for you to Continue your khatam")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(context);


        notificationManagerCompat.notify(200,builder.build());
    }
}
