package com.example.mytrainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.mytrainapp.ActivityTrainAppLogin.API_KEY;

public class ActivityPNRStatus extends AppCompatActivity {

    EditText pnr;
    Button search;
    String pnrNumber;
    TextView name, number, from, to, date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnr_status);
        //Casting edit text PNR
        pnr = findViewById(R.id.pnr_num);
        search = findViewById(R.id.search_button);

        //Casting name num from to and date from xml
        name = findViewById(R.id.train_name_id);
        number = findViewById(R.id.train_no_id);
        from = findViewById(R.id.trin_from_id);
        to = findViewById(R.id.train_to_id);
        date = findViewById(R.id.train_jourey_id);

        //Getting the pnr number and storing to pass into searchPNR class
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pnrNumber = pnr.getText().toString();
                //To call the backgroung thread i.e. SearchPNR class
                new SearchPNR().execute();
            }
        });

    }

    class SearchPNR extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            OkHttpClient client = new OkHttpClient();

            //to debug we have used Log.e==> its like printing this to know that these lines got executed
            Log.e("url", "https://indianrailapi.com/api/v2/PNRCheck/apikey/" + API_KEY + "/PNRNumber/" + pnrNumber + "/Route/1/");

            Request request = new Request.Builder()
                    .url("https://indianrailapi.com/api/v2/PNRCheck/apikey/" + API_KEY + "/PNRNumber/" + pnrNumber + "/Route/1/")
                    .build();

            try (Response response = client.newCall(request).execute()) {

                //write here for returning the data from back thread to reflect in the UI


                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "";
        }

        @Override
        // this s is having the data from the server
        protected void onPostExecute(String s) {

            Log.e("to check", s);
            super.onPostExecute(s);

            try {
                JSONObject jsonObject = new JSONObject(s);
                name.setText(jsonObject.getString("TrainName"));
                number.setText(jsonObject.getString("TrainNumber"));
                from.setText(jsonObject.getString("From"));
                to.setText(jsonObject.getString("To"));
                date.setText(jsonObject.getString("JourneyDate"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
