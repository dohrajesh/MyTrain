package com.example.mytrainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class ActivityTrainAppLogin extends AppCompatActivity {


    Button loginButton;
    public static String mobileNumberValue = "";

    //API key for comunicating to the server making it as static cause we are using every where in our network calls
    public static String API_KEY = "975c8ae5a256ffeda9845796b549bd26";
    EditText mobileNumber;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_app_login);

        //To make the box radius for facbook and Gmail in activity train app login page
//        by creating ID linear we connect
        LinearLayout linearLayout = findViewById(R.id.linear);
        linearLayout.setClipToOutline(true);

        loginButton = findViewById(R.id.login_button);
        mobileNumber = findViewById(R.id.mobile_edit_text);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Sending OTP");

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mobileNumber.getText().toString().length() == 10) {
                    progressDialog.show();


                    PhoneAuthProvider.getInstance().verifyPhoneNumber(
                            // phone Number
                            "+91" + mobileNumber.getText().toString(),
                            // time
                            15,
                            TimeUnit.SECONDS,
                            ActivityTrainAppLogin.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                @Override
                                public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

                                    //We are dismissing the progress bar when we are going to next screen
                                    progressDialog.dismiss();

                                    mobileNumberValue = mobileNumber.getText().toString();
                                    Intent intent = new Intent(ActivityTrainAppLogin.this, NavigationDrawerActivity.class);
                                    startActivity(intent);
                                }

                                @Override
                                public void onVerificationFailed(FirebaseException e) {

                                    Log.e("error", e.getLocalizedMessage());
                                }

                                @Override
                                public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                    Log.e("code", s);


                                    Runnable runnable = new Runnable() {
                                        @Override
                                        public void run() {
                                            progressDialog.dismiss();
                                          //  Toast.makeText(ActivityTrainAppLogin.this, "Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    };
                                    // perfor a task after a particular time
                                    new Handler().postDelayed(runnable, 25000);
                                }
                            }
                    );
                } else {
                    Toast.makeText(ActivityTrainAppLogin.this, "Mobile Number should have 10 digits", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

//on click of create now should go to registration page
    public void onCreatYourAccount(View view) {
        startActivity(new Intent(ActivityTrainAppLogin.this, NavigationDrawerActivity.class).putExtra("key", "1"));
    }
}

