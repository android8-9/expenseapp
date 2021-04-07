package com.cheekupeeku.expensetrackerapp;

import com.cheekupeeku.expensetrackerapp.model.Category;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class CategoryApi {
   private static CategoryApiInterface categoryApiInterface;
   public static CategoryApiInterface getCategoryApiInstance(){
       if(categoryApiInterface == null){
           Retrofit retrofit = new Retrofit.Builder()
                   .baseUrl(ServerAddress.BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
           categoryApiInterface  = retrofit.create(CategoryApiInterface.class);
       }
       return categoryApiInterface;
   }
}
interface CategoryApiInterface{
    @GET("/CategoryController")
    public Call<ArrayList<Category>> getCategoryList();
}
