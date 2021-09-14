package com.example.notifyme;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.notifyme.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "ID001";
    private ActivityMainBinding binding;
    private int notificationId = 1;
    NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        createNotificationChannel();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle("My Notification")
                .setContentText("Much longer text that can not fit in one line .....")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Much longer text that can not fit in one line ....."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);


        binding.button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.textView01.setText("Create A Notification");
                notificationManager.notify(notificationId, builder.build());

            }
        });

    }
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}