package com.example.admin.firebasestorageexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;


public class SplashScreen extends AppCompatActivity {

  //  AVLoadingIndicatorView avl;

  private static final long SPLASH_TIME = 5100;

  //  GradienTextView gtv_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


        //gtv_app = (GradienTextView)findViewById(R.id.gradientApp);


    //    gtv_app.start(Orientation.INNER_TO_OUTER,3000);

    //    avl = (AVLoadingIndicatorView)findViewById(R.id.avi);

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent().setClass(SplashScreen.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        };

        startAnim();
        Timer timer = new Timer();
        timer.schedule(task,SPLASH_TIME);

    }

    void startAnim(){
     //   avl.show();

    }

    void stopAnim(){
   //     avl.hide();

    }
}

