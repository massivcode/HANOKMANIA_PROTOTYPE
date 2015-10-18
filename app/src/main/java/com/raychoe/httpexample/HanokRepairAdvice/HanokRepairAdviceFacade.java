package com.raychoe.httpexample.HanokRepairAdvice;

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
public class HanokRepairAdviceFacade {
    private static final String TAG = HanokRepairAdviceFacade.class.getSimpleName();
    private HanokDBHelper mHelper;

    public HanokRepairAdviceFacade(Context context) {
        mHelper = HanokDBHelper.getInstance(context);
    }

    public void bulkInsert(List<HanokRepairAdvice> hanokRepairAdviceList) {
        SQLiteDatabase db = null;
        SQLiteStatement statement;

        try {
            if (hanokRepairAdviceList != null && hanokRepairAdviceList.size() > 0) {
                db = mHelper.getWritableDatabase();
                db.beginTransaction();
                statement = db.compileStatement(
                        "INSERT INTO "
                                + HanokRepairAdviceContract.HanokRepairAdviceEntry.TABLE_NAME
                                +
                                " ( "
                                +
                                HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_HANOKNUM + " , " +
                                HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_SN + ", " +
                                HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_ADDR + ", " +
                                HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_ITEM + ", " +
                                HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_CONSTRUCTION + ", " +
                                HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_REQUEST + ", " +
                                HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_REVIEW + ", " +
                                HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_RESULT + ", " +
                                HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_LOANDEC + ", " +
                                HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_NOTE
                                +
                                " ) "
                                + "values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

                for (HanokRepairAdvice hanokRepairAdvice : hanokRepairAdviceList) {
                    int column = 1;

                    statement.bindString(column++, hanokRepairAdvice.HANOKNUM);
                    statement.bindString(column++, hanokRepairAdvice.SN);
                    statement.bindString(column++, hanokRepairAdvice.ADDR);
                    statement.bindString(column++, hanokRepairAdvice.ITEM);
                    statement.bindString(column++, hanokRepairAdvice.CONSTRUCTION);
                    statement.bindString(column++, hanokRepairAdvice.REQUEST);
                    statement.bindString(column++, hanokRepairAdvice.REVIEW);
                    statement.bindString(column++, hanokRepairAdvice.RESULT);
                    statement.bindString(column++, hanokRepairAdvice.LOANDEC);
                    statement.bindString(column++, hanokRepairAdvice.NOTE);

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
        cursor = db.query(HanokRepairAdviceContract.HanokRepairAdviceEntry.TABLE_NAME, null, null, null, null, null, null);


        while(cursor.moveToNext()) {
            String num = cursor.getString(cursor.getColumnIndexOrThrow(HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_HANOKNUM));
            String sn = cursor.getString(cursor.getColumnIndexOrThrow(HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_SN));
            String addr = cursor.getString(cursor.getColumnIndexOrThrow(HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_ADDR));
            String item = cursor.getString(cursor.getColumnIndexOrThrow(HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_ITEM));
            String construction = cursor.getString(cursor.getColumnIndexOrThrow(HanokRepairAdviceContract.HanokRepairAdviceEntry.COLUMN_NAME_CONSTRUCTION));
            Log.d(TAG, "num : " + num + " sn : " + sn + " addr : " + addr + " item : " + item + " construction : " + construction);
        }




    }
}
