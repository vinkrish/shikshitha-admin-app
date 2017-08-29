package com.shikshitha.shikshithaadmin.version;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.shikshitha.shikshithaadmin.R;
import com.shikshitha.shikshithaadmin.model.AppVersion;
import com.shikshitha.shikshithaadmin.util.DividerItemDecoration;
import com.shikshitha.shikshithaadmin.util.NetworkUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VersionActivity extends AppCompatActivity implements VersionView {
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private VersionPresenter presenter;
    private VersionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new VersionPresenterImpl(this, new VersionInteractorImpl());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this));

        adapter = new VersionAdapter(new ArrayList<AppVersion>(0), mItemListener);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newVersion();
            }
        });

        refreshLayout.setColorSchemeColors(
                ContextCompat.getColor(this, R.color.colorPrimary),
                ContextCompat.getColor(this, R.color.colorAccent),
                ContextCompat.getColor(this, R.color.colorPrimaryDark)
        );

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getAppVersions();
            }
        });

        if(NetworkUtil.isNetworkAvailable(this)) {
            presenter.getAppVersions();
        }
    }

    @Override
    public void showProgress() {
        refreshLayout.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void reload() {
        startActivity(new Intent(this, VersionActivity.class));
        finish();
    }

    @Override
    public void showError(String message) {
        showSnackbar(message);
    }

    private void showSnackbar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showVersions(List<AppVersion> appVersions) {
        adapter.replaceData(appVersions);
    }

    VersionAdapter.OnItemClickListener mItemListener = new VersionAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(final AppVersion appVersion) {
            AlertDialog.Builder builder = new AlertDialog.Builder(VersionActivity.this);
            View view = getLayoutInflater().inflate(R.layout.version_edit, null);
            EditText verId = view.findViewById(R.id.version_id_et);
            verId.setText(String.valueOf(appVersion.getVersionId()));
            verId.setKeyListener(null);
            final EditText verName = view.findViewById(R.id.version_name_et);
            verName.setText(appVersion.getVersionName());
            final EditText verStatus = view.findViewById(R.id.version_status_et);
            verStatus.setText(appVersion.getStatus());
            builder.setView(view);

            builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(NetworkUtil.isNetworkAvailable(VersionActivity.this)) {
                        appVersion.setVersionName(verName.getText().toString());
                        appVersion.setStatus(verStatus.getText().toString());
                        presenter.updateAppVersion(appVersion);
                    } else {
                        showSnackbar("You are offline !");
                    }
                }
            });
            builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(NetworkUtil.isNetworkAvailable(VersionActivity.this)) {
                        presenter.deleteAppVersion(appVersion.getId());
                    } else {
                        showSnackbar("You are offline !");
                    }
                }
            });
            builder.show();
        }
    };

    private void newVersion() {
        final AppVersion appVersion = new AppVersion();
        AlertDialog.Builder builder = new AlertDialog.Builder(VersionActivity.this);
        View view = getLayoutInflater().inflate(R.layout.version_edit, null);
        final EditText verId = view.findViewById(R.id.version_id_et);
        final EditText verName = view.findViewById(R.id.version_name_et);
        final EditText verStatus = view.findViewById(R.id.version_status_et);
        builder.setView(view);

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(NetworkUtil.isNetworkAvailable(VersionActivity.this)) {
                    appVersion.setVersionId(Integer.parseInt(verId.getText().toString()));
                    appVersion.setVersionName(verName.getText().toString());
                    appVersion.setStatus(verStatus.getText().toString());
                    presenter.addAppVersion(appVersion);
                } else {
                    showSnackbar("You are offline !");
                }
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }
}
