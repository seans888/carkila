package com.prototype.carkila.Owner;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class AddVehicle extends AppCompatActivity {

    Button btnDone;
    EditText EditMake, EditModel, EditYear, EditColor, EditCapacity, EditEngineNum, EditChassis, EditPlateNumber, EditRentalRate, EditImage;
    String getMake, getModel, getYear, getColor, getCapacity, getEngineNum, getChassis, getPlateNum, getRentalRate, getImage;

    String DataParseUrl = "http://192.168.1.9/Carkila/add_vehicle.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        EditMake = (EditText)findViewById(R.id.TFMake);
        EditModel = (EditText)findViewById(R.id.TFModel);
        EditYear = (EditText)findViewById(R.id.TFYear);
        EditColor = (EditText)findViewById(R.id.TFColor);
        EditCapacity = (EditText)findViewById(R.id.TFCapacity);
        EditEngineNum = (EditText)findViewById(R.id.TFEngineNum);
        EditChassis = (EditText)findViewById(R.id.TFChassis);
        EditPlateNumber = (EditText)findViewById(R.id.TFPlateNum);
        EditRentalRate = (EditText)findViewById(R.id.TFRentalRate);
        EditImage = (EditText)findViewById(R.id.TFImage);


        btnDone = findViewById(R.id.done_btn);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData();
                Insert(getMake, getModel, getYear, getColor, getCapacity, getEngineNum, getChassis, getPlateNum, getRentalRate, getImage);

                Intent i = new Intent(AddVehicle.this, OwnerHome.class);
                startActivity(i);
            }
        });

    }

    public void GetData() {

        getMake = EditMake.getText().toString();
        getModel = EditModel.getText().toString();
        getYear = EditYear.getText().toString();
        getColor = EditColor.getText().toString();
        getCapacity = EditCapacity.getText().toString();
        getEngineNum = EditEngineNum.getText().toString();
        getChassis = EditChassis.getText().toString();
        getPlateNum = EditPlateNumber.getText().toString();
        getRentalRate = EditRentalRate.getText().toString();
        getImage = EditImage.getText().toString();


        if (TextUtils.isEmpty(getMake) || TextUtils.isEmpty(getModel) || TextUtils.isEmpty(getYear) || TextUtils.isEmpty(getColor) || TextUtils.isEmpty(getCapacity) || TextUtils.isEmpty(getEngineNum) || TextUtils.isEmpty(getChassis) || TextUtils.isEmpty(getPlateNum) || TextUtils.isEmpty(getRentalRate) || TextUtils.isEmpty(getImage)) {
            Toast.makeText(getApplicationContext(), "Please fill up all fields.", Toast.LENGTH_SHORT).show();
        }
    }


    public void Insert(final String make, final String model, final String year, final String color, final String seatingCapacity, final String engineNumber, final String chassisNumber, final String plateNumber, final String rentalRate, final String image) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String QuickMake = make;
                String QuickModel = model;
                String QuickYear = year;
                String QuickColor = color;
                String QuickCapacity = seatingCapacity;
                String QuickEngineNum = engineNumber;
                String QuickChassis = chassisNumber;
                String QuickPlateNum = plateNumber;
                String QuickRentalRate = rentalRate;
                String QuickImage = image;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("make", QuickMake));
                nameValuePairs.add(new BasicNameValuePair("model", QuickModel));
                nameValuePairs.add(new BasicNameValuePair("year", QuickYear));
                nameValuePairs.add(new BasicNameValuePair("color", QuickColor));
                nameValuePairs.add(new BasicNameValuePair("seatingCapacity", QuickCapacity));
                nameValuePairs.add(new BasicNameValuePair("engineNumber", QuickEngineNum));
                nameValuePairs.add(new BasicNameValuePair("chassisNumber", QuickChassis));
                nameValuePairs.add(new BasicNameValuePair("plateNumber", QuickPlateNum));
                nameValuePairs.add(new BasicNameValuePair("rentalRate", QuickRentalRate));
                nameValuePairs.add(new BasicNameValuePair("image", QuickImage));


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

                Toast.makeText(AddVehicle.this, "Vehicle added!", Toast.LENGTH_LONG).show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(make, model, year, color, seatingCapacity, engineNumber, chassisNumber, plateNumber, rentalRate, image);
    }
}
