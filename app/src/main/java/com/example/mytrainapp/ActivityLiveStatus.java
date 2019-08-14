package com.example.mytrainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.mytrainapp.ActivityTrainAppLogin.API_KEY;

public class ActivityLiveStatus extends AppCompatActivity {

    ImageView dialog;
    EditText num, date;
    Button checkStatus;
    String trainNum, dateString;
    TextView station, stationTime, eaDep,eaDepTime, eaDest,eaDestTime, delay;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_status);
        //Casting edit text Train No
        num=findViewById(R.id.train_no);
        checkStatus=findViewById(R.id.check_status_button);
        //Casting these from xml
        station = findViewById(R.id.last_reported_station);
        stationTime = findViewById(R.id.last_reported_station_time);
        eaDep = findViewById(R.id.expected_arrival_dep);
        eaDepTime = findViewById(R.id.expected_arrival_dep_time);
        eaDest = findViewById(R.id.expected_arrival_dest);
        eaDestTime = findViewById(R.id.expected_arrival_dest_time);
        delay = findViewById(R.id.delay);

         //clicking on button
        checkStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                trainNum = num.getText().toString();
                new LiveStatus().execute();
            }
        });

        //Calendar
        date = findViewById(R.id.date_field);
        dialog = findViewById(R.id.calendar_image);

        //instance of calendar
        Calendar calendar = Calendar.getInstance();

        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // to show the date picker dialog box
                DatePickerDialog datePickerDialog = new DatePickerDialog(ActivityLiveStatus.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        //sending selected date to the edit text
                        date.setText(i + "/" + (i1 + 1) + "/" + i2);
                        dateString = i + (i1+1) + i2 + "";
                        Toast.makeText(ActivityLiveStatus.this, i + "-" + (i1 + 1) + "-" + i2, Toast.LENGTH_SHORT).show();

                    }
                    //getting to know current date
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                datePickerDialog.show();
            }
        });

    }
    class LiveStatus extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            //to debug we have used Log.e==> its like printing this to know that these lines got executed
            Log.e("url", "https://indianrailapi.com/api/v2/livetrainstatus/apikey/" + API_KEY+ "/trainnumber/"+ trainNum+"/date/" + dateString+ "/");

            Request request = new Request.Builder()
                    .url("https://indianrailapi.com/api/v2/livetrainstatus/apikey/" + API_KEY+ "/trainnumber/"+ trainNum+"/date/" + dateString+ "/")
                    .build();
            //This below line is calling the API from the above URL
            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(s);
                String a = jsonObject.getJSONArray("TrainRoute").getJSONObject(jsonObject.getJSONArray("TrainRoute").length() -1).getString("DelayInDeparture");
            } catch (JSONException e) {
                e.printStackTrace();
            }


            Log.e("getData", s);
        }
    }

}


