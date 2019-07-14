package com.example.borsalino.Remote;


import com.example.borsalino.Model.APIResponse;
import com.example.borsalino.Model.Category;
import com.example.borsalino.Model.Product;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IMyAPI {
    @FormUrlEncoded
    @POST("login.php")
    Call<APIResponse> loginUser(@Field("email") String email, @Field("pass") String pass);

    @FormUrlEncoded
    @POST("conex.php")
    Call<APIResponse> registerUser(@Field("nombre") String nombre, @Field("apellido") String apellido, @Field("email") String email, @Field("pass") String pass);

    @GET("categoria.php")
    Observable<List<Category>> getMenu();

    @FormUrlEncoded
    @POST("productos.php")
    Observable<List<Product>> getProduct(@Field("menuid") String menuID);
}
