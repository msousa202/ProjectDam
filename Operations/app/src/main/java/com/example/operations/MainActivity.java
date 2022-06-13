package com.example.operations;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doOperation(View view){
        EditText editTextNumber1 = (EditText) findViewById(R.id.edTextNumber1);

        int number1 = Integer.parseInt(editTextNumber1.getText().toString());

    }

}