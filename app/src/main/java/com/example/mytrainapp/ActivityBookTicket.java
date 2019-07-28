package com.example.mytrainapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ActivityBookTicket extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ticket);
// need to link with xml
        recyclerView = findViewById(R.id.book_ticket_recyclerview);

//hard coded values passing to the data adapter ===> pass this list in side data adapter
        ArrayList<TrainScheduleModel>scheduleList =new ArrayList<>();

        scheduleList.add(new TrainScheduleModel("1","Shathabdhi","1:30","2:01"));
        scheduleList.add(new TrainScheduleModel("1","Shathabdhi","1:30","2:01"));
        scheduleList.add(new TrainScheduleModel("1","Shathabdhi","1:30","2:01"));
        scheduleList.add(new TrainScheduleModel("1","Shathabdhi","1:30","2:01"));
        scheduleList.add(new TrainScheduleModel("1","Shathabdhi","1:30","2:01"));
        scheduleList.add(new TrainScheduleModel("1","Shathabdhi","1:30","2:01"));
        scheduleList.add(new TrainScheduleModel("1","Shathabdhi","1:30","2:01"));
        scheduleList.add(new TrainScheduleModel("1","Shathabdhi","1:30","2:01"));
        scheduleList.add(new TrainScheduleModel("1","Shathabdhi","1:30","2:01"));
        scheduleList.add(new TrainScheduleModel("1","Shathabdhi","1:30","2:01"));
        scheduleList.add(new TrainScheduleModel("1","Shathabdhi","1:30","2:01"));

        //Putting the data inside the adapter ==> passing the data or list ex: schedule list
        TrainScheduleAdapter trainScheduleAdapter =new TrainScheduleAdapter(this,scheduleList);
        //Connecting the recycler view with the adapter
        recyclerView.setAdapter(trainScheduleAdapter);

        //To set the layout ==> how we want the view of the data list

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this);

        //Now connect with the recycler view

        recyclerView.setLayoutManager(linearLayoutManager);
    }


}
