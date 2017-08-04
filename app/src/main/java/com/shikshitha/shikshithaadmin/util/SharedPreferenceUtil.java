package com.shikshitha.shikshithaadmin.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.shikshitha.shikshithaadmin.model.AdminCredentials;

/**
 * Created by Vinay on 20-07-2017.
 */

public class SharedPreferenceUtil {

    public static void saveAdminCredentials(Context context, AdminCredentials adminCredentials) {
        SharedPreferences sharedPref = context.getSharedPreferences("credentials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("authToken", adminCredentials.getToken());
        editor.putLong("schoolId", adminCredentials.getSchoolId());
        editor.apply();
    }

    public static AdminCredentials getAuthToken(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("credentials", Context.MODE_PRIVATE);
        AdminCredentials adminCredentials = new AdminCredentials();
        adminCredentials.setToken(sharedPref.getString("authToken", ""));
        adminCredentials.setSchoolId(sharedPref.getLong("schoolId", 0));
        return adminCredentials;
    }

    public static void logout(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences("credentials", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("authToken", "");
        editor.putLong("schoolId", 0);
        editor.apply();
    }

}
