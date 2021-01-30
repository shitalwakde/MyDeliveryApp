package com.delivery.order;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.delivery.Product;
import com.delivery.R;
import com.delivery.callback.OrderDetailLisener;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity implements OrderDetailLisener {

    RecyclerView rv_orders;
    ArrayList<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

    }

    private void init(){
        if (getIntent() != null) {
            productList = (ArrayList<Product>) getIntent().getSerializableExtra("List");
        }
        rv_orders = (RecyclerView) findViewById(R.id.rv_orders);

        OrderAdapter adapter = new OrderAdapter(productList, this);
        rv_orders.setAdapter(adapter);

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

    @Override
    public void orderDetailLisener(Product product) {
        Intent intent = new Intent(OrderActivity.this, OrderDetailActivity.class);
        intent.putExtra("List", product);
        intent.putExtra("typeStatus", getIntent().getStringExtra("typeStatus"));
        startActivity(intent);
    }
}
