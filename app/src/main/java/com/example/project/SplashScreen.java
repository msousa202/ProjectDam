package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;



public class SplashScreen extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView imageView = findViewById(R.id.appsplash);

        TextView textView = findViewById(R.id.appname);


       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {

               startActivity(new Intent(getApplicationContext(),MainActivity.class));
               finish();

           }
       },3500);
    }


}