package com.example.taskg7;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class EditRecordActivity extends AppCompatActivity {

    private DBManager dbManager;
    long id;
    EditText name_txt;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_record);

        dbManager = MainActivity.dbManager;
        id=getIntent().getLongExtra("id", 0);
        TextView result_tv= (TextView) findViewById(R.id.result_tv);
        result_tv.setText("New task ");
        name_txt = (EditText) findViewById(R.id.name_txt);
        if (id>0) {
            result_tv.setText("Editing task " + id);
            Cursor cursor = dbManager.fetch_one(id);
            name_txt.setText(cursor.getString(cursor.getColumnIndex("name")));
            edit_buttons();
        }
    }

    public void Back(View view){
        finish();
    }

    public void save(View view){
        name= name_txt.getText().toString();
        if (id>0){
            dbManager.update(id, name );
        } else {
            dbManager.insert(name );
        }
        MainActivity.listview_refresh();
        finish();
    }

    public void delete(View view){
        dbManager.delete(id);
        MainActivity.listview_refresh();
        finish();
    }
    private void edit_buttons(){
        Button delete_btn = (Button) findViewById(R.id.delete_btn);
        delete_btn.setVisibility(View.VISIBLE);
    }




}