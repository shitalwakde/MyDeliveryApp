package com.delivery.controller;

import android.content.Context;

public class AppConstant {
    public static final String PREF_USER_ID="userid";
    public static final String PREF_USER_DATA = "user_detail";

    public static boolean isLogin(Context context){
        String userId= PrefUtil.getInstance(AppController.getInstance()).getPreferences().getString(PREF_USER_ID, null);
        return userId!=null;
    }
}
