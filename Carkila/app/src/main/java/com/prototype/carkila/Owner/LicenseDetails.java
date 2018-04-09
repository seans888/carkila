package com.prototype.carkila.Owner;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class LicenseDetails extends AppCompatActivity {

    Button btnDone;
    EditText EditLicenseNum, EditLicenseExp;
    String getLicenseNum, getLicenseExp;
    String DataParseUrl = "http://192.168.1.9/Carkila/insert_vehicle_owner.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license_details);

        EditLicenseNum = (EditText)findViewById(R.id.TFLicenseNum);
        EditLicenseExp = (EditText)findViewById(R.id.TFLicenseExp);

        btnDone = findViewById(R.id.done_btn);
        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData();
                Insert(getLicenseNum, getLicenseExp);

                Intent i = new Intent(LicenseDetails.this, OwnerHome.class);
                startActivity(i);
            }
        });

    }

    public void GetData() {

        getLicenseNum = EditLicenseNum.getText().toString();
        getLicenseExp = EditLicenseExp.getText().toString();


        if (TextUtils.isEmpty(getLicenseNum) || TextUtils.isEmpty(getLicenseExp)) {
            Toast.makeText(getApplicationContext(), "Please fill up all fields.", Toast.LENGTH_SHORT).show();
        }
    }


    public void Insert(final String license_num, final String license_expiry) {
        class SendPostReqAsyncTask extends AsyncTask<String, Void, String> {
            @Override
            protected String doInBackground(String... params) {

                String QuickLicenseNum = license_num;
                String QuickLicenseExp = license_expiry;

                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();

                nameValuePairs.add(new BasicNameValuePair("license_num", QuickLicenseNum));
                nameValuePairs.add(new BasicNameValuePair("license_expiry", QuickLicenseExp));


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

                Toast.makeText(LicenseDetails.this, "Registration Successful!", Toast.LENGTH_LONG).show();

            }
        }
        SendPostReqAsyncTask sendPostReqAsyncTask = new SendPostReqAsyncTask();
        sendPostReqAsyncTask.execute(license_num, license_expiry);
    }
}
