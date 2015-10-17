package com.raychoe.httpexample.Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.raychoe.httpexample.Hanok;

import java.util.List;

/**
 * Created by Ray Choe on 2015-10-17.
 */
public class HanokStatusFacade {
    private static final String TAG = HanokStatusFacade.class.getSimpleName();
    private DBHelper mHelper;

    public HanokStatusFacade(Context context) {
        mHelper = DBHelper.getInstance(context);
    }

    public void bulkInsert(List<Hanok> hanokList) {
        SQLiteDatabase db = null;
        SQLiteStatement statement;

        try {
            if (hanokList != null && hanokList.size() > 0) {
                db = mHelper.getWritableDatabase();
                db.beginTransaction();
                statement = db.compileStatement(
                        "INSERT INTO "
                                + HanokStatusContract.HanokEntry.TABLE_NAME
                                +
                                " ( "
                                +
                                HanokStatusContract.HanokEntry.COLUMN_NAME_HANOKNUM + " , " +
                                HanokStatusContract.HanokEntry.COLUMN_NAME_ADDR + ", " +
                                HanokStatusContract.HanokEntry.COLUMN_NAME_PLLOTAGE + ", " +
                                HanokStatusContract.HanokEntry.COLUMN_NAME_TOTAR + ", " +
                                HanokStatusContract.HanokEntry.COLUMN_NAME_BUILDAREA + ", " +
                                HanokStatusContract.HanokEntry.COLUMN_NAME_FLOOR + ", " +
                                HanokStatusContract.HanokEntry.COLUMN_NAME_FLOOR2 + ", " +
                                HanokStatusContract.HanokEntry.COLUMN_NAME_USE + ", " +
                                HanokStatusContract.HanokEntry.COLUMN_NAME_STRUCTURE + ", " +
                                HanokStatusContract.HanokEntry.COLUMN_NAME_PLANTYPE + ", " +
                                HanokStatusContract.HanokEntry.COLUMN_NAME_BUILDDATE + ", " +
                                HanokStatusContract.HanokEntry.COLUMN_NAME_NOTE
                                +
                                " ) "
                                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                for (Hanok hanok : hanokList) {
                    int column = 1;

                    statement.bindString(column++, hanok.HANOKNUM);
                    statement.bindString(column++, hanok.ADDR);
                    statement.bindString(column++, hanok.PLOTTAGE);
                    statement.bindString(column++, hanok.TOTAR);
                    statement.bindString(column++, hanok.BUILDAREA);
                    statement.bindString(column++, hanok.FLOOR);
                    statement.bindString(column++, hanok.FLOOR2);
                    statement.bindString(column++, hanok.USE);
                    statement.bindString(column++, hanok.STRUCTURE);
                    statement.bindString(column++, hanok.PLANTYPE);
                    statement.bindString(column++, hanok.BUILDDATE);
                    statement.bindString(column++, hanok.NOTE);

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
        cursor = db.query(HanokStatusContract.HanokEntry.TABLE_NAME, null, null, null, null, null, null);

        while(cursor.moveToNext()) {
            String num = cursor.getString(cursor.getColumnIndexOrThrow(HanokStatusContract.HanokEntry.COLUMN_NAME_HANOKNUM));
            String addr = cursor.getString(cursor.getColumnIndexOrThrow(HanokStatusContract.HanokEntry.COLUMN_NAME_ADDR));
            String buildArea = cursor.getString(cursor.getColumnIndexOrThrow(HanokStatusContract.HanokEntry.COLUMN_NAME_BUILDAREA));
            String pllotage = cursor.getString(cursor.getColumnIndexOrThrow(HanokStatusContract.HanokEntry.COLUMN_NAME_PLLOTAGE));
            String total = cursor.getString(cursor.getColumnIndexOrThrow(HanokStatusContract.HanokEntry.COLUMN_NAME_TOTAR));
            Log.d(TAG, "등록번호 : " + num + " 주소 : " + addr + " buildArea : " + buildArea + " pllotage : " + pllotage + " total : " + total);
        }



    }
}
