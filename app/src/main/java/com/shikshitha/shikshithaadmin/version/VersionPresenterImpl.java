package com.shikshitha.shikshithaadmin.version;

import com.shikshitha.shikshithaadmin.model.AppVersion;

import java.util.List;

/**
 * Created by Vinay on 28-08-2017.
 */

public class VersionPresenterImpl implements VersionPresenter, VersionInteractor.OnFinishedListener {

    private VersionView mView;
    private VersionInteractor mInteractor;

    VersionPresenterImpl(VersionView view, VersionInteractor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    @Override
    public void getAppVersions() {
        if (mView != null) {
            mView.showProgress();
            mInteractor.getAppVersions(this);
        }
    }

    @Override
    public void addAppVersion(AppVersion appVersion) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.addAppVersion(appVersion, this);
        }
    }

    @Override
    public void updateAppVersion(AppVersion appVersion) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.updateAppVersion(appVersion, this);
        }
    }

    @Override
    public void deleteAppVersion(int id) {
        if (mView != null) {
            mView.showProgress();
            mInteractor.deleteAppVersion(id, this);
        }
    }

    @Override
    public void onError(String message) {
        if (mView != null) {
            mView.hideProgress();
            mView.showError(message);
        }
    }

    @Override
    public void onAppVersionsReceived(List<AppVersion> appVersions) {
        if (mView != null) {
            mView.showVersions(appVersions);
            mView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if (mView != null) {
            mView.hideProgress();
            mView.reload();
        }
    }
}
