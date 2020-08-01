package com.danc.sqlitegettingstarted;

import android.provider.BaseColumns;

public class FeedReaderContract {

    private FeedReaderContract() {

    }

    public static class FeedEntry implements BaseColumns {

        public static final String TABLE_NAME = "student_table";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_SURNAME = "surname";
        public static final String COLUMN_MARKS = "marks";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY, " +
                    FeedEntry.COLUMN_NAME + " TEXT," +
                    FeedEntry.COLUMN_SURNAME + " TEXT," +
                    FeedEntry.COLUMN_MARKS + " INTEGER)";

    public static final String SQL_DELETE_ENTRIES =
            "DELETE TABLE IF EXITS " + FeedEntry.TABLE_NAME;

    public static final String[] projection = {
            FeedEntry._ID,
            FeedEntry.COLUMN_NAME,
            FeedEntry.COLUMN_SURNAME,
            FeedEntry.COLUMN_MARKS
    };

    public static String selection = FeedEntry.COLUMN_NAME + " = ?";

    public static String[] selectionArgs = {"Daniel"};

    public static final String sortOrder = FeedEntry.COLUMN_NAME + " DESC ";
}
