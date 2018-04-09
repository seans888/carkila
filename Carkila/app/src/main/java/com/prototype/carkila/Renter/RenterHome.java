package com.prototype.carkila.Renter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;

import com.prototype.carkila.Dashboard;
import com.prototype.carkila.R;
import com.prototype.carkila.Vehicle.DisplayVehicle;

public class RenterHome extends AppCompatActivity {

    private CardView btnBrowse, btnNotifications, btnMessages, btnTrips, btnProfile, btnSignout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter_home);

        //Dashboard Button
        btnBrowse = findViewById(R.id.browse_btn);
        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RenterHome.this, DisplayVehicle.class);
                startActivity(i);
            }
        });

        //Notification Button
        btnNotifications = findViewById(R.id.notificationsR_btn);
        btnNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RenterHome.this, Dashboard.class);
                startActivity(i);
            }
        });

        //Messages Button
        btnMessages = findViewById(R.id.messagesR_btn);
        btnMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RenterHome.this, TripDetails.class);
                startActivity(i);
            }
        });

        //Vehicle Listing Button
        btnTrips = findViewById(R.id.trips_btn);
        btnTrips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RenterHome.this, Dashboard.class);
                startActivity(i);
            }
        });

        //Profile Button
        btnProfile = findViewById(R.id.profileR_btn);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RenterHome.this, Dashboard.class);
                startActivity(i);
            }
        });

        //Sign out Button
        btnSignout = findViewById(R.id.signoutR_btn);
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RenterHome.this, Dashboard.class);
                startActivity(i);
            }
        });

    }
}
