package com.example.mytrainapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityProfileView extends AppCompatActivity {

    //declaring variables
    TextView email, password, name, phoneNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
//variables in xml connected to java or casting
        email=findViewById(R.id.email_in_profile);
        password=findViewById(R.id.password_in_profile);
        name=findViewById(R.id.name_in_profile);
        phoneNum=findViewById(R.id.phone_in_profile);
//from fire base getting the value according to the mobile number  , that we stored on firebase from registerscreen
        FirebaseDatabase.getInstance().getReference().child("users").child(ActivityTrainAppLogin.mobileNumberValue).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                //mapping the data snapshot to the UserDetails
                UserDetails userDetails = dataSnapshot.getValue(UserDetails.class);

                //Setting the dynamic values
                email.setText(userDetails.getEmail().replace(",", "."));
                password.setText(userDetails.getPassword());
                name.setText(userDetails.getFirstname());
                phoneNum.setText(userDetails.getPhonenumber());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
