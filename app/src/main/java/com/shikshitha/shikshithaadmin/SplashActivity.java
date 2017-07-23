package com.shikshitha.shikshithaadmin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.shikshitha.shikshithaadmin.dashboard.DashboardActivity;
import com.shikshitha.shikshithaadmin.login.LoginActivity;
import com.shikshitha.shikshithaadmin.util.SharedPreferenceUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(SharedPreferenceUtil.getAuthToken(this).getToken().equals("")) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        } else {
            startActivity(new Intent(this, DashboardActivity.class));
            finish();
        }

    }

}
