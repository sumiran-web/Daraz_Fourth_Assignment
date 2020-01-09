package com.example.daraz_fourth_assignment.InterfaceClasses;

import com.example.daraz_fourth_assignment.ModelClass.ProductCollectionModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    // url
    @GET("simant_daraz_product_api.php")
    Call<ProductCollectionModel> parseProduct();
}
