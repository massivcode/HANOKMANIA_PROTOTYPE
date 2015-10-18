package com.raychoe.httpexample.HanokStatus;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.raychoe.httpexample.Utils.HanokDBHelper;

import java.util.List;

/**
 * Created by Ray Choe on 2015-10-17.
 */
public class HanokStatusFacade {
    private static final String TAG = HanokStatusFacade.class.getSimpleName();
    private HanokDBHelper mHelper;

    public HanokStatusFacade(Context context) {
        mHelper = HanokDBHelper.getInstance(context);
    }

    public void bulkInsert(List<HanokStatus> hanokStatusList) {
        SQLiteDatabase db = null;
        SQLiteStatement statement;

        try {
            if (hanokStatusList != null && hanokStatusList.size() > 0) {
                db = mHelper.getWritableDatabase();
                db.beginTransaction();
                statement = db.compileStatement(
                        "INSERT INTO "
                                + HanokStatusContract.HanokStatusEntry.TABLE_NAME
                                +
                                " ( "
                                +
                                HanokStatusContract.HanokStatusEntry.COLUMN_NAME_HANOKNUM + " , " +
                                HanokStatusContract.HanokStatusEntry.COLUMN_NAME_ADDR + ", " +
                                HanokStatusContract.HanokStatusEntry.COLUMN_NAME_PLLOTAGE + ", " +
                                HanokStatusContract.HanokStatusEntry.COLUMN_NAME_TOTAR + ", " +
                                HanokStatusContract.HanokStatusEntry.COLUMN_NAME_BUILDAREA + ", " +
                                HanokStatusContract.HanokStatusEntry.COLUMN_NAME_FLOOR + ", " +
                                HanokStatusContract.HanokStatusEntry.COLUMN_NAME_FLOOR2 + ", " +
                                HanokStatusContract.HanokStatusEntry.COLUMN_NAME_USE + ", " +
                                HanokStatusContract.HanokStatusEntry.COLUMN_NAME_STRUCTURE + ", " +
                                HanokStatusContract.HanokStatusEntry.COLUMN_NAME_PLANTYPE + ", " +
                                HanokStatusContract.HanokStatusEntry.COLUMN_NAME_BUILDDATE + ", " +
                                HanokStatusContract.HanokStatusEntry.COLUMN_NAME_NOTE
                                +
                                " ) "
                                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                for (HanokStatus hanokStatus : hanokStatusList) {
                    int column = 1;

                    statement.bindString(column++, hanokStatus.HANOKNUM);
                    statement.bindString(column++, hanokStatus.ADDR);
                    statement.bindString(column++, hanokStatus.PLOTTAGE);
                    statement.bindString(column++, hanokStatus.TOTAR);
                    statement.bindString(column++, hanokStatus.BUILDAREA);
                    statement.bindString(column++, hanokStatus.FLOOR);
                    statement.bindString(column++, hanokStatus.FLOOR2);
                    statement.bindString(column++, hanokStatus.USE);
                    statement.bindString(column++, hanokStatus.STRUCTURE);
                    statement.bindString(column++, hanokStatus.PLANTYPE);
                    statement.bindString(column++, hanokStatus.BUILDDATE);
                    statement.bindString(column++, hanokStatus.NOTE);

                    statement.execute();
                }
                statement.close();
                db.setTransactionSuccessful();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.endTransaction();
            }
        }
    }

    public void select() {
        Cursor cursor = null;
        SQLiteDatabase db = null;

        db = mHelper.getWritableDatabase();
        cursor = db.query(HanokStatusContract.HanokStatusEntry.TABLE_NAME, null, null, null, null, null, null);

        while(cursor.moveToNext()) {
            String num = cursor.getString(cursor.getColumnIndexOrThrow(HanokStatusContract.HanokStatusEntry.COLUMN_NAME_HANOKNUM));
            String addr = cursor.getString(cursor.getColumnIndexOrThrow(HanokStatusContract.HanokStatusEntry.COLUMN_NAME_ADDR));
            String buildArea = cursor.getString(cursor.getColumnIndexOrThrow(HanokStatusContract.HanokStatusEntry.COLUMN_NAME_BUILDAREA));
            String pllotage = cursor.getString(cursor.getColumnIndexOrThrow(HanokStatusContract.HanokStatusEntry.COLUMN_NAME_PLLOTAGE));
            String total = cursor.getString(cursor.getColumnIndexOrThrow(HanokStatusContract.HanokStatusEntry.COLUMN_NAME_TOTAR));
            Log.d(TAG, "등록번호 : " + num + " 주소 : " + addr + " buildArea : " + buildArea + " pllotage : " + pllotage + " total : " + total);
        }



    }
}
