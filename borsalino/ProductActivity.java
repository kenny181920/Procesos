package com.example.borsalino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.borsalino.Adapter.ProductAdapter;
import com.example.borsalino.Common.Common;
import com.example.borsalino.Model.Product;
import com.example.borsalino.Remote.IMyAPI;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class ProductActivity extends AppCompatActivity {

    IMyAPI mService;
    RecyclerView lst_product;
    TextView txt_banner_name;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mService = Common.getAPI();
        lst_product = (RecyclerView)findViewById(R.id.recycler_products);
        lst_product.setLayoutManager(new GridLayoutManager(this, 2));
        lst_product.setHasFixedSize(true);

        txt_banner_name = (TextView)findViewById(R.id.txt_menu_name);
        loadListProduct(Common.currentCategory.CATEGORIA_ID);
    }

    private void loadListProduct(String menuId) {
        compositeDisposable.add(mService.getProduct(menuId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Product>>() {
            @Override
            public void accept(List<Product> products) throws Exception {
                displayProductList(products);
            }
        }));
    }

    private void displayProductList(List<Product> products) {
        ProductAdapter adapter = new ProductAdapter(this,products);
        lst_product.setAdapter(adapter);
    }
}
