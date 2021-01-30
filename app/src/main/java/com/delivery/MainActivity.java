package com.delivery;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.MenuItemCompat;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.delivery.controller.AppConstant;
import com.delivery.controller.AppUtils;
import com.delivery.controller.PrefUtil;
import com.delivery.order.OrderActivity;
import com.delivery.utils.RestClient;
import com.google.gson.JsonObject;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TimerTask timerTask;
    Long noDelay = 0L;
    Long everyoneminute = 10000L;
    Timer timer;
    CardView cardTodayPend, cardTodayComp, cardTotalPend, cardTotalComp;
    public static TextView tv;
    public ImageView iv;
    ProgressBar progressBar;
    TextView tv_slot, tv_pending_count, tv_completed_count, tv_total_pending_count, tv_total_completed_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        init();
    }

    private void init(){
        cardTodayPend = (CardView)findViewById(R.id.cardTodayPend);
        cardTodayComp = (CardView)findViewById(R.id.cardTodayComp);
        cardTotalPend = (CardView)findViewById(R.id.cardTotalPend);
        cardTotalComp = (CardView)findViewById(R.id.cardTotalComp);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        tv_slot = (TextView) findViewById(R.id.tv_slot);
        tv_pending_count = (TextView) findViewById(R.id.tv_pending_count);
        tv_completed_count = (TextView) findViewById(R.id.tv_completed_count);
        tv_total_pending_count = (TextView) findViewById(R.id.tv_total_pending_count);
        tv_total_completed_count = (TextView) findViewById(R.id.tv_total_completed_count);


        cardTodayPend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrderList("todayPending", "pending");
            }
        });

        cardTodayComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrderList("todayComplete", "complete");
            }
        });

        cardTotalPend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrderList("totalPending", "pending");
            }
        });

        cardTotalComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getOrderList("totalComplete", "complete");
            }
        });

    }

    private void getOrderList(String type, String typeStatus){
        progressBar.setVisibility(View.VISIBLE);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deliveryBoyId", AppUtils.getUserDetails().getDeliveryBoyId());
        jsonObject.addProperty("type", type);

        new RestClient().getApiService().getOrderList(jsonObject, new Callback<DashboardModel>() {
            @Override
            public void success(DashboardModel dashboardModel, Response response) {
                progressBar.setVisibility(View.GONE);
                if(dashboardModel.getSuccess().equals("1")){
                    Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                    intent.putExtra("List", dashboardModel.getOrderList());
                    intent.putExtra("typeStatus", typeStatus);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, dashboardModel.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void getDashboard(){
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("deliveryBoyId", AppUtils.getUserDetails().deliveryBoyId);

        new RestClient().getApiService().getDashboard(jsonObject, new Callback<DashboardModel>() {
            @Override
            public void success(DashboardModel dashboardModel, Response response) {
                progressBar.setVisibility(View.GONE);
                if(dashboardModel.getSuccess().equals("1")){
                    setDashDetail(dashboardModel);
                }else{
                    Toast.makeText(MainActivity.this, dashboardModel.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void failure(RetrofitError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setDashDetail(DashboardModel dashboardModel) {
        tv_slot.setText(dashboardModel.getNextSlot());
        tv_pending_count.setText(dashboardModel.getTodayPendingOrder());
        tv_completed_count.setText(dashboardModel.getTodayCompleteOrder());
        tv_total_pending_count.setText(dashboardModel.getTotalPendingOrder());
        tv_total_completed_count.setText(dashboardModel.getTotalCompleteOrder());
    }


    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.VISIBLE);
        timerTask = new TimerTask() {
            @Override
            public void run() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        getDashboard();
                    }
                });
            }
        };

        timer=new Timer();
        timer.schedule(timerTask,noDelay,everyoneminute);
    }

    @Override
    public void onPause() {
        super.onPause();

        timer.cancel();
        timer.purge();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_menu, menu);

        MenuItem item = menu.findItem(R.id.action_open_cart);
        MenuItemCompat.setActionView(item, R.layout.notification_menu_layout);

        tv = (TextView) MenuItemCompat.getActionView(item).findViewById(R.id.actionbar_notifcation_textview);
        //tv.setText(sharedPreferences.getCartCount());
        iv = (ImageView) MenuItemCompat.getActionView(item).findViewById(R.id.actionbar_notifcation_iv);

        MenuItem item_logout = menu.findItem(R.id.action_my_logout);
        MenuItem item_account = menu.findItem(R.id.action_account);
        item_logout.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure want to Logout ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PrefUtil.getInstance(MainActivity.this).removeKeyData(AppConstant.PREF_USER_ID);
                        PrefUtil.getInstance(MainActivity.this).removeKeyData(AppConstant.PREF_USER_DATA);
                        Intent intent=new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finishAffinity();
                        dialog.dismiss();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();
                return true;
            }
        });

        item_account.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
                return true;
            }
        });

        return true;
    }

}
