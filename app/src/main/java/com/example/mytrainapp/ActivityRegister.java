package com.example.mytrainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityRegister extends AppCompatActivity {

    Button register;
    EditText firstName, lastName, email,phoneNumber,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//Casting of xml view to java
        register = findViewById(R.id.register);
        firstName = findViewById(R.id.first_name_edit_text);
        lastName = findViewById(R.id.last_name_edit_text);
        email = findViewById(R.id.email_edit_text);
        phoneNumber = findViewById(R.id.phone_no_edit_text);
        password = findViewById(R.id.password_edit_text);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //setting the values in user detail object
                UserDetails userDetails = new UserDetails();
                userDetails.setFirstname(firstName.getText().toString());
                userDetails.setLastname(lastName.getText().toString());
                userDetails.setEmail(email.getText().toString().replace(".",","));
                userDetails.setPhonenumber(phoneNumber.getText().toString());
                userDetails.setPassword(password.getText().toString());

                //setting the values in fire base or throwing the  userDetail value in fire base
                FirebaseDatabase.getInstance().getReference().child("users").child(phoneNumber.getText().toString()).setValue(userDetails);


            }
        });
    }

}
