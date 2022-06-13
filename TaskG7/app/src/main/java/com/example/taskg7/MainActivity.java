package com.example.taskg7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list();
    }

    static public DBManager dbManager;
    private ListView records_lv;
    static private SimpleCursorAdapter adapter;
    private Cursor cursor;

    public void list() {
        dbManager = new DBManager(this);
        dbManager.open();
        cursor = dbManager.fetch();
        records_lv = (ListView) findViewById(R.id.records_lv);
        // fields to display in the list
        String[] display = new String[]{"name"};
        // fields to bind
        int[] bindTo = new int[]{android.R.id.text1};
        // use predefined android layout simple 1 contaning only one : text1 //
        adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, cursor, display, bindTo);
        records_lv.setAdapter(adapter);
        records_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                Intent edit_intent = new Intent(getApplicationContext(), EditRecordActivity.class);
                edit_intent.putExtra("id", viewId);
                startActivity(edit_intent);
            }
        });
    }

    public void newRecord(View view){
        Intent edit_intent = new Intent(getApplicationContext(), EditRecordActivity.class);
        edit_intent.putExtra("id", 0);
        startActivity(edit_intent);
    }

    static public void listview_refresh(){
        adapter.changeCursor(dbManager.fetch());
        adapter.notifyDataSetChanged();
    }



}