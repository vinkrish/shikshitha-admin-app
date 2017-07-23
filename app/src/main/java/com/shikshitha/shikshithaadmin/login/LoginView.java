package com.shikshitha.shikshithaadmin.login;

import com.shikshitha.shikshithaadmin.model.AdminCredentials;

/**
 * Created by Vinay on 28-03-2017.
 */

interface LoginView {
    void showProgress();

    void hideProgress();

    void showError(String message);

    void noUser();

    void saveUser(AdminCredentials adminCredentials);

    void navigateToDashboard();
}
