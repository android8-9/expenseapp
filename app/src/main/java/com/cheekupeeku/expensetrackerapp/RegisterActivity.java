package com.cheekupeeku.expensetrackerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cheekupeeku.expensetrackerapp.databinding.RegisterBinding;
import com.cheekupeeku.expensetrackerapp.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
  RegisterBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = RegisterBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        binding.btnSignup.setOnClickListener(new View.OnClickListener() {
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
                ProgressDialog pd = new ProgressDialog(RegisterActivity.this);
                pd.setMessage("Please wait while register..");
                pd.show();
                UserApi.UserApiInterface apiInterface = UserApi.getUserApiInstance();
                Call<User> call = apiInterface.registerUser(mobile,password);
                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        pd.dismiss();
                        if(response.code() == 200){
                            User user = response.body();
                            Log.e("User","===>"+user);
                            Toast.makeText(RegisterActivity.this, ""+user, Toast.LENGTH_SHORT).show();
                            sendUserToLoginActivity();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        pd.dismiss();
                        Toast.makeText(RegisterActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
    private void sendUserToLoginActivity(){
        Intent in = new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(in);
        finish();
    }
}
