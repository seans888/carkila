import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.HashMap;

public class UserReg extends AppCompatActivity {

   Button Confirm, Back;
    EditText fname, mname, lname, address, city, sex, birth, mobile, email, password;
    String fnameholder, mnameholder, lnameholder, addressholder, cityholder, sexholder, birth_holder, mobileholder, emailholder, passwordholder;
    String finalResult ;
    String HttpURL = "http://carkila.myapc.edu.ph/InsertUser.php";
    Boolean CheckEditText ;
    ProgressDialog progressDialog;
    HashMap<String,String> hashMap = new HashMap<>();
    HttpParse httpParse = new HttpParse();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_reg);

        //Assign Id'S
        fname = (EditText)findViewById(R.id.fname);
        mname = (EditText)findViewById(R.id.mname);
        lname = (EditText)findViewById(R.id.lname);
        address = (EditText)findViewById(R.id.address);
        city = (EditText)findViewById(R.id.city);
        sex = (EditText)findViewById(R.id.sex);
        birth = (EditText)findViewById(R.id.birthdate);
        mobile = (EditText)findViewById(R.id.phone);
        email = (EditText)findViewById(R.id.email);
        password = (EditText)findViewById(R.id.password);

        Confirm = (Button)findViewById(R.id.confirm);
        Back = (Button)findViewById(R.id.back);

        //Adding Click Listener on button.
        Confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking whether EditText is Empty or Not
                CheckEditTextIsEmptyOrNot();

                if(CheckEditText){

                    // If EditText is not empty and CheckEditText = True then this block will execute.

                    UserRegisterFunction(fnameholder, mnameholder, lnameholder, addressholder, cityholder, sexholder, birth_holder, mobileholder, emailholder, passwordholder);

                }
                else {

                    // If EditText is empty then this block will execute .
                    Toast.makeText(MainActivity.this, "Please fill all form fields.", Toast.LENGTH_LONG).show();

                }


            }
        });

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,UserLoginActivity.class);
                startActivity(intent);

            }
        });

    }

    public void CheckEditTextIsEmptyOrNot(){

        fnameholder = fname.getText().toString();
        mnameholder = mname.getText().toString();
        lnameholder = lname.getText().toString();
        addressholder = address.getText().toString();
        cityholder = city.getText().toString();
        sexholder = sex.getText().toString();
        birth_holder = birth.getText().toString();
        mobileholder = phone.getText().toString();
        emailholder = email.getText().toString();
        passwordholder = password.getText().toString();

        if(TextUtils.isEmpty(fnameholder) || TextUtils.isEmpty(mnameholder) || TextUtils.isEmpty(lnameholder) || TextUtils.isEmpty(addressholder) || TextUtils.isEmpty(cityholder)) ||
            TextUtils.isEmpty(sexholder) || TextUtils.isEmpty(birth_holder) || TextUtils.isEmpty(mobileholder) || TextUtils.isEmpty(emailholder) || TextUtils.isEmpty(passwordholder)
        {

            CheckEditText = false;

        }
        else {

            CheckEditText = true ;
        }

    }

    public void UserRegisterFunction(final String fname, final String mname, final String lname, final String address, final String city, final String sex, final String birthdate, final String mobile, final String email, final String password){

        class UserRegisterFunctionClass extends AsyncTask<String,Void,String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                progressDialog = ProgressDialog.show(MainActivity.this,"Loading Data",null,true,true);
            }

            @Override
            protected void onPostExecute(String httpResponseMsg) {

                super.onPostExecute(httpResponseMsg);

                progressDialog.dismiss();

                    Toast.makeText(MainActivity.this,httpResponseMsg.toString(), Toast.LENGTH_LONG).show();

            }

            @Override
            protected String doInBackground(String... params) {

                hashMap.put("fname",params[0]);

                hashMap.put("mname",params[1]);

                hashMap.put("lname",params[2]);

                hashMap.put("address",params[3]);

                hashMap.put("city",params[4]);

                hashMap.put("sex",params[5]);

                hashMap.put("mobile",params[6]);

                hashMap.put("email",params[7]);

                



                finalResult = httpParse.postRequest(hashMap, HttpURL);

                return finalResult;
            }
        }

        UserRegisterFunctionClass userRegisterFunctionClass = new UserRegisterFunctionClass();

        userRegisterFunctionClass.execute(fname, mname, lname, address, city, sex, birth, mobile, email, password);
    }

}