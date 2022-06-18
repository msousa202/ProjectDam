package com.example.project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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
    ArrayAdapter<String> adapter2;
    ArrayList<String> arrayList2;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_history);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        UUID = fAuth.getCurrentUser().getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        UUID = fAuth.getCurrentUser().getUid();

        list = (ListView) findViewById(R.id.listViewExerciseHistory);
        arrayList2 = new ArrayList<String>();

        adapter2 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList2);
        list.setAdapter(adapter2);

        Task<QuerySnapshot> documentReference = db.collection("exercises").whereEqualTo("UUID", UUID).orderBy("date").get();
        documentReference.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        arrayList2.add("Exercise Pack: " + document.getString("type") + " Date: " + document.getString("date") );
                        adapter2.notifyDataSetChanged();

                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }

}