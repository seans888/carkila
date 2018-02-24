package com.example.admin.firebasestorageexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Listing extends AppCompatActivity {

    private Button btnListing, btn_back;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listing);

        btnListing = (Button) findViewById(R.id.btnAdd);
        btnListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Listing.this, AddListing.class);
                startActivity(i);
                finish();

            }
        });

        btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(Listing.this, Home.class);
                startActivity(j);
                finish();
            }
        });
    }
}
