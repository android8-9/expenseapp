package com.cheekupeeku.expensetrackerapp;

import com.cheekupeeku.expensetrackerapp.model.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class UserApi {
   private static  UserApiInterface userApiInstance;

   public static UserApiInterface getUserApiInstance(){
       if(userApiInstance == null) {
           Retrofit retrofit = new Retrofit.Builder()
                   .baseUrl(ServerAddress.BASE_URL)
                   .addConverterFactory(GsonConverterFactory.create())
                   .build();
           userApiInstance = retrofit.create(UserApiInterface.class);
       }
       return userApiInstance;
   }
   public interface UserApiInterface{
       //https://track-own-expenses.herokuapp.com/RegisterController
       @FormUrlEncoded
       @POST("/RegisterController")
       Call<User> registerUser(@Field("mobile") String mobile, @Field("password") String password);

       //https://track-own-expenses.herokuapp.com/LoginController
       @FormUrlEncoded
       @POST("/LoginController")
       Call<User> authenticateUser(@Field("mobile") String mobile, @Field("password") String password);
   }
}
