package com.shikshitha.shikshithaadmin.login;

import com.shikshitha.shikshithaadmin.model.AdminCredentials;
import com.shikshitha.shikshithaadmin.model.Credentials;

/**
 * Created by Vinay on 28-03-2017.
 */

interface LoginInteractor {
    interface OnLoginFinishedListener{

        void onSuccess(AdminCredentials adminCredentials);

        void onError(String message);
    }

    void login(Credentials credentials, OnLoginFinishedListener listener);
}
