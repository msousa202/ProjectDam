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
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.OrderBy;
import com.google.firestore.v1.StructuredQuery;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GraphsActivity extends AppCompatActivity {

    String UUID;
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

        list = (ListView) findViewById(R.id.listViewHistory);
        arrayList = new ArrayList<String>();

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList);
        list.setAdapter(adapter);

        DecimalFormat df = new DecimalFormat("#.##");

        Task<QuerySnapshot> documentReference = db.collection("userData").whereEqualTo("UUID", UUID).orderBy("date", Query.Direction.ASCENDING).get();
        documentReference.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());
                        double imc =(Double.parseDouble(document.getString("weight")))/(Double.parseDouble(document.getString("height"))*Double.parseDouble(document.getString("height")));
                        arrayList.add("IMC "+ df.format(imc) +" Age: " + document.getString("age")+" Height: " + document.getString("height")+" Weight: " + document.getString("weight") );
                        arrayList.add("^ Date: " + document.getString("date"));
                        adapter.notifyDataSetChanged();

                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });

    }

}