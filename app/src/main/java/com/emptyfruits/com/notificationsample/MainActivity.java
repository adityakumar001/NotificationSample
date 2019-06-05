package com.emptyfruits.com.notificationsample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.notify);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String CHANNEL_ID = MainActivity.class.getPackage().getName();
                Intent notifiedIntent = new Intent(MainActivity.this,
                        NotifiedActivity.class);
                notifiedIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                        | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Notification notification
                        = new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID)
                        .setContentTitle("User Notification")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentText("You have clicked the notify button")
                        .setPriority(Notification.PRIORITY_HIGH)
                        .setContentIntent(PendingIntent.getActivity(MainActivity.this, 230, notifiedIntent, 0))
                        .setAutoCancel(true)
                        .build();
                NotificationManager notificationManager
                        = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID,
                            "My Notification", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManager.createNotificationChannel(notificationChannel);
                }
                notificationManager.notify(1232, notification);
                Toast.makeText(MainActivity.this, "Button clicked!!",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}