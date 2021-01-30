package com.delivery.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.delivery.DashboardModel;
import com.delivery.MainActivity;
import com.delivery.Product;
import com.delivery.R;
import com.delivery.controller.AppUtils;
import com.delivery.utils.RestClient;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {

    TextView tvCancel, tvDeliver, tvOrderAmount, tvOrderDateTop, tvOrderStatus, tvCustName, tvCustEmail, tvCustAddress, tvOrderDate, tvPurchaseAmount,
            tvDeliveryCharges, tvGst, tvWalletUsed, tvTotalAmount, tvPaymentType;
    LinearLayout ll_bottom;
    RecyclerView rvOrderView;
    Product productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();
    }

    private void init(){
        ll_bottom = (LinearLayout)findViewById(R.id.ll_bottom);
        rvOrderView = (RecyclerView) findViewById(R.id.rv_order_view);
        tvCancel = (TextView) findViewById(R.id.tv_cancel);
        tvDeliver = (TextView) findViewById(R.id.tv_deliver);
        tvOrderAmount = (TextView) findViewById(R.id.tv_orderAmount);
        tvOrderDateTop = (TextView) findViewById(R.id.tv_deliveryDateTop);
        tvOrderStatus = (TextView) findViewById(R.id.tv_orderStatus);
        tvCustName = (TextView) findViewById(R.id.tv_name);
        tvCustEmail = (TextView) findViewById(R.id.tv_email);
        tvCustAddress = (TextView) findViewById(R.id.tv_address);
        tvOrderDate = (TextView) findViewById(R.id.tv_orderDate);
        tvPurchaseAmount = (TextView) findViewById(R.id.tv_purchaseAmount);
        tvDeliveryCharges = (TextView) findViewById(R.id.tv_deliveryCharge);
        tvGst = (TextView) findViewById(R.id.tv_gst);
        tvWalletUsed = (TextView) findViewById(R.id.tv_wallet);
        tvTotalAmount = (TextView) findViewById(R.id.tv_totalFinalAmount);
        tvPaymentType = (TextView) findViewById(R.id.tv_paymentStatus);

        if (getIntent() != null) {
            productList = (Product) getIntent().getSerializableExtra("List");
            if(getIntent().getStringExtra("typeStatus").equals("pending")){
                ll_bottom.setVisibility(View.VISIBLE);
            }else{
                ll_bottom.setVisibility(View.GONE);
            }
        }
        setOrderDetail(productList);

        OrderDetailAdapter adapter = new OrderDetailAdapter(productList.getOrderDetail());
        rvOrderView.setAdapter(adapter);


        tvDeliver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderStatus();
            }
        });
    }

    private void setOrderDetail(Product productList) {
        tvOrderAmount.setText("\u20B9 "+productList.getOrderAmount());
        tvOrderDateTop.setText(productList.getOrderDate());
        tvOrderStatus.setText(productList.getOrderStatus());
        tvCustName.setText(productList.getDeliveryName());
        tvCustEmail.setText(productList.getDeliveryEmail()+", "+productList.getDeliveryMobile());
        tvCustAddress.setText(productList.getDeliveryHouseNo()+", "+productList.getDeliveryArea()+", "+productList.getDeliveryLandmark()+", "
                +productList.getDeliveryCity()+", "+productList.getDeliveryPincode()+", "+productList.getDeliveryState());
        tvOrderDate.setText(productList.getOrderDate());
        tvPurchaseAmount.setText("\u20B9 "+productList.getOrderAmount());
        tvTotalAmount.setText("\u20B9 "+productList.getOrderAmount());
        tvPaymentType.setText(productList.getPaymentType());
        if(productList.getDeliveryCharges().equals("0")){
            tvDeliveryCharges.setText("-  ");
        }else {
            tvDeliveryCharges.setText("\u20B9 " + productList.getOrderAmount());
        }

    }

    private void orderStatus(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deliveryBoyId", AppUtils.getUserDetails().getDeliveryBoyId());
        jsonObject.addProperty("orderStatus", "Delivered");
        jsonObject.addProperty("orderId", productList.getOrderId());

        new RestClient().getApiService().orderStatus(jsonObject, new Callback<DashboardModel>() {
            @Override
            public void success(DashboardModel dashboardModel, Response response) {
                if(dashboardModel.getSuccess().equals("1")){
                    Intent intent = new Intent(OrderDetailActivity.this, MainActivity.class);
                    startActivity(intent);
                    finishAffinity();
                }else{
                    Toast.makeText(OrderDetailActivity.this, dashboardModel.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(OrderDetailActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //onBackPressed();
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
