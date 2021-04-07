package com.cheekupeeku.expensetrackerapp;

import com.cheekupeeku.expensetrackerapp.model.Expense;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class ExpenseApi {

  public interface ExpenseApiInterface{
    @FormUrlEncoded
    @POST("/AddExpenseController")
    public Call<Expense> saveExpense(@Field("categoryId")String categoryId,
                                     @Field("userId") String userId,
                                     @Field("amount") String amount,
                                     @Field("edate") String edate,
                                     @Field("paymentMode") String paymentMode,
                                     @Field("tag") String tag);
  }
}
