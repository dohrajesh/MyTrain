package com.example.mytrainapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TicketDetailAdapter extends RecyclerView.Adapter<TicketDetailAdapter.TicketDetailHolder> {


    ArrayList<TicketDetailModel> ticketDetailModel;
    Context context;
    Boolean isFirstTime;
    public TicketDetailAdapter(Context context, ArrayList<TicketDetailModel> ticketDetailModel) {
        this.ticketDetailModel = ticketDetailModel;
        this.context = context;
    }

    // inflating single row layout
    @NonNull
    @Override
    public TicketDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TicketDetailHolder(LayoutInflater.from(context).inflate(R.layout.ticket_detail, parent, false));
    }



    // functiomality of each row according to position
    @Override
    public void onBindViewHolder(@NonNull TicketDetailHolder holder, int position) {

        holder.slNo.setText(ticketDetailModel.get(position).getSlNo());
        holder.passenger.setText(ticketDetailModel.get(position).getPassenger());
        holder.age.setText(ticketDetailModel.get(position).getAge());

        String[] berthArray = new String[] {"Select Berth" , "SL", "3A" , "2A"  ,"2S" , "CC"};
                ////To add the spinner
        ArrayAdapter<CharSequence> adapter= new ArrayAdapter<>(context , android.R.layout.simple_spinner_dropdown_item, berthArray);
        holder.berth.setAdapter(adapter);

        isFirstTime = true;




        Log.e("pos", isFirstTime + "");
        holder.berth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

              if (i  > 0)
                  Toast.makeText(context, berthArray[i], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }




    // number of rows
    @Override
    public int getItemCount() {
        return ticketDetailModel.size();
    }


    // casting of views ( single row)
    class TicketDetailHolder extends RecyclerView.ViewHolder {


        TextView slNo, passenger, age;
        Spinner berth;
        public TicketDetailHolder(@NonNull View itemView) {
            super(itemView);
            slNo = itemView.findViewById(R.id.sl_no);
            passenger = itemView.findViewById(R.id.passenger_name);
            age = itemView.findViewById(R.id.age);
            berth = itemView.findViewById(R.id.berth_spinner);

        }
    }
}
