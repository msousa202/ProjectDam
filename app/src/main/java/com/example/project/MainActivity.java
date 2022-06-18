package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    Button button1, button2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.startclass1);
        button2 = findViewById(R.id.startclass2);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        String UUID = fAuth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DateFormat df = new SimpleDateFormat("yyyy,MM,dd");
        String date = df.format(Calendar.getInstance().getTime());

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);

                Map<String, Object> exercises = new HashMap<>();

                exercises.put("type", "under 18");
                exercises.put("date", date);
                exercises.put("UUID", UUID);

                // Add a new document with a generated ID
                db.collection("exercises")
                        .add(exercises)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(MainActivity.this,"Exercise Pack Registered and Started Succefully. Good Luck!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,SecondActivity2.class);
                startActivity(intent);

                Map<String, Object> exercises = new HashMap<>();

                exercises.put("type", "Over 18");
                exercises.put("date", date);
                exercises.put("UUID", UUID);

                // Add a new document with a generated ID
                db.collection("exercises")
                        .add(exercises)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(MainActivity.this,"Exercise Pack Registered and Started Succefully. Good Luck!", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

            }
        });


    }


    public void B18(View view) {

        Intent intent = new Intent(MainActivity.this,SecondActivity.class);
        startActivity(intent);


    }


    public void food(View view) {

        Intent intent = new Intent(MainActivity.this, DataActivity.class);
        startActivity(intent);

    }

    public void A18(View view) {

        Intent intent = new Intent(MainActivity.this,SecondActivity2.class);
        startActivity(intent);

    }




}