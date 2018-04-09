package com.prototype.carkila.RenterAuth;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.prototype.carkila.Owner.OwnerHome;
import com.prototype.carkila.R;
import com.prototype.carkila.Renter.RenterHome;

public class RenterLogin extends AppCompatActivity {

    EditText Email, Password;
    Button btnLogin, signupBtn;
    private FirebaseAuth auth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_renter_login);

        auth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);

        if (auth.getCurrentUser() != null) {
            startActivity(new Intent(RenterLogin.this, RenterHome.class));
            finish();
        }

        signupBtn = findViewById(R.id.signupRenter_btn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RenterLogin.this, RenterRegistration.class);
                startActivity(i);
            }
        });


        Email = (EditText) findViewById(R.id.TFLoginEmail);
        Password = (EditText) findViewById(R.id.TFLoginPassword);

        btnLogin = findViewById(R.id.login_btn);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String email = Email.getText().toString();
                final String password = Password.getText().toString();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter an email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Enter password.", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RenterLogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(RenterLogin.this, OwnerHome.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Email or password isn't correct.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

}