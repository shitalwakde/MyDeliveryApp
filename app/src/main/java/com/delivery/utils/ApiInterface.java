package com.delivery.utils;

import com.delivery.DashboardModel;
import com.delivery.ModLogin;
import com.google.gson.JsonObject;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.POST;

public interface ApiInterface {

    @POST("/Deliveryboy/Login")
    void getLogin(@Body JsonObject jsonObject, Callback<ModLogin> signUpModelCallback);

    @POST("/Deliveryboy/VerifyOtp")
    void getVerifyOtp(@Body JsonObject jsonObject, Callback<ModLogin> signUpModelCallback);

    @POST("/Deliveryboy/getProfile")
    void getProfile(@Body JsonObject jsonObject, Callback<ModLogin> signUpModelCallback);

    @POST("/Deliveryboy/updateProfile")
    void updateProfile(@Body JsonObject jsonObject, Callback<ModLogin> signUpModelCallback);

    @POST("/Deliveryboy/Dashboard")
    void getDashboard(@Body JsonObject jsonObject, Callback<DashboardModel> signUpModelCallback);

    @POST("/Deliveryboy/getOrderList")
    void getOrderList(@Body JsonObject jsonObject, Callback<DashboardModel> signUpModelCallback);

    @POST("/Deliveryboy/orderStatus")
    void orderStatus(@Body JsonObject jsonObject, Callback<DashboardModel> signUpModelCallback);

}
