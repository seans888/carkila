package com.prototype.carkila.Owner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.prototype.carkila.Dashboard;
import com.prototype.carkila.Initial;
import com.prototype.carkila.OwnerAuth.OwnerLogin;
import com.prototype.carkila.R;
import com.prototype.carkila.Renter.TripDetails;
import com.prototype.carkila.Vehicle.DisplayVehicle;

public class OwnerHome extends AppCompatActivity {

    private CardView btnDashboard, btnVehicle, btnNotifications, btnMessages, btnProfile, btnSignout;
    private ProgressBar progressBar;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);

        auth = FirebaseAuth.getInstance();


        //Dashboard Button
        btnDashboard = findViewById(R.id.dashboard_btn);
        btnDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OwnerHome.this, DisplayVehicle.class);
                startActivity(i);
            }
        });

        //Vehicle Listing Button
        btnVehicle = findViewById(R.id.vehicle_btn);
        btnVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OwnerHome.this, VehicleListing.class);
                startActivity(i);
            }
        });

        //Notification Button
        btnNotifications = findViewById(R.id.notifications_btn);
        btnNotifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OwnerHome.this, Dashboard.class);
                startActivity(i);
            }
        });

        //Messages Button
        btnMessages = findViewById(R.id.messages_btn);
        btnMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OwnerHome.this, TripDetails.class);
                startActivity(i);
            }
        });

        //Profile Button
        btnProfile = findViewById(R.id.profile_btn);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(OwnerHome.this, Dashboard.class);
                startActivity(i);
            }
        });

        //Sign out Button
        btnSignout = findViewById(R.id.signout_btn);
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent k = new Intent(OwnerHome.this, Initial.class);
                startActivity(k);
                finish();
            }
        });
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        if (progressBar != null) {
            progressBar.setVisibility(View.GONE);
        }
        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

    }


    // this listener will be called when there is change in firebase user session
    FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            FirebaseUser user = firebaseAuth.getCurrentUser();
            if (user == null) {
                // user auth state is changed - user is null
                // launch login activity
                startActivity(new Intent(OwnerHome.this, OwnerLogin.class));
                finish();
            } else {


            }
        }


    };


        //sign out method
    public void signOut() {
        auth.signOut();

    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onStart() {
        super.onStart();
        auth.addAuthStateListener(authListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }

    }
}
