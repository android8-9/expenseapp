package com.cheekupeeku.expensetrackerapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cheekupeeku.expensetrackerapp.databinding.ExpenseItemListBinding;
import com.cheekupeeku.expensetrackerapp.model.Expense;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ExpenseViewHolder> {
    Context context;
    ArrayList<Expense>al;
    public ExpenseAdapter(Context context,ArrayList<Expense>al){
        this.context = context;
        this.al = al;
    }
    @NonNull
    @Override
    public ExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ExpenseItemListBinding binding = ExpenseItemListBinding.inflate(LayoutInflater.from(context),parent,false);
        return new ExpenseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseViewHolder holder, int position) {
        Expense e  = al.get(position);
        holder.binding.tvTag.setText(e.getTag());
        holder.binding.tvAmount.setText(e.getAmount()+" Rs");
        holder.binding.tvCategory.setText(e.getCategoryName());
        holder.binding.tvDate.setText(e.getEdate());
        holder.binding.tvPaymentMode.setText(e.getPaymentMode());
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class ExpenseViewHolder extends RecyclerView.ViewHolder{
      ExpenseItemListBinding binding;
      public ExpenseViewHolder(ExpenseItemListBinding binding){
          super(binding.getRoot());
          this.binding = binding;
      }
  }
}
