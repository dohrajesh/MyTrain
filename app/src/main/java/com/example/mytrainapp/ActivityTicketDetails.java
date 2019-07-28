package com.example.mytrainapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ActivityTicketDetails extends AppCompatActivity //implements AdapterView.OnItemSelectedListener
 {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);

        recyclerView = findViewById(R.id.ticket_details_recyclerview);

        ArrayList<TicketDetailModel> passengerList = new ArrayList<>();

        passengerList.add(new TicketDetailModel("1", "Rajesh", "24", "Robertsonpet"));
        passengerList.add(new TicketDetailModel("2", "Rajesh", "24", "Robertsonpet"));
        passengerList.add(new TicketDetailModel("3", "Rajesh", "24", "Robertsonpet"));
        passengerList.add(new TicketDetailModel("4", "Rajesh", "24", "Robertsonpet"));
        passengerList.add(new TicketDetailModel("5", "Rajesh", "24", "Robertsonpet"));
        passengerList.add(new TicketDetailModel("6", "Rajesh", "24", "Robertsonpet"));
        passengerList.add(new TicketDetailModel("8", "Rajesh", "24", "Robertsonpet"));
        passengerList.add(new TicketDetailModel("9", "Rajesh", "24", "Robertsonpet"));
        passengerList.add(new TicketDetailModel("10", "Rajesh", "24", "Robertsonpet"));
        passengerList.add(new TicketDetailModel("11", "Rajesh", "24", "Robertsonpet"));


        //sending data (passengerList) to the adapter
        TicketDetailAdapter ticketDetailAdapter = new TicketDetailAdapter(this, passengerList);

        //sending that ticketDetailAdapter object into recycler view
        recyclerView.setAdapter(ticketDetailAdapter);

        //creating object of linear layout manager so that we can pass the recycler view into it
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


    }
     public void onCreatYourAccount(View view) {
         startActivity(new Intent(ActivityTicketDetails.this, ActivityTrainSearch.class).putExtra("key", "1"));
     }

 }
