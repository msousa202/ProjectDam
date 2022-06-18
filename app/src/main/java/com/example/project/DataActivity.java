package com.example.project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DataActivity extends LoginActivity {


    EditText etAge,etHeight,etWeight;

    Button btnInsertData;

    String UUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);


        etAge = findViewById(R.id.age);
        etHeight = findViewById(R.id.height);
        etWeight = findViewById(R.id.weight);
        btnInsertData = findViewById(R.id.InsertData);
        DateFormat df = new SimpleDateFormat("yyyy,MM,dd");
        String date = df.format(Calendar.getInstance().getTime());
        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        UUID = fAuth.getCurrentUser().getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> userData = new HashMap<>();

        btnInsertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String age = etAge.getText().toString();
                String height = etHeight.getText().toString();
                String weight = etWeight.getText().toString();

                userData.put("age", age);
                userData.put("weight", weight);
                userData.put("height", height);
                userData.put("date", date);
                userData.put("UUID", UUID);

                // Add a new document with a generated ID
                db.collection("userData")
                        .add(userData)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(DataActivity.this,"Added Data Succefully", Toast.LENGTH_SHORT).show();
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
    public void GoToHistory(View view) {

        Intent intent = new Intent(DataActivity.this,GraphsActivity.class);
        startActivity(intent);

    }

    public void GoToExerciseHistory(View view) {

        Intent intent = new Intent(DataActivity.this,ExerciseHistoryActivity.class);
        startActivity(intent);

    }

   /*
    private void insertUserAge() {

        String age = etAge.getText().toString();
        String height = etHeight.getText().toString();
        String weight = etWeight.getText().toString();


    }

    public void ImportAge(View view) {
    }

    public void ImportWeight(View view) {
    }

    public void ImportHeight(View view) {
    }*/
}