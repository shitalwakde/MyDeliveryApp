package com.delivery;

import java.io.Serializable;
import java.util.ArrayList;

public class Product implements Serializable {
    private String orderId;
    private String userId;
    private String orderAmount;
    private String deliveryCharges;
    private String orderDate;
    private String paymentType;
    private String status;
    private String deliveryName;
    private String deliveryMobile;
    private String deliveryEmail;
    private String deliveryHouseNo;
    private String deliveryArea;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryLandmark;
    private String deliveryPincode;
    private String orderStatus;

    ArrayList<Product> orderDetail;

    private String orderDetailId;
    private String productId;
    private String quantity;
    private String productRate;
    private String productDiscount;
    private String productGrossAmount;
    private String productFinalAmount;
    private String amount;
    private String productName;
    private String finalAmount;
    private String productQuantity;
    private String productType;
    private String rate;
    private String discount;
    private String stockAvailable;
    private String isVarient;
    private String productImage;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getDeliveryCharges() {
        return deliveryCharges;
    }

    public void setDeliveryCharges(String deliveryCharges) {
        this.deliveryCharges = deliveryCharges;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryMobile() {
        return deliveryMobile;
    }

    public void setDeliveryMobile(String deliveryMobile) {
        this.deliveryMobile = deliveryMobile;
    }

    public String getDeliveryEmail() {
        return deliveryEmail;
    }

    public void setDeliveryEmail(String deliveryEmail) {
        this.deliveryEmail = deliveryEmail;
    }

    public String getDeliveryHouseNo() {
        return deliveryHouseNo;
    }

    public void setDeliveryHouseNo(String deliveryHouseNo) {
        this.deliveryHouseNo = deliveryHouseNo;
    }

    public String getDeliveryArea() {
        return deliveryArea;
    }

    public void setDeliveryArea(String deliveryArea) {
        this.deliveryArea = deliveryArea;
    }

    public String getDeliveryState() {
        return deliveryState;
    }

    public void setDeliveryState(String deliveryState) {
        this.deliveryState = deliveryState;
    }

    public String getDeliveryLandmark() {
        return deliveryLandmark;
    }

    public void setDeliveryLandmark(String deliveryLandmark) {
        this.deliveryLandmark = deliveryLandmark;
    }

    public String getDeliveryPincode() {
        return deliveryPincode;
    }

    public void setDeliveryPincode(String deliveryPincode) {
        this.deliveryPincode = deliveryPincode;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public ArrayList<Product> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(ArrayList<Product> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public String getOrderDetailId() {
        return orderDetailId;
    }

    public void setOrderDetailId(String orderDetailId) {
        this.orderDetailId = orderDetailId;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getProductRate() {
        return productRate;
    }

    public void setProductRate(String productRate) {
        this.productRate = productRate;
    }

    public String getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(String productDiscount) {
        this.productDiscount = productDiscount;
    }

    public String getProductGrossAmount() {
        return productGrossAmount;
    }

    public void setProductGrossAmount(String productGrossAmount) {
        this.productGrossAmount = productGrossAmount;
    }

    public String getProductFinalAmount() {
        return productFinalAmount;
    }

    public void setProductFinalAmount(String productFinalAmount) {
        this.productFinalAmount = productFinalAmount;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(String finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getStockAvailable() {
        return stockAvailable;
    }

    public void setStockAvailable(String stockAvailable) {
        this.stockAvailable = stockAvailable;
    }

    public String getIsVarient() {
        return isVarient;
    }

    public void setIsVarient(String isVarient) {
        this.isVarient = isVarient;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
