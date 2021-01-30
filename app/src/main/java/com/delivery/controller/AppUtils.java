package com.delivery.controller;

import android.content.Context;

import com.delivery.ModLogin;
import com.google.gson.Gson;

public class AppUtils {

    public static boolean isNullOrEmpty(String input) {
        return input == null || input.isEmpty();
    }

    public static void setUserDetails(ModLogin loginModel) {
        if (loginModel != null) {
            String userDetail = new Gson().toJson(loginModel);
            PrefUtil.getInstance(AppController.getInstance()).putData(AppConstant.PREF_USER_DATA, userDetail);
        } else {
            PrefUtil.getInstance(AppController.getInstance()).removeKeyData(AppConstant.PREF_USER_DATA);
        }
    }

    public static void updateUserDetails(ModLogin loginModel) {
        if (loginModel != null) {
            String userDetail = new Gson().toJson(loginModel);
            PrefUtil.getInstance(AppController.getInstance()).putData(AppConstant.PREF_USER_DATA, userDetail);
        } else {
            PrefUtil.getInstance(AppController.getInstance()).removeKeyData(AppConstant.PREF_USER_DATA);
        }
    }

    public static ModLogin getUserDetails() {
        String userDetail = PrefUtil.getInstance(AppController.getInstance()).getPreferences().getString(AppConstant.PREF_USER_DATA, null);
        ModLogin loginModel = null;
        if (userDetail != null)
            loginModel = new Gson().fromJson(userDetail, ModLogin.class);
        return loginModel;
    }
}
