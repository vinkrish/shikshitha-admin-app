package com.shikshitha.shikshithaadmin.version;

import com.shikshitha.shikshithaadmin.model.AppVersion;

import java.util.List;

/**
 * Created by Vinay on 28-08-2017.
 */

interface VersionView {
    void showProgress();

    void hideProgress();

    void reload();

    void showError(String message);

    void showVersions(List<AppVersion> appVersions);
}
