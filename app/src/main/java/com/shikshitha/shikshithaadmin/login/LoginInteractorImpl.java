package com.shikshitha.shikshithaadmin.login;

import com.shikshitha.shikshithaadmin.App;
import com.shikshitha.shikshithaadmin.R;
import com.shikshitha.shikshithaadmin.api.AdminApi;
import com.shikshitha.shikshithaadmin.api.ApiClient;
import com.shikshitha.shikshithaadmin.model.AdminCredentials;
import com.shikshitha.shikshithaadmin.model.Credentials;
import com.shikshitha.shikshithaadmin.util.SharedPreferenceUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Vinay on 28-03-2017.
 */

class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void login(final Credentials credentials, final OnLoginFinishedListener listener) {
        AdminApi adminApi = ApiClient.getClient().create(AdminApi.class);

        Call<AdminCredentials> login = adminApi.login(credentials);
        login.enqueue(new Callback<AdminCredentials>() {
            @Override
            public void onResponse(Call<AdminCredentials> call, Response<AdminCredentials> response) {
                if(response.isSuccessful()) {
                    listener.onSuccess(response.body());
                } else {
                    listener.onError(App.getInstance().getString(R.string.request_error));
                }
            }

            @Override
            public void onFailure(Call<AdminCredentials> call, Throwable t) {
                listener.onError(App.getInstance().getString(R.string.network_error));
            }
        });
    }

}
