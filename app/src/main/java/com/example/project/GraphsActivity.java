package com.example.project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class GraphsActivity extends AppCompatActivity {

    String UUID;
    TextView age, height, weight;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayList;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphs);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        UUID = fAuth.getCurrentUser().getUid();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        UUID = fAuth.getCurrentUser().getUid();

        age = findViewById(R.id.textViewAge);
        height = findViewById(R.id.textViewHeight);
        weight = findViewById(R.id.textViewWeight);

        list = (ListView) findViewById(R.id.listViewHistory);
        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        list.setAdapter(adapter);

        Task<QuerySnapshot> documentReference = db.collection("userData").whereEqualTo("UUID", UUID).orderBy("date").get();
        documentReference.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        age.setText(document.getString("age"));
                        height.setText(document.getString("height"));
                        weight.setText(document.getString("weight"));

                        arrayList.add("Age: " + document.getString("age"));
                        adapter.notifyDataSetChanged();

                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }

}