package com.shikshitha.shikshithaadmin.dashboard;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shikshitha.shikshithaadmin.R;
import com.shikshitha.shikshithaadmin.fragment.HomeFragment;
import com.shikshitha.shikshithaadmin.util.ReplaceFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DashboardActivity extends AppCompatActivity {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        toolbar.setTitle(R.string.title_activity_dashboard);
        setSupportActionBar(toolbar);

        ReplaceFragment.replace(new HomeFragment(), getFragmentManager());

    }

}
