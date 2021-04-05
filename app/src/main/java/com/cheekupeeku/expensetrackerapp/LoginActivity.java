package com.cheekupeeku.expensetrackerapp;

import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cheekupeeku.expensetrackerapp.databinding.LoginBinding;

public class LoginActivity extends AppCompatActivity {
    LoginBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
    }
}
