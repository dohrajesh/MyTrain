package com.example.mytrainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.mytrainapp.ActivityTrainAppLogin.API_KEY;

public class ActivityTrainSearch extends AppCompatActivity {


    EditText date, trainNum, from, to, asOn ;
    ImageView dialog;
    Button trainSearch;
    String tNum, fromString, tooString, dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_search);



        //Casting name num from to and date from xml
        trainNum = findViewById(R.id.train_num);
        from = findViewById(R.id.train_from);
        to = findViewById(R.id.train_to);
        asOn = findViewById(R.id.date_selector);


        //similerly for button but dont do for dateString bcz we have done for this in date picker (see below code)
        trainSearch = findViewById(R.id.search_button);
        trainSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // getting text from xml using id trainNum and storing in tNum
                tNum=trainNum.getText().toString();
                fromString=from.getText().toString();
                tooString=to.getText().toString();

                //calling or executing the class
                new TrainSearch().execute();
            }
        });
        //Calendar
        date = findViewById(R.id.date_selector);
        dialog = findViewById(R.id.date_dialog);

        //instance of calendar
        Calendar calendar = Calendar.getInstance();

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to show the date picker dialog box
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityTrainSearch.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        //sending selected date to the edit text
                        date.setText(i2 + "/" + (i1 + 1) + "/" + i);
                        dateString = i + (i1+1) + i2 + "";
                        Toast.makeText(ActivityTrainSearch.this, i2 + "-" + (i1 + 1) + "-" + i, Toast.LENGTH_SHORT).show();

                    }
                    //getting to know current date
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

    }

    public void bookTicket(View view) {
        startActivity(new Intent(ActivityTrainSearch.this, ActivityBookTicket.class).putExtra("key", "1"));

    }


    class TrainSearch extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {

            OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://indianrailapi.com/api/v2/SeatAvailability/apikey/"+API_KEY+"/TrainNumber/"+tNum+"/From/"+fromString+"/To/"+tooString+"/Date/"+ dateString +"/Quota/GN/Class/SL")
                        .build();

                try (Response response = client.newCall(request).execute()) {
                    return response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Log.e("searchData", s);
        }
    }
}
