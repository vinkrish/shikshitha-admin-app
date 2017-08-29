package com.shikshitha.shikshithaadmin.version;

import com.shikshitha.shikshithaadmin.model.AppVersion;

/**
 * Created by Vinay on 28-08-2017.
 */

interface VersionPresenter {
    void getAppVersions();

    void addAppVersion(AppVersion appVersion);

    void updateAppVersion(AppVersion appVersion);

    void deleteAppVersion(int id);
}
