package com.gptinteractive.rabbit.api;

import android.os.Build;

public class DeviceInfo {

    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String Model = Build.MODEL;
        if (Model.startsWith(manufacturer)) {
            return capitalize(Model);
        } else {
            return capitalize(manufacturer) + " " + Model;
        }
    }

    private static String capitalize(String manufacturer) {
        if (manufacturer == null || manufacturer.length() == 0) {
            return "";
        }
        char fist = manufacturer.charAt(0);
        if (Character.isUpperCase(fist)) {
            return manufacturer;
        } else {
            return Character.toUpperCase(fist) + manufacturer.substring(1);
        }
    }
}
