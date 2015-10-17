package com.raychoe.httpexample.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ray Choe on 2015-10-17.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Hanok.db";
    public static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            HanokStatusContract.HanokEntry.TABLE_NAME + " (" +
            HanokStatusContract.HanokEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HanokStatusContract.HanokEntry.COLUMN_NAME_HANOKNUM + " TEXT NOT NULL, " +
            HanokStatusContract.HanokEntry.COLUMN_NAME_ADDR + " TEXT NOT NULL, " +
            HanokStatusContract.HanokEntry.COLUMN_NAME_PLLOTAGE + " TEXT NOT NULL, " +
            HanokStatusContract.HanokEntry.COLUMN_NAME_TOTAR + " TEXT NOT NULL, " +
            HanokStatusContract.HanokEntry.COLUMN_NAME_BUILDAREA + " TEXT NOT NULL, " +
            HanokStatusContract.HanokEntry.COLUMN_NAME_FLOOR + " TEXT NOT NULL, " +
            HanokStatusContract.HanokEntry.COLUMN_NAME_FLOOR2 + " TEXT NOT NULL, " +
            HanokStatusContract.HanokEntry.COLUMN_NAME_USE + " TEXT , " +
            HanokStatusContract.HanokEntry.COLUMN_NAME_STRUCTURE + " TEXT , " +
            HanokStatusContract.HanokEntry.COLUMN_NAME_PLANTYPE + " TEXT , " +
            HanokStatusContract.HanokEntry.COLUMN_NAME_BUILDDATE + " TEXT , " +
            HanokStatusContract.HanokEntry.COLUMN_NAME_NOTE + " TEXT  " +
            ");";

    private static DBHelper sSingleton = null;

    public static synchronized DBHelper getInstance(Context context) {
        if (sSingleton == null) {
            sSingleton = new DBHelper(context);
        }
        return sSingleton;
    }

    private DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
