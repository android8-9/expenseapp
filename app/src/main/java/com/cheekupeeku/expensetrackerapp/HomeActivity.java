package com.cheekupeeku.expensetrackerapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cheekupeeku.expensetrackerapp.databinding.HomeBinding;

public class HomeActivity extends AppCompatActivity {
    HomeBinding binding;
    SharedPreferences sp;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomeBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        setSupportActionBar(binding.homeToolBar);
        sp = getSharedPreferences("user",MODE_PRIVATE);
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendUserToAddExpenseActivity();
            }
        });
    }
    private void sendUserToAddExpenseActivity(){
        Intent in = new Intent(this,AddExpenseActivity.class);
        startActivity(in);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Logout");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String title = item.getTitle().toString();
        if(title.equals("Logout")){
            SharedPreferences.Editor editor = sp.edit();
            editor.clear();
            editor.commit();
            sendUserToLoginActivity();
        }
        return super.onOptionsItemSelected(item);
    }
    private void sendUserToLoginActivity(){
        Intent in= new Intent(this,LoginActivity.class);
        startActivity(in);
        finish();
    }
}
