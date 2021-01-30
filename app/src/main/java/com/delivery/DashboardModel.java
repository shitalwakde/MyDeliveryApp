package com.delivery;

import java.util.ArrayList;

public class DashboardModel {
    String nextSlot;
    String todayPendingOrder;
    String todayCompleteOrder;
    String totalPendingOrder;
    String totalCompleteOrder;
    String success;
    String message;
    String msg;

    ArrayList<Product> orderList;

    public ArrayList<Product> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Product> orderList) {
        this.orderList = orderList;
    }

    public String getNextSlot() {
        return nextSlot;
    }

    public void setNextSlot(String nextSlot) {
        this.nextSlot = nextSlot;
    }

    public String getTodayPendingOrder() {
        return todayPendingOrder;
    }

    public void setTodayPendingOrder(String todayPendingOrder) {
        this.todayPendingOrder = todayPendingOrder;
    }

    public String getTodayCompleteOrder() {
        return todayCompleteOrder;
    }

    public void setTodayCompleteOrder(String todayCompleteOrder) {
        this.todayCompleteOrder = todayCompleteOrder;
    }

    public String getTotalPendingOrder() {
        return totalPendingOrder;
    }

    public void setTotalPendingOrder(String totalPendingOrder) {
        this.totalPendingOrder = totalPendingOrder;
    }

    public String getTotalCompleteOrder() {
        return totalCompleteOrder;
    }

    public void setTotalCompleteOrder(String totalCompleteOrder) {
        this.totalCompleteOrder = totalCompleteOrder;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
