package com.prototype.carkila.Renter;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.prototype.carkila.Owner.OwnerHome;
import com.prototype.carkila.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.TextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TripDetails extends AppCompatActivity {

    Button btnSendRequest;
    EditText EditDestination, EditPickup, EditSDate, EditEDate, EditSTime, EditETime, EditPassenger;
    String getDestination, getPickup, getSDate, getEDate, getSTime, getETime, getPassenger;

    String DataParseUrl = "http://192.168.1.9/Carkila/send_request.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_details);

        EditDestination = (EditText)findViewById(R.id.TFDestination);
        EditPickup = (EditText)findViewById(R.id.TFPickup);
        EditSDate = (EditText)findViewById(R.id.TFSDate);
        EditEDate = (EditText)findViewById(R.id.TFEDate);
        EditSTime = (EditText)findViewById(R.id.TFSTime);
        EditETime = (EditText)findViewById(R.id.TFETime);
        EditPassenger = (EditText)findViewById(R.id.TFPassenger);

        btnSendRequest = findViewById(R.id.request_btn);
        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData();
                Insert(getDestination, getPickup, getSDate, getEDate, getSTime, getETime, getPassenger);

                Intent i = new Intent(TripDetails.this, OwnerHome.class);
                startActivity(i);
            }
        });

    }

    public void GetData() {

        getDestination = EditDestination.getText().toString();
        getPickup = EditPickup.getText().toString();
        getSDate = EditSDate.getText().toString();
        getEDate = EditEDate.getText().toString();
        getSTime = EditSTime.getText().toString();
        getETime = EditETime.getText().toString();
        getPassenger = EditPassenger.getText().toString();



        if (TextUtils.isEmpty(getDestination) || TextUtils.isEmpty(getPickup) || TextUtils.isEmpty(getSDate) || TextUtils.isEmpty(getEDate) || TextUtils.isEmpty(getSTime) || TextUtils.isEmpty(getETime) || TextUtils.isEmpty(getPassenger)) {
            Toast.makeText(getApplicationContext(), "Please fill up all fields.", Toast.LENGTH_SHORT).show();
        }
    }


    public void Insert(final String destination, final String pickupLocation, final String startDate, final String endDate, final String startTime, final String endTime, final String passengerNum) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String QuickDestination = destination;
                String QuickPickup = pickupLocation;
                String QuickSDate = startDate;
                String QuickEDate = endDate;
                String QuickSTime = startTime;
                String QuickETime = endTime;
                String QuickPassenger = passengerNum;



                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("destination", QuickDestination));
                nameValuePairs.add(new BasicNameValuePair("pickupLocation", QuickPickup));
                nameValuePairs.add(new BasicNameValuePair("startDate", QuickSDate));
                nameValuePairs.add(new BasicNameValuePair("endDate", QuickEDate));
                nameValuePairs.add(new BasicNameValuePair("startTime", QuickSTime));
                nameValuePairs.add(new BasicNameValuePair("endTime", QuickETime));
                nameValuePairs.add(new BasicNameValuePair("passengerNum", QuickPassenger));


                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(DataParseUrl);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Submitted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(TripDetails.this, "Rent request sent!", Toast.LENGTH_LONG).show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(destination, pickupLocation, startDate, endDate, startTime, endTime, passengerNum);
    }
}
