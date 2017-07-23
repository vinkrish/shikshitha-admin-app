package com.shikshitha.shikshithaadmin.login;

import com.shikshitha.shikshithaadmin.model.AdminCredentials;
import com.shikshitha.shikshithaadmin.model.Credentials;

/**
 * Created by Vinay on 28-03-2017.
 */

class LoginPresenterImpl implements LoginPresenter, LoginInteractor.OnLoginFinishedListener {

    private LoginView loginView;
    private LoginInteractor interactor;

    LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.interactor = new LoginInteractorImpl();
    }

    @Override
    public void validateCredentials(Credentials credentials) {
        if(loginView != null) {
            loginView.showProgress();
            interactor.login(credentials, this);
        }
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }

    @Override
    public void onSuccess(AdminCredentials adminCredentials) {
        if(loginView != null) {
            loginView.saveUser(adminCredentials);
            loginView.hideProgress();
            loginView.navigateToDashboard();
        }
    }

    @Override
    public void onError(String message) {
        if(loginView != null) {
            loginView.hideProgress();
            loginView.showError(message);
        }
    }
}
