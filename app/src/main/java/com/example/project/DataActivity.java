package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Member;

public class DataActivity extends LoginActivity {


    EditText etAge;
    EditText etHeight;
    EditText etWeight;

    Button btnInsertAge;
    Button btnInsertHeight;
    Button btnInsertWeight;

    DatabaseReference graphdata;

    DataInfo Member;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        etAge = findViewById(R.id.age);
        etHeight = findViewById(R.id.height);
        etWeight = findViewById(R.id.weight);
        btnInsertAge = findViewById(R.id.InsertAge);
        btnInsertHeight = findViewById(R.id.InsertHeight);
        btnInsertWeight = findViewById(R.id.InsertWeight);





        graphdata = FirebaseDatabase.getInstance().getReference().child("Member");

        btnInsertAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int age = Integer.parseInt(etAge.getText().toString().trim());
                int height = Integer.parseInt(etHeight.getText().toString().trim());
                int weight = Integer.parseInt(etWeight.getText().toString().trim());

                graphdata.push().setValue();







                
            }
        });



    }

    private void insertUserAge() {

        String age = etAge.getText().toString();
        String height = etHeight.getText().toString();
        String weight = etWeight.getText().toString();


    }


    public void GoToGraph(View view) {

        Intent intent = new Intent(DataActivity.this,GraphsActivity.class);
        startActivity(intent);

    }


    public void ImportAge(View view) {
    }

    public void ImportWeight(View view) {
    }

    public void ImportHeight(View view) {
    }
}