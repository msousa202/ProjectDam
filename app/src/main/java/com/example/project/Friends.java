package com.example.project;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Friends extends AppCompatActivity {

    Button btnInsertFriend;
    EditText editTextFriend;
    String MyUUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        FirebaseAuth fAuth = FirebaseAuth.getInstance();
        MyUUID = fAuth.getCurrentUser().getUid(); //obter user id a partir da authentication firebase

        FirebaseFirestore db = FirebaseFirestore.getInstance(); //base de dados FireStore

        LinearLayout friendsListedLayout;
        friendsListedLayout = findViewById(R.id.friendsListedLayout); //layout onde vamos adicionar textview dinâmicos

        Map<String, Object> friendData = new HashMap<>(); // começo de fazer amigo
        btnInsertFriend = findViewById(R.id.addFriend);
        editTextFriend = findViewById(R.id.addFriends);

        btnInsertFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String friend = editTextFriend.getText().toString();

                friendData.put("friendEmail", friend);
                friendData.put("MyUUID", MyUUID);

                Task<QuerySnapshot> existingFriends = db.collection("friendData").whereEqualTo("friendEmail", friend).get();
                Task<QuerySnapshot> existingAccounts = db.collection("userFireStore").whereEqualTo("email", friend).get();
                existingAccounts.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()&&!existingAccounts.getResult().isEmpty()&&existingFriends.getResult().isEmpty()) {

                            db.collection("friendData")
                                    .add(friendData)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Toast.makeText(Friends.this,"Added Friend Succefully", Toast.LENGTH_SHORT).show();
                                            finish();
                                            startActivity(getIntent());
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                        }
                                    });

                        } else if (task.isSuccessful()&&!existingAccounts.getResult().isEmpty()&&!existingFriends.getResult().isEmpty()){
                            Toast.makeText(Friends.this,"That Friend is in your list!", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(Friends.this,"That Friend Hasn't started using the app yet!", Toast.LENGTH_SHORT).show();
                        }
                    }


                });
            }
        });//fim de fazer amigo

        Task<QuerySnapshot> documentReference = db.collection("friendData").whereEqualTo("MyUUID", MyUUID).orderBy("friendEmail").get();//adicionar TextViews com emails dos amigos
        documentReference.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Log.d(TAG, document.getId() + " => " + document.getData());

                        TextView text1 = new TextView(Friends.this);
                        text1.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        friendsListedLayout.addView(text1);
                        text1.setText("" + document.getString("friendEmail"));

                    }
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });//Fim de adicionar textViews

    }
}