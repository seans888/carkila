package com.prototype.carkila;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.prototype.carkila.OwnerAuth.OwnerLogin;
import com.prototype.carkila.RenterAuth.RenterLogin;

public class Initial extends AppCompatActivity {

    Button btnDriver, btnRenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        btnDriver = findViewById(R.id.driver_btn);
        btnDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Initial.this, OwnerLogin.class);
                startActivity(i);
            }
        });

        btnRenter = findViewById(R.id.renter_btn);
        btnRenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Initial.this, RenterLogin.class);
                startActivity(i);
            }
        });
    }
}
