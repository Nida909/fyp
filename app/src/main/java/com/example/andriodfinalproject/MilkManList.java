package com.example.andriodfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MilkManList extends AppCompatActivity {
    ListView lv;
    ArrayList<String> arrayList=new ArrayList<>();
    DatabaseHelper dbh;
    SQLiteDatabase db;
String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_milk_man_list);
        dbh = new DatabaseHelper(this);
        Intent inten=getIntent();
        str=inten.getStringExtra("val");
        Toast.makeText(getApplicationContext(),"Record id"+str,Toast.LENGTH_LONG).show();
        db = dbh.getReadableDatabase();

        String[] colm = {DatabaseContract.MilkMan.COL_NAME, DatabaseContract.MilkMan.COL_LOCATION};
        Cursor c = db.query(DatabaseContract.MilkMan.TABLE_NAME, colm, null, null
                , null, null, null, null);
        if (c.getCount() == 0) {

            Toast.makeText(getApplicationContext(), "No Record exist", Toast.LENGTH_LONG).show();
        } else {
            while (c.moveToNext()) {
           arrayList.add("Milk Man Name: "+c.getString(0)+",  Loc : "+c.getString(1));
            }
            lv = (ListView) findViewById(R.id.list1);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);
        lv.setAdapter(adapter);
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main1, menu);
        return true;


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.Ordersh:
                Toast.makeText(getApplicationContext(),"Record id"+str,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,CustomerHistory.class);
                intent.putExtra("var", str);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);


        }
    }
}