package com.gptinteractive.rabbit.network;

import android.util.Log;
import com.gptinteractive.rabbit.data.Config;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class WebHookRaw {
    public static void send(final String message, final WebhookCallBack callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try {
                    URL url = new URL(Config.getWebhookUrl());
                    conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);

                    try (OutputStream os = conn.getOutputStream()) {
                        byte[] input = message.getBytes(StandardCharsets.UTF_8);
                        os.write(input, 0, input.length);
                        os.flush();
                    }
                    int responseCode = conn.getResponseCode();
                    if (responseCode == 200 || responseCode == 204) {
                        callback.osSuccess(true);
                    } else {
                        callback.osSuccess(false);
                    }
                } catch (Exception e) {
                    callback.osError(e);
                } finally {
                    if (conn != null) {
                        conn.disconnect();
                    }
                }
            }
        }).start();
    }
    public interface WebhookCallBack {
        void osSuccess(Boolean result);
        void osError(Exception e);
    }
}
