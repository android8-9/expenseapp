package com.cheekupeeku.expensetrackerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cheekupeeku.expensetrackerapp.databinding.LoginBinding;
import com.cheekupeeku.expensetrackerapp.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    LoginBinding binding;
    SharedPreferences sp = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        sp = getSharedPreferences("user",MODE_PRIVATE);

        binding.tvNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToRegisterActivity();
            }
        });

        if(isUserLoggedIn())
            sendUserToHomeActivity();

        binding.btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobile = binding.etMobile.getText().toString();
                String password = binding.etPassword.getText().toString();
                if(TextUtils.isEmpty(mobile)){
                    binding.etMobile.setError("Mobile required");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    binding.etPassword.setError("Password required");
                    return;
                }

                ProgressDialog pd = new ProgressDialog(LoginActivity.this);
                pd.setMessage("please wait while authenticating..");
                pd.show();
                UserApi.UserApiInterface apiInterface = UserApi.getUserApiInstance();
                Call<User> call = apiInterface.authenticateUser(mobile,password);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        pd.dismiss();
                        if(response.code() == 200){
                            User user = response.body();
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("mobile",user.getMobile());
                            editor.putInt("id",user.getId());
                            editor.commit();
                            sendUserToHomeActivity();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                      pd.dismiss();
                        Toast.makeText(LoginActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private boolean isUserLoggedIn() {
       String status = sp.getString("mobile","");
       if(status.equals(""))
           return false;
       return true;
    }

    private void sendUserToRegisterActivity(){
        Intent in = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(in);
        finish();
    }
    private void sendUserToHomeActivity(){
        Intent in = new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(in);
        finish();

    }
}
