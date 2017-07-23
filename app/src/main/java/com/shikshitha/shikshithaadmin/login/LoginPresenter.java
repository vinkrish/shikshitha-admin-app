package com.shikshitha.shikshithaadmin.login;

import com.shikshitha.shikshithaadmin.model.Credentials;

/**
 * Created by Vinay on 28-03-2017.
 */

interface LoginPresenter {

    void validateCredentials(Credentials credentials);

    void onDestroy();
}
