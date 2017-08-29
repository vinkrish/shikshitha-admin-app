package com.shikshitha.shikshithaadmin.version;

import com.shikshitha.shikshithaadmin.model.AppVersion;

import java.util.List;

/**
 * Created by Vinay on 28-08-2017.
 */

interface VersionInteractor {
    interface OnFinishedListener {
        void onError(String message);

        void onAppVersionsReceived(List<AppVersion> appVersionList);

        void onSuccess();
    }

    void getAppVersions(OnFinishedListener listener);

    void addAppVersion(AppVersion appVersion, OnFinishedListener listener);

    void updateAppVersion(AppVersion appVersion, OnFinishedListener listener);

    void deleteAppVersion(int id, OnFinishedListener listener);
}
