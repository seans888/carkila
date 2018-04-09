package com.prototype.carkila.Owner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.prototype.carkila.R;

public class VehicleListing extends AppCompatActivity {

    Button btnAddVehicle;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_listing);

        //Add Vehicle Listing Button
        btnAddVehicle = findViewById(R.id.addVehicle_btn);
        btnAddVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VehicleListing.this, AddVehicle.class);
                startActivity(i);
            }
        });

        btnBack = findViewById(R.id.back_btn);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VehicleListing.this, OwnerHome.class);
                startActivity(i);
            }
        });
    }


}
