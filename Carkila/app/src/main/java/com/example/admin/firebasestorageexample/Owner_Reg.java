package com.example.admin.firebasestorageexample;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Owner_Reg extends Activity {

    EditText editFname, editMname, EditLname, EditAddress, EditCity, EditSex, EditBirth, EditMobile, EditEmail, EditPassword;

    String GetFName, GetMName, GetLName, GetAddress, GetCity, GetSex, GetBirth, GetMobile, GetEmail, GetPassword;

    Button Confirm, Back;

    String DataParseUrl = "http://carkila.myapc.edu.ph/insert_renter_data.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter_reg);

        editFname = (EditText)findViewById(R.id.fname);
        editMname = (EditText)findViewById(R.id.mname);
        EditLname = (EditText)findViewById(R.id.lname);
        EditAddress = (EditText)findViewById(R.id.address);
        EditCity = (EditText)findViewById(R.id.city);
        EditSex = (EditText)findViewById(R.id.sex);
        EditBirth = (EditText)findViewById(R.id.birthdate);
        EditMobile = (EditText)findViewById(R.id.phone);
        EditEmail = (EditText)findViewById(R.id.email);
        EditPassword = (EditText)findViewById(R.id.password);

        Confirm = (Button)findViewById(R.id.confirm);
        Back = (Button)findViewById(R.id.back);

        Confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                GetDataFromEditText();

                SendDataToServer(GetFName, GetMName, GetLName, GetAddress, GetCity, GetSex, GetBirth, GetMobile, GetEmail, GetPassword);

                Intent i = new Intent(Owner_Reg.this, Owner_Home.class);
                startActivity(i);
                finish();
            }
        });
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (Owner_Reg.this, Owner_Login.class);
                startActivity(i);
                finish();
            }
        });
    }


    public void GetDataFromEditText(){

        GetFName = editFname.getText().toString();
        GetMName = editMname.getText().toString();
        GetLName = EditLname.getText().toString();
        GetAddress = EditAddress.getText().toString();
        GetCity = EditCity.getText().toString();
        GetSex = EditSex.getText().toString();
        GetBirth = EditBirth.getText().toString();
        GetMobile = EditMobile.getText().toString();
        GetEmail = EditEmail.getText().toString();
        GetPassword = EditPassword.getText().toString();

    }


    public void SendDataToServer(final String firstName, final String midName, final String lastName, final String address, final String city, final String sex, final String birthDate, final String mobileNumber, final String email, final String password){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String QuickFname = firstName;
                String QuickMName = midName;
                String QuickLname = lastName;
                String QuickAddress = address;
                String QuickCity = city;
                String QuickSex = sex ;
                String QuickBirthdate = birthDate;
                String QuickMobile = mobileNumber;
                String QuickEmail = email;
                String QuickPassword = password;


                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("firstName", QuickFname));
                nameValuePairs.add(new BasicNameValuePair("midName", QuickMName));
                nameValuePairs.add(new BasicNameValuePair("lastName", QuickLname));
                nameValuePairs.add(new BasicNameValuePair("address", QuickAddress));
                nameValuePairs.add(new BasicNameValuePair("city", QuickCity));
                nameValuePairs.add(new BasicNameValuePair("sex", QuickSex));
                nameValuePairs.add(new BasicNameValuePair("birthDate", QuickBirthdate));
                nameValuePairs.add(new BasicNameValuePair("mobileNumber", QuickMobile));
                nameValuePairs.add(new BasicNameValuePair("email", QuickEmail));
                nameValuePairs.add(new BasicNameValuePair("password", QuickPassword));


                try {
                    HttpClient httpClient = new DefaultHttpClient();

                    HttpPost httpPost = new HttpPost(DataParseUrl);

                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

                    HttpResponse response = httpClient.execute(httpPost);

                    HttpEntity entity = response.getEntity();


                } catch (ClientProtocolException e) {

                } catch (IOException e) {

                }
                return "Data Submitted Successfully";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);

                Toast.makeText(Owner_Reg.this, "Data Submitted Successfully", Toast.LENGTH_LONG).show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(firstName, midName, lastName, address, city, sex, birthDate, mobileNumber, email, password);
    }

}