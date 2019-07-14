package com.example.borsalino.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.borsalino.Interface.IItemClickListener;
import com.example.borsalino.Model.Product;
import com.example.borsalino.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductViewHolder> {

    Context context;
    List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList){
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.menu_item_layout, null);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {

        productViewHolder.txt_price.setText(new StringBuilder("$").append(productList.get(i).PRODUCTO_PRECIO).toString());
        productViewHolder.txt_product_name.setText(productList.get(i).PRODUCTO_NOMBRE);

        Picasso.with(context)
                .load(productList.get(i).PRODUCTO_DESCRIP)
                .into(productViewHolder.img_product);

        productViewHolder.setItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }
}
