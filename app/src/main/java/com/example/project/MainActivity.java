package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button button1, button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        button1 = findViewById(R.id.startclass1);
        button2 = findViewById(R.id.startclass2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,SecondActivity2.class);
                startActivity(intent);

            }
        });


    }


    public void B18(View view) {

        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);


    }



    public void food(View view) {

        Intent intent = new Intent(MainActivity.this,FoodActivity.class);
        startActivity(intent);



    }

    public void A18(View view) {

        Intent intent = new Intent(MainActivity.this,SecondActivity2.class);
        startActivity(intent);

    }
}