package com.example.admin.firebasestorageexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Owner_Vehicles extends AppCompatActivity {

    private Button add, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_vehicles);


        add = (Button) findViewById(R.id.btnAdd);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Owner_Vehicles.this, Owner_AddVehicle.class);
                startActivity(i);
                finish();
            }
        });

        back = (Button) findViewById(R.id.btn_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(Owner_Vehicles.this, Owner_Home.class);
                startActivity(j);
                finish();
            }
        });
    }
}
