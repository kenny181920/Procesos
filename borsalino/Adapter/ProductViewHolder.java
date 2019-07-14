package com.example.borsalino.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.borsalino.Interface.IItemClickListener;
import com.example.borsalino.R;

public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView img_product;
    TextView txt_product_name, txt_price;

    IItemClickListener itemClickListener;

    public void setItemClickListener(IItemClickListener itemClickListener){
        this.itemClickListener = itemClickListener;
    }

    public ProductViewHolder(View itemView){
        super(itemView);

        img_product = (ImageView)itemView.findViewById(R.id.image_product);
        txt_product_name = (TextView)itemView.findViewById(R.id.txt_product_name);
        txt_price = (TextView)itemView.findViewById(R.id.txt_price);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        itemClickListener.onClick(v);
    }
}
