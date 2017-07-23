package com.shikshitha.shikshithaadmin;

import android.app.Application;

/**
 * Created by Vinay on 20-07-2017.
 */

public class App extends Application {
    private static App Instance;

    @Override
    public void onCreate() {
        super.onCreate();
        Instance = this;
    }

    public static App getInstance() {
        return Instance;
    }
}
