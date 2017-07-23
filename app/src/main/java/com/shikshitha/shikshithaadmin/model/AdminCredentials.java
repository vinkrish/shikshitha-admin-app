package com.shikshitha.shikshithaadmin.model;

/**
 * Created by Vinay on 20-07-2017.
 */

public class AdminCredentials {
    private String token;
    private long schoolId;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }
}
