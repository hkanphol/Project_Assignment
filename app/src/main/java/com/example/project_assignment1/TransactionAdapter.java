package com.example.project_assignment1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.TransitionAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.MyViewHolder> {
    Context context;
    ArrayList<TransactionModel> transactionModelArrayListList;

    public TransactionAdapter(Context context, ArrayList<TransactionModel> transactionModelArrayList){
        this.context = context;
        this.transactionModelArrayListList = transactionModelArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.one_recyler_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionAdapter.MyViewHolder holder, int position) {
        TransactionModel model=transactionModelArrayListList.get(position);
        String priority=model.getType();
        if (priority.equals("Expense")){
            holder.priority.setBackgroundResource(R.drawable.red_shape);
        }
        else {
            holder.priority.setBackgroundResource(R.drawable.green_shape);
        }
        holder.amount.setText(model.getAmount());
        holder.date.setText(model.getDate());
        holder.note.setText(model.getNote());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,UpdateActivity.class);
                intent.putExtra("id",transactionModelArrayListList.get(position).getId());
                intent.putExtra("amount",transactionModelArrayListList.get(position).getAmount());
                intent.putExtra("note",transactionModelArrayListList.get(position).getNote());
                intent.putExtra("type",transactionModelArrayListList.get(position).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionModelArrayListList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView note,amount,date;
        View priority;
        public MyViewHolder(@NotNull View itemView) {
            super(itemView);
            note=itemView.findViewById(R.id.note_one);
            amount=itemView.findViewById(R.id.amount_one);
            date=itemView.findViewById(R.id.date_one);
            priority=itemView.findViewById(R.id.priority_one);
        }
    }
}
