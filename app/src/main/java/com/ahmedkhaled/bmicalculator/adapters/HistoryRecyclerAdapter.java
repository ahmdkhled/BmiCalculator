package com.ahmedkhaled.bmicalculator.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmedkhaled.bmicalculator.R;
import com.ahmedkhaled.bmicalculator.backend.HistoryDatabase;
import com.ahmedkhaled.bmicalculator.model.history;

import java.util.ArrayList;

/**
 * Created by Ahmed Khaled on 9/27/2017.
 */

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.HistoryHolder> {

    private ArrayList<history> historyList;
    private Context context;
    private HistoryDatabase historyDatabase;

    public HistoryRecyclerAdapter(Context context, ArrayList<history> historyList) {
        this.context = context;
        this.historyList = historyList;
        historyDatabase=new HistoryDatabase(context);
    }


    @NonNull
    @Override
    public HistoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.row_history,parent,false);
        return new HistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryHolder holder, int position) {
        holder.weight.setText(historyList.get(position).weight);
        holder.height.setText(historyList.get(position).height);
        holder.result.setText(historyList.get(position).result);
        holder.date.setText(historyList.get(position).getDate());

        final int index=position;
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                historyDatabase.delete(historyList.get(index).id);
                historyList.remove(index);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }



    class HistoryHolder extends RecyclerView.ViewHolder{
        int index;
        TextView weight,height,result,date;
        ImageView delete;

        HistoryHolder(View itemView) {
            super(itemView);
            weight= itemView.findViewById(R.id.weighthistory);
            height=  itemView.findViewById(R.id.heightHistory);
            result=  itemView.findViewById(R.id.resultHistory);
            date=  itemView.findViewById(R.id.date);
            delete=  itemView.findViewById(R.id.delete);


        }



    }
}
