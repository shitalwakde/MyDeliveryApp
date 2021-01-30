package com.delivery.order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.delivery.Product;
import com.delivery.R;
import com.delivery.callback.OrderDetailLisener;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.MyViewHolder> {
    ArrayList<Product> mdata;
    OrderDetailLisener lisener;

    public OrderAdapter(ArrayList<Product> mdata, OrderDetailLisener lisener) {
        this.mdata = mdata;
        this.lisener = lisener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_adapter, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product products = mdata.get(position);
        holder.tvOrderId.setText(products.getOrderId());
        holder.tvOrderDate.setText(products.getOrderDate());
        holder.tvOrderStatus.setText(products.getOrderStatus());
        holder.tvPaymentStatus.setText(products.getPaymentType());
        holder.tvOrderAmount.setText("\u20B9 "+products.getOrderAmount());
        holder.tvAddress.setText(products.getDeliveryHouseNo()+", "+products.getDeliveryArea()+", "+products.getDeliveryLandmark()+", "
                +products.getDeliveryCity()+", "+products.getDeliveryPincode()+", "+products.getDeliveryState());
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvOrderId, tvOrderDate, tvAddress, tvOrderStatus, tvPaymentStatus, tvOrderAmount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvOrderId = (TextView)itemView.findViewById(R.id.txt_order_no);
            tvOrderDate = (TextView)itemView.findViewById(R.id.txt_date_time);
            tvAddress = (TextView)itemView.findViewById(R.id.txtAddress);
            tvOrderStatus = (TextView)itemView.findViewById(R.id.tv_orderStatus);
            tvPaymentStatus = (TextView)itemView.findViewById(R.id.tv_payment_status);
            tvOrderAmount = (TextView)itemView.findViewById(R.id.tv_orderAmount);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lisener.orderDetailLisener(mdata.get(getAdapterPosition()));
                }
            });
        }
    }
}
