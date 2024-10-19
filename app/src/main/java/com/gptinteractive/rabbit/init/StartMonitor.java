package com.gptinteractive.rabbit.init;

import android.os.Handler;
import android.util.Log;
import com.gptinteractive.rabbit.api.DeviceInfo;
import com.gptinteractive.rabbit.network.WebHook;

public class StartMonitor {
    private Handler handler = new Handler();
    private WebHook webHook = new WebHook();

    public void start() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String deviceName = DeviceInfo.getDeviceName();
                            webHook.sendWebhook("`" + deviceName + "`", "Dispositivo conectado y operando correctamente!");
                        } catch (Exception e) {
                            Log.e("Rabbit-App", e.getMessage().toString());
                        }
                    }
                }).start();
                handler.postDelayed(this, 60000);
            }
        }, 60000);
    }
}
