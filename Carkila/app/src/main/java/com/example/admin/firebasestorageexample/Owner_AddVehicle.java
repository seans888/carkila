package com.example.admin.firebasestorageexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Owner_AddVehicle extends AppCompatActivity {

    private Button next, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_add_vehicle);

        next = (Button) findViewById(R.id.btnNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Owner_AddVehicle.this, Owner_UploadImage.class);
                startActivity(i);
                finish();
            }
        });

        cancel = (Button) findViewById(R.id.back);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(Owner_AddVehicle.this, Owner_Vehicles.class);
                startActivity(j);
                finish();
            }
        });
    }
}
