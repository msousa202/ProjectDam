package com.example.project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ExerciseHistoryActivity extends AppCompatActivity {

    String UUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_history);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        UUID = fAuth.getCurrentUser().getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        UUID = fAuth.getCurrentUser().getUid();

        LinearLayout linearLayout;
        linearLayout = findViewById(R.id.linearLayout);


        Task<QuerySnapshot> documentReference = db.collection("exercises").whereEqualTo("UUID", UUID).orderBy("date").get();
        documentReference.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());

                        TextView text1 = new TextView(ExerciseHistoryActivity.this);
                        text1.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayout.addView(text1);
                        TextView text2 = new TextView(ExerciseHistoryActivity.this);
                        text2.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        linearLayout.addView(text2);
                        text1.setText("Exercise Pack: " + document.getString("type") + " Date: " + document.getString("date"));
                        text2.setText("^Latitude: " + document.getString("latitude") + " Longitude: " + document.getString("longitude"));
                        Button button = new Button(ExerciseHistoryActivity.this);
                        button.setText("Check on Map");
                        button.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        button.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(ExerciseHistoryActivity.this,MainActivity.class);
                                startActivity(intent);
                            }
                        });
                        if (linearLayout != null){
                            linearLayout.addView(button);
                        }
                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }

}