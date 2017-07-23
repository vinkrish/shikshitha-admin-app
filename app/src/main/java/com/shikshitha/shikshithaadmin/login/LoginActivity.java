package com.shikshitha.shikshithaadmin.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.shikshitha.shikshithaadmin.R;
import com.shikshitha.shikshithaadmin.SplashActivity;
import com.shikshitha.shikshithaadmin.dashboard.DashboardActivity;
import com.shikshitha.shikshithaadmin.model.AdminCredentials;
import com.shikshitha.shikshithaadmin.model.Credentials;
import com.shikshitha.shikshithaadmin.util.SharedPreferenceUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginView{

    @BindView(R.id.login_id_et)
    EditText loginId;
    @BindView(R.id.password_et) EditText password;
    @BindView(R.id.login_id)
    TextInputLayout loginLayout;
    @BindView(R.id.password) TextInputLayout passwordLayout;
    @BindView(R.id.progress)
    ProgressBar progressBar;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        presenter = new LoginPresenterImpl(this);
    }

    public void login(View view) {
        if(validate()) {
            Credentials c = new Credentials();
            c.setUsername(loginId.getText().toString());
            c.setPassword(password.getText().toString());
            presenter.validateCredentials(c);
        }
    }

    private void showSnackbar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        showSnackbar(message);
    }

    @Override
    public void noUser() {
        showSnackbar(getString(R.string.no_user));
    }

    @Override
    public void saveUser(AdminCredentials adminCredentials) {
        SharedPreferenceUtil.saveAdminCredentials(this, adminCredentials);
    }

    @Override
    public void navigateToDashboard() {
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }

    public boolean validate(){
        if(loginId.getText().toString().isEmpty()){
            loginLayout.setError(getString(R.string.username_error));
            return false;
        } else if (loginId.getText().toString().length() != 10) {
            loginLayout.setError(getString(R.string.valid_mobile));
            return false;
        }else if (password.getText().toString().isEmpty() ||
                password.getText().toString().length() < 6) {
            passwordLayout.setError(getString(R.string.valid_password));
            return false;
        }
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
