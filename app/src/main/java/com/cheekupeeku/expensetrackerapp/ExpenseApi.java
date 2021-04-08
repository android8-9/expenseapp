package com.cheekupeeku.expensetrackerapp;

import com.cheekupeeku.expensetrackerapp.model.Expense;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public class ExpenseApi {
  private static ExpenseApiInterface apiInterface;
  public static ExpenseApiInterface getApiInstance(){
    if(apiInterface == null){
      Retrofit retrofit = new Retrofit.Builder()
              .baseUrl(ServerAddress.BASE_URL)
              .addConverterFactory(GsonConverterFactory.create())
              .build();
      apiInterface = retrofit.create(ExpenseApiInterface.class);
    }
    return apiInterface;
  }
  public interface ExpenseApiInterface{
    @FormUrlEncoded
    @POST("/AddExpenseController")
    public Call<Expense> saveExpense(@Field("categoryId")String categoryId,
                                     @Field("userId") String userId,
                                     @Field("amount") String amount,
                                     @Field("edate") String edate,
                                     @Field("paymentMode") String paymentMode,
                                     @Field("tag") String tag);
    @GET("/ExpenseListController")
    public Call<ArrayList<Expense>> getExpenseList(@QueryMap Map<String,String> data);
  }
}
