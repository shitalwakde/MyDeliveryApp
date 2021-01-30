package com.delivery.order;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.delivery.Product;
import com.delivery.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.MyViewHolder> {
    ArrayList<Product> mdata;

    public OrderDetailAdapter(ArrayList<Product> mdata) {
        this.mdata = mdata;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_detail_adapter, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Product products = mdata.get(position);
        Picasso.with(holder.itemView.getContext()).load(products.getProductImage()).into(holder.ivPrdImage);
        holder.tvProductName.setText(products.getProductName());
        holder.tvSubProductName.setText(products.getProductName());
        holder.tvDiscountPrice.setText("\u20B9 " + products.getProductFinalAmount());
        holder.tvQuantity.setText(products.getProductQuantity());
        if(products.getProductDiscount().equals("0")){
            holder.rlDiscount.setVisibility(View.GONE);
            holder.tvPrice.setVisibility(View.GONE);
        }else {
            holder.rlDiscount.setVisibility(View.VISIBLE);
            holder.tvDiscount.setText(products.getProductDiscount() + "%");
            holder.tvPrice.setVisibility(View.VISIBLE);
            holder.tvPrice.setText("\u20B9 " + products.getProductGrossAmount());
            holder.tvPrice.setPaintFlags(holder.tvPrice.getPaintFlags()
                    | Paint.STRIKE_THRU_TEXT_FLAG);
        }

        if(products.getProductRate().equals("")){
            holder.tvRating.setVisibility(View.INVISIBLE);
        }else{
            holder.tvRating.setVisibility(View.VISIBLE);
            holder.tvRating.setText(products.getProductRate()+" Ratings");
        }
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView ivPrdImage;
        TextView tvProductName,tvSubProductName, tvRating, tvPrice, tvDiscountPrice, tvQuantity, tvDiscount;
        RelativeLayout rlDiscount;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPrdImage = (ImageView)itemView.findViewById(R.id.iv_best);
            tvRating = (TextView)itemView.findViewById(R.id.tv_rating);
            tvProductName = (TextView)itemView.findViewById(R.id.tv_pr_name);
            tvSubProductName = (TextView)itemView.findViewById(R.id.tv_pr_sub_name);
            tvPrice = (TextView)itemView.findViewById(R.id.tv_price);
            tvDiscountPrice = (TextView)itemView.findViewById(R.id.tv_discount_price);
            tvQuantity = (TextView)itemView.findViewById(R.id.tvPiece);
            tvDiscount = (TextView)itemView.findViewById(R.id.txtDiscountOff);
            rlDiscount = (RelativeLayout) itemView.findViewById(R.id.rl_discount);
        }
    }
}
