package com.example.mytrainapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class TrainScheduleAdapter extends RecyclerView.Adapter<TrainScheduleAdapter.ProductHolder> {


    ArrayList<TrainScheduleModel> trainschedulemodel;
    Context context;
    public TrainScheduleAdapter(Context context, ArrayList<TrainScheduleModel> trainschedulemodel) {
        this.trainschedulemodel = trainschedulemodel;
        this.context = context;
    }

    // inflating single row layout
    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductHolder(LayoutInflater.from(context).inflate(R.layout.train_schedule, parent, false));
    }



    // functiomality of each row according to position
    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {

        holder.name.setText(trainschedulemodel.get(position).getTrain());
        holder.slNo.setText(trainschedulemodel.get(position).getNo());
        holder.depTime.setText(trainschedulemodel.get(position).getDep());
        holder.arrTime.setText(trainschedulemodel.get(position).getArr());



    }


    // number of rows
    @Override
    public int getItemCount() {
        return trainschedulemodel.size();
    }


    // casting of views ( single row)
    class ProductHolder extends RecyclerView.ViewHolder {


        TextView slNo, name, depTime, arrTime;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            slNo = itemView.findViewById(R.id.sl_no);
            name = itemView.findViewById(R.id.train_name);
            depTime = itemView.findViewById(R.id.departure_time);
            arrTime = itemView.findViewById(R.id.arrival_time);

        }
    }
}
