package com.prototype.carkila.OwnerAuth;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.prototype.carkila.Owner.LicenseDetails;
import com.prototype.carkila.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.TextUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class OwnerRegistration extends Activity {

    EditText EditFName, EditMName, EditLname, EditAddress, EditCity, EditGender, EditBirthdate, EditMobile, EditEmail, EditPassword, EditConfirmPassword;

    String getFName, getMName, getLName, getAddress, getCity, getGender, getBirthdate, getMobile, getEmail, getPassword, getConfirmPassword;

    Button BtnRegister;

    private FirebaseAuth mAuth;


    String DataParseUrl = "http://192.168.1.9/Carkila/insert_user.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_registration);

        EditFName = (EditText)findViewById(R.id.TFFname);
        EditMName = (EditText)findViewById(R.id.TFMname);
        EditLname = (EditText)findViewById(R.id.TFLname);
        EditAddress = (EditText)findViewById(R.id.TFAddress);
        EditCity = (EditText)findViewById(R.id.TFCity);
        EditGender = (EditText)findViewById(R.id.TFGender);
        EditBirthdate = (EditText)findViewById(R.id.TFBirthdate);
        EditMobile = (EditText)findViewById(R.id.TFMobile);
        EditEmail = (EditText)findViewById(R.id.TFEmail);
        EditPassword = (EditText)findViewById(R.id.TFPassword);
        EditConfirmPassword = (EditText) findViewById(R.id.TFConfirmPassword) ;
        BtnRegister = (Button)findViewById(R.id.BtnRegister);

        mAuth = FirebaseAuth.getInstance();


        BtnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                String Email = EditEmail.getText().toString();
                String Pass = EditPassword.getText().toString();

                if (android.text.TextUtils.isEmpty(Email)) { //if email is empty
                    Toast.makeText(getApplicationContext(), "Enter email!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) { //email must be a valid account!
                    Toast.makeText(getApplicationContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (android.text.TextUtils.isEmpty(Pass)) { //if pass is empty
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                } else if (Pass.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(Email,Pass)
                        .addOnCompleteListener(OwnerRegistration.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(!task.isSuccessful()){
                                    Toast.makeText(OwnerRegistration.this, "Authentication failed, email is already in use.", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    GetData();
                                    InsertUser(getFName, getMName, getLName, getAddress, getCity, getGender, getBirthdate, getMobile, getEmail, getPassword);
                                    startActivity(new Intent(OwnerRegistration.this, LicenseDetails.class));
                                }
                            }
                        });
            }
        });

    }

    public void GetData(){

        getFName = EditFName.getText().toString();
        getMName = EditMName.getText().toString();
        getLName = EditLname.getText().toString();
        getAddress = EditAddress.getText().toString();
        getCity = EditCity.getText().toString();
        getGender = EditGender.getText().toString();
        getBirthdate = EditBirthdate.getText().toString();
        getMobile = EditMobile.getText().toString();
        getEmail = EditEmail.getText().toString();
        getPassword = EditPassword.getText().toString();
        getConfirmPassword = EditConfirmPassword.getText().toString();


        if (TextUtils.isEmpty(getFName) || TextUtils.isEmpty(getMName) || TextUtils.isEmpty(getLName) || TextUtils.isEmpty(getBirthdate) || TextUtils.isEmpty(getAddress) || TextUtils.isEmpty(getCity) || TextUtils.isEmpty(getGender) || TextUtils.isEmpty(getMobile) || TextUtils.isEmpty(getEmail) || TextUtils.isEmpty(getPassword)) {
            Toast.makeText(getApplicationContext(), "Please fill up all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Check if password matches
        else if (!getPassword.equals(getConfirmPassword)) {
            Toast.makeText(getApplicationContext(), "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return;
        }

        //Check if email is valid
        else if (!Patterns.EMAIL_ADDRESS.matcher(getEmail).matches()) {
            Toast.makeText(getApplicationContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show();
            return;
        }

        //Password must be 6 or more characters
        if (getPassword.length() < 6) {
            Toast.makeText(getApplicationContext(), "Password too short.", Toast.LENGTH_SHORT).show();
            return;
        }
    }


    public void InsertUser(final String firstName, final String midName, final String lastName, final String address, final String city, final String sex, final String birthDate, final String mobileNumber, final String email, final String password){
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String QuickFname = firstName;
                String QuickMName = midName;
                String QuickLname = lastName;
                String QuickAddress = address;
                String QuickCity = city;
                String QuickGender = sex ;
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
                nameValuePairs.add(new BasicNameValuePair("sex", QuickGender));
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

                Toast.makeText(OwnerRegistration.this, "Registration Successful!", Toast.LENGTH_LONG).show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(firstName, midName, lastName, address, city, sex, birthDate, mobileNumber, email, password);
    }

}