package com.prototype.carkila.Vehicle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.prototype.carkila.R;
import com.prototype.carkila.Renter.TripDetails;

public class Vehicle_details extends AppCompatActivity {


    TextView Make, Model, Year, Capacity;
    Button btnSendRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_details);



        String image = getIntent().getStringExtra("CarImage");
        String make = getIntent().getStringExtra("CarMake");
        String model = getIntent().getStringExtra("CarModel");
        String year = getIntent().getStringExtra("CarYear");
        String capacity = getIntent().getStringExtra("CarCapacity");


        ImageView img = findViewById(R.id.Image);
        Glide.with(this)
                .load(image)
                .into(img);

        Make =(TextView) findViewById(R.id.Make);
        Make.setText(make);

        Model =(TextView)findViewById(R.id.Model);
        Model.setText(model);

        Year = (TextView)findViewById(R.id.Year);
        Year.setText(year);

        Capacity =(TextView)findViewById(R.id.Capacity);
        Capacity.setText(capacity);

        btnSendRequest = findViewById(R.id.sendrequest_btn);
        btnSendRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Vehicle_details.this, TripDetails.class);
            }
        });
    }
}
