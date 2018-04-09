package com.prototype.carkila.Vehicle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.prototype.carkila.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DisplayVehicle extends AppCompatActivity {

    //this is the JSON Data URL
    //make sure you are using the correct ip else it will not work
    private static final String URL_VEHICLES = "http://192.168.1.9/Carkila/display_vehicle.php";

    //a list to store all the products
    List<Vehicle> VehicleList;

    //the recyclerview
    RecyclerView recyclerView;


    public void onButtonVehicle(View v) {
        if (v.getId() == R.id.recylcerView) {
            Intent i = new Intent(DisplayVehicle.this, VehicleInfo.class);

            startActivity(i);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_vehicle);

        //getting the recyclerview from xml
        recyclerView = findViewById(R.id.recylcerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        //initializing the productlist
        VehicleList = new ArrayList<>();

        //this method will fetch and parse json
        //to display it in recyclerview
        loadVehicles();
    }

    private void loadVehicles() {

        /*
        * Creating a String Request
        * The request type is GET defined by first parameter
        * The URL is defined in the second parameter
        * Then we have a Response Listener and a Error Listener
        * In response listener we will get the JSON response as a String
        * */
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_VEHICLES,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            //converting the string to json array object
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for (int i = 0; i < array.length(); i++) {

                                //getting product object from json array
                                JSONObject vehicle = array.getJSONObject(i);

                                //adding the vehicle to list
                                VehicleList.add(new Vehicle(
                                        vehicle.getInt("id"),
                                        vehicle.getString("make"),
                                        vehicle.getString("model"),
                                        vehicle.getString("year"),
                                        vehicle.getString("seatingCapacity"),
                                        vehicle.getString("rentalRate"),
                                        vehicle.getString("image")
                                ));
                            }

                            //creating adapter object and setting it to recyclerview
                            VehicleAdapter adapter = new VehicleAdapter(DisplayVehicle.this, VehicleList);
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DisplayVehicle.this, error.getMessage(), Toast.LENGTH_LONG).show();

                    }
                });

        //adding our stringrequest to queue
        Volley.newRequestQueue(this).add(stringRequest);
    }
}