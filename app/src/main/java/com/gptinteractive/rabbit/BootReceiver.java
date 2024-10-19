package com.gptinteractive.rabbit;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.gptinteractive.rabbit.init.StartMonitor;

public class BootReceiver extends BroadcastReceiver {
    private StartMonitor startMonitor = new StartMonitor();
    @Override
    public void onReceive(Context context, Intent intent) {
        startMonitor.start();
    }
}
