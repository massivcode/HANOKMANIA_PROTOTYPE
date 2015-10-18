package com.raychoe.httpexample.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.raychoe.httpexample.HanokRepairAdvice.HanokRepairAdviceContract;
import com.raychoe.httpexample.HanokStatus.HanokStatusContract;

/**
 * Created by Ray Choe on 2015-10-17.
 */
public class HanokDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Hanok.db";
    public static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_HANOK_STATUS_ENTRY = "CREATE TABLE " +
            HanokStatusContract.HanokStatusEntry.TABLE_NAME + " (" +
            HanokStatusContract.HanokStatusEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HanokStatusContract.HanokStatusEntry.COLUMN_NAME_HANOKNUM + " TEXT NOT NULL, " +
            HanokStatusContract.HanokStatusEntry.COLUMN_NAME_ADDR + " TEXT NOT NULL, " +
            HanokStatusContract.HanokStatusEntry.COLUMN_NAME_PLLOTAGE + " TEXT NOT NULL, " +
            HanokStatusContract.HanokStatusEntry.COLUMN_NAME_TOTAR + " TEXT NOT NULL, " +
            HanokStatusContract.HanokStatusEntry.COLUMN_NAME_BUILDAREA + " TEXT NOT NULL, " +
            HanokStatusContract.HanokStatusEntry.COLUMN_NAME_FLOOR + " TEXT NOT NULL, " +
            HanokStatusContract.HanokStatusEntry.COLUMN_NAME_FLOOR2 + " TEXT NOT NULL, " +
            HanokStatusContract.HanokStatusEntry.COLUMN_NAME_USE + " TEXT , " +
            HanokStatusContract.HanokStatusEntry.COLUMN_NAME_STRUCTURE + " TEXT , " +
            HanokStatusContract.HanokStatusEntry.COLUMN_NAME_PLANTYPE + " TEXT , " +
            HanokStatusContract.HanokStatusEntry.COLUMN_NAME_BUILDDATE + " TEXT , " +
            HanokStatusContract.HanokStatusEntry.COLUMN_NAME_NOTE + " TEXT  " +
            ");";
    private static final String SQL_CREATE_HANOK_REPAIR_ADVICE_ENTRY = "CREATE TABLE " +
            HanokRepairAdviceContract.HanokRepairAdviceEntry.TABLE_NAME + " (" +
            HanokRepairAdviceContract.HanokRepairAdviceEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_HANOKNUM + " TEXT NOT NULL, " +
            HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_SN + " TEXT NOT NULL, " +
            HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_ADDR + " TEXT NOT NULL, " +
            HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_ITEM + " TEXT NOT NULL, " +
            HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_CONSTRUCTION + " TEXT , " +
            HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_REQUEST + " TEXT , " +
            HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_REVIEW + " TEXT , " +
            HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_RESULT + " TEXT , " +
            HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_LOANDEC + " TEXT , " +
            HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_NOTE + " TEXT  " +
            ");";

    private static HanokDBHelper sSingleton = null;

    public static synchronized HanokDBHelper getInstance(Context context) {
        if (sSingleton == null) {
            sSingleton = new HanokDBHelper(context);
        }
        return sSingleton;
    }

    private HanokDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_HANOK_STATUS_ENTRY);
        db.execSQL(SQL_CREATE_HANOK_REPAIR_ADVICE_ENTRY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
