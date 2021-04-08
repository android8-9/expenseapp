package com.cheekupeeku.expensetrackerapp;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.cheekupeeku.expensetrackerapp.databinding.AddExpenseBinding;
import com.cheekupeeku.expensetrackerapp.model.Category;
import com.cheekupeeku.expensetrackerapp.model.Expense;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddExpenseActivity extends AppCompatActivity {
    AddExpenseBinding binding;
    ArrayAdapter<Category>categoryArrayAdapter;
    String edate;
    SharedPreferences sp;
    int currentUserId;
    Category category;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AddExpenseBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());
         setSupportActionBar(binding.addExpenseToolBar);
         ActionBar ab = getSupportActionBar();
         ab.setDisplayHomeAsUpEnabled(true);
        sp = getSharedPreferences("user",MODE_PRIVATE);
        currentUserId = sp.getInt("id",0);
        CategoryApiInterface apiInterface = CategoryApi.getCategoryApiInstance();
        Call<ArrayList<Category>> call = apiInterface.getCategoryList();
        call.enqueue(new Callback<ArrayList<Category>>() {
            @Override
            public void onResponse(Call<ArrayList<Category>> call, Response<ArrayList<Category>> response) {
                if(response.code() == 200){
                    ArrayList<Category>categoryArrayList = response.body();
                    categoryArrayAdapter = new ArrayAdapter<>(AddExpenseActivity.this, android.R.layout.simple_list_item_checked,categoryArrayList);
                    binding.spinnerCategory.setAdapter(categoryArrayAdapter);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Category>> call, Throwable t) {
                Toast.makeText(AddExpenseActivity.this, ""+t, Toast.LENGTH_SHORT).show();
            }
        });
        binding.tvDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dp = new DatePickerDialog(AddExpenseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       edate = dayOfMonth+"-"+(month+1)+"-"+year;
                       binding.tvDate.setText(edate);
                    }
                }, calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                dp.show();
            }
        });
        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                category = (Category) binding.spinnerCategory.getSelectedItem();
                String tag = binding.etTag.getText().toString();
                String amount = binding.etAmount.getText().toString();
                String paymentMode = "";
                if(binding.rdCash.isChecked()) paymentMode = "Cash";
                else if(binding.rdOnline.isChecked()) paymentMode = "Online";
                ProgressDialog pd = new ProgressDialog(AddExpenseActivity.this);
                pd.setMessage("please wait while saving data...");
                pd.show();
                ExpenseApi.ExpenseApiInterface apiInterface1 = ExpenseApi.getApiInstance();
                Call<Expense> call = apiInterface1.saveExpense(""+category.getId(),currentUserId+"",amount,edate,paymentMode,tag);
                call.enqueue(new Callback<Expense>() {
                    @Override
                    public void onResponse(Call<Expense> call, Response<Expense> response) {
                        pd.dismiss();
                        if(response.code() == 200){
                            Toast.makeText(AddExpenseActivity.this, "Expense saved", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Expense> call, Throwable t) {
                        pd.dismiss();
                        Toast.makeText(AddExpenseActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
