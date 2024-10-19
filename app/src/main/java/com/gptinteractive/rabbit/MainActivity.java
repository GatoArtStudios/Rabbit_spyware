package com.gptinteractive.rabbit;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import androidx.appcompat.app.AppCompatActivity;
import com.gptinteractive.rabbit.init.StartMonitor;


public class MainActivity extends AppCompatActivity {
    private StartMonitor startMonitor = new StartMonitor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startMonitor.start();

        if (!isNotificationServiceEnabled()) {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            startActivity(intent);
        }
    }

    private boolean isNotificationServiceEnabled() {
        String enabledListeners = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        String packageName = getPackageName();
        return enabledListeners != null && enabledListeners.contains(packageName);
    }
}
