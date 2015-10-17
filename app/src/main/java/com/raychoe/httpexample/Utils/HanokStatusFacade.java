package com.raychoe.httpexample.Utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.raychoe.httpexample.Hanok;

import java.util.List;

/**
 * Created by Ray Choe on 2015-10-17.
 */
public class HanokStatusFacade {
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

    public Cursor select() {
        Cursor cursor = null;
        SQLiteDatabase db = null;

        db = mHelper.getWritableDatabase();


        return cursor;
    }
}
