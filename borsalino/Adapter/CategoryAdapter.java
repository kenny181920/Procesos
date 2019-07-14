package com.example.borsalino.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.borsalino.Common.Common;
import com.example.borsalino.Interface.IItemClickListener;
import com.example.borsalino.Model.Category;
import com.example.borsalino.ProductActivity;
import com.example.borsalino.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryViewHolder> {

    Context context;
    List<Category> categories;

    public CategoryAdapter(Context context, List<Category> categories){
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.menu_item_layout, null);
        return new CategoryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, final int position) {
        Picasso.with(context)
                .load(categories.get(position).CATEGORIA_DESCRIP)
                .into(categoryViewHolder.img_product);
        categoryViewHolder.txt_menu_name.setText(categories.get(position).CATEGORIA_NOMBRE);

        categoryViewHolder.setItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View v) {
                Common.currentCategory = categories.get(position);

                context.startActivity(new Intent(context, ProductActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
