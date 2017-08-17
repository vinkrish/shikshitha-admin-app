package com.shikshitha.shikshithaadmin.util;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.shikshitha.shikshithaadmin.api.AdminApi;
import com.shikshitha.shikshithaadmin.api.ApiClient;
import com.shikshitha.shikshithaadmin.model.School;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ElasticBeanstalkService extends IntentService {

    public ElasticBeanstalkService() {
        super("ElasticBeanstalk");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        AdminApi api = ApiClient.getAuthorizedClient().create(AdminApi.class);

        Call<Void> queue = api.keepServerUp();
        queue.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.d("Alarm", "Triggered");
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });

        ElasticBeanstalkReceiver.completeWakefulIntent(intent);
    }
}
