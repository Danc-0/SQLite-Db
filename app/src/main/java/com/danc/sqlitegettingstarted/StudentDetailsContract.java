package com.danc.sqlitegettingstarted;

import android.provider.BaseColumns;

public class StudentDetailsContract {

    private StudentDetailsContract() {

    }

    //Here We Describe the Db Schema The Table Name and the Columns
    public static class DetailsEntry implements BaseColumns {
        public static final String TABLE_NAME = "student_table";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_MARKS = "marks";
    }

    public static class ParentsEntry implements BaseColumns {
        public static final String TABLE_NAME = "parent_table";
        public static final String COLUMN_PARENT_FIRST_NAME = "parent_first_name";
        public static final String COLUMN_PARENT_SECOND_NAME = "parent_second_name";
        public static final String COLUMN_PARENT_ID = "parent_ID_Number";
    }

    //Write the SQL command to Create table followed with the column names for Creating the Db
    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + DetailsEntry.TABLE_NAME + " (" +
                    DetailsEntry._ID + " INTEGER PRIMARY KEY, " +
                    DetailsEntry.COLUMN_NAME + " TEXT," +
                    DetailsEntry.COLUMN_SURNAME + " TEXT," +
                    DetailsEntry.COLUMN_MARKS + " INTEGER)";

    public static final String SQL_DELETE_ENTRIES =
            "DELETE TABLE IF EXITS " + DetailsEntry.TABLE_NAME;


    public static final String SQL_CREATE_PARENT_ENTRIES =
        "CREATE TABLE " + ParentsEntry.TABLE_NAME + " (" +
                BaseColumns._ID + "INTEGER PRIMARY KEY" +
                ParentsEntry.COLUMN_PARENT_FIRST_NAME + "TEXT" +
                ParentsEntry.COLUMN_PARENT_SECOND_NAME + "TEXT" +
                ParentsEntry.COLUMN_PARENT_ID + "INTEGER)";

    public static final String SQL_DELETE_PARENT_ENTRIES =
            "DELETE TABLE IF EXISTS " + ParentsEntry.TABLE_NAME;


    public static final String[] projection = {
            DetailsEntry._ID,
            DetailsEntry.COLUMN_NAME,
            DetailsEntry.COLUMN_SURNAME,
            DetailsEntry.COLUMN_MARKS
    };

    public static String selection = DetailsEntry.COLUMN_NAME + " = ?";

    public static String[] selectionArgs = {"Daniel"};

    public static final String sortOrder = DetailsEntry.COLUMN_NAME + " DESC ";
}
