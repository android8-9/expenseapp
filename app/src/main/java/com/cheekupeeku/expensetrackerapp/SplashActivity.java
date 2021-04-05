package com.cheekupeeku.expensetrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cheekupeeku.expensetrackerapp.databinding.SplashBinding;

public class SplashActivity extends AppCompatActivity {
    SplashBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SplashBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        new SplashThread().start();
    }
    class SplashThread extends Thread{
        public void run(){
            try{
                Thread.sleep(5000);
                Intent in = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(in);
                finish();
            }
            catch (Exception e){

            }
        }
    }
}
