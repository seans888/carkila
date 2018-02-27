package com.example.admin.firebasestorageexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Initial extends AppCompatActivity {

    private Button owner, renter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);

        owner = (Button)findViewById(R.id.btnOwner);
        owner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Initial.this, Owner_Login.class);
               startActivity(i);
                finish();
            }
        });


    }
}
