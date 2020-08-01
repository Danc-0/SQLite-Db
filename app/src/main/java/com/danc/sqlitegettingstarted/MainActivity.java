package com.danc.sqlitegettingstarted;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static com.danc.sqlitegettingstarted.StudentDetailsContract.*;
import static com.danc.sqlitegettingstarted.StudentDetailsContract.DetailsEntry.TABLE_NAME;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    StudentDetailsDbHelper dbHelper;
    SQLiteDatabase db;

    EditText name, surname, marks;
    Button addData, viewData;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        marks = findViewById(R.id.marks);
        addData = findViewById(R.id.addData);

        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                moveToParent();
            }
        });

//        viewData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Cursor res = ViewAllData();
//
//                if (res.getCount() == 0){
//                    //Show message
//                    return;
//                } else {
//                    StringBuffer buffer = new StringBuffer();
//                    while (res.moveToNext()) {
//                        buffer.append("_ID: " + res.getString(0) + "\n");
//                        buffer.append("Name: " + res.getString(1) + "\n");
//                        buffer.append("Surname: " + res.getString(2) + "\n");
//                        buffer.append("Marks: " + res.getString(3) + "\n");
//                    }
//                }
//            }
//        });
//
    }

    public boolean insertData() {
        String firstName = name.getText().toString();
        String surName1 = surname.getText().toString();
        String totalMarks = marks.getText().toString();

        dbHelper = new StudentDetailsDbHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DetailsEntry.COLUMN_NAME, firstName);
        values.put(DetailsEntry.COLUMN_SURNAME, surName1);
        values.put(DetailsEntry.COLUMN_MARKS, totalMarks);

        long newRowID = db.insert(TABLE_NAME, null, values);
//        db = dbHelper.getReadableDatabase();

        if (newRowID == -1) {
            Log.d(TAG, "insertData: Row Id" + newRowID);
            return false;

        } else {
            Toast.makeText(this, "Data Inserted Successful", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "insertData: Data Inserted Successfully");
            return true;
        }
    }

    public void getAllData() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                TABLE_NAME,
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder

        );
        List<Long> itemIds = new ArrayList<Long>();
        while (cursor.moveToNext()){
            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(DetailsEntry._ID)
            );
            itemIds.add(itemId);
            cursor.close();
        }
    }

    public Cursor ViewAllData(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

    }

    public void moveToParent() {
        startActivity(new Intent(this, ParentsActivity.class));
    }
}