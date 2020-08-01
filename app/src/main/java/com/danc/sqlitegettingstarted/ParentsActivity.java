package com.danc.sqlitegettingstarted;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ParentsActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "ParentsActivity";

    EditText name, surname, idNumber;
    Button SaveData;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents);

        name = findViewById(R.id.name);
        surname = findViewById(R.id.surname);
        idNumber = findViewById(R.id.idNumber);
        SaveData = findViewById(R.id.saveData);

        SaveData.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        insertParentDetails();
    }

    private boolean insertParentDetails() {
        ParentDetailsDbOpenHelper dbHelper;

        String firstName = name.getText().toString();
        String secondName = surname.getText().toString();
        String parentID = idNumber.getText().toString();

        dbHelper = new ParentDetailsDbOpenHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(StudentDetailsContract.ParentsEntry.COLUMN_PARENT_FIRST_NAME, firstName);
        values.put(StudentDetailsContract.ParentsEntry.COLUMN_PARENT_SECOND_NAME, secondName);
        values.put(StudentDetailsContract.ParentsEntry.COLUMN_PARENT_ID, parentID);

        long newValuesID = db.insert(StudentDetailsContract.ParentsEntry.TABLE_NAME, null, values);
        db = dbHelper.getReadableDatabase();

        if (newValuesID == -1) {
            Log.d(TAG, "saveParentDetails: new Row ID" + newValuesID);
            return false;

        } else {
            Toast.makeText(this, "Parent Data Inserted Successfully", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "saveParentDetails: Data Inserted Successfully");
            return true;
        }

    }

}