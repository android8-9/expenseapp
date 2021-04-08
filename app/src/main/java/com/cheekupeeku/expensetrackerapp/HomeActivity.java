package com.cheekupeeku.expensetrackerapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.cheekupeeku.expensetrackerapp.adapter.ExpenseAdapter;
import com.cheekupeeku.expensetrackerapp.databinding.HomeBinding;
import com.cheekupeeku.expensetrackerapp.model.Expense;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    HomeBinding binding;
    SharedPreferences sp;
    int currentUserId;
    ArrayList<Expense> expenseList;
    ExpenseAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = HomeBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
        setSupportActionBar(binding.homeToolBar);
        sp = getSharedPreferences("user",MODE_PRIVATE);
        currentUserId = sp.getInt("id",0);

        // Fetch expenses of current user
        ProgressDialog pd = new ProgressDialog(HomeActivity.this);
        pd.setMessage("please wait while loading the data..");
        pd.show();

        ExpenseApi.ExpenseApiInterface apiInterface = ExpenseApi.getApiInstance();
        HashMap<String,String> hm = new HashMap<String,String>();
        hm.put("userId",""+currentUserId);
        Call<ArrayList<Expense>> call = apiInterface.getExpenseList(hm);
        call.enqueue(new Callback<ArrayList<Expense>>() {
            @Override
            public void onResponse(Call<ArrayList<Expense>> call, Response<ArrayList<Expense>> response) {
                pd.dismiss();
                if(response.code() == 200){
                    expenseList = response.body();
                    Toast.makeText(HomeActivity.this, ""+expenseList, Toast.LENGTH_SHORT).show();
                    adapter = new ExpenseAdapter(HomeActivity.this,expenseList);
                    binding.rv.setAdapter(adapter);
                    binding.rv.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Expense>> call, Throwable t) {
               pd.dismiss();
                Toast.makeText(HomeActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });

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
