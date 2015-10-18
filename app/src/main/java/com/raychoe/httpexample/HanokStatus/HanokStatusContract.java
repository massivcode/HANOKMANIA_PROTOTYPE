package com.raychoe.httpexample.HanokStatus;

import android.provider.BaseColumns;

/**
 * Created by Ray Choe on 2015-10-17.
 */
public class HanokStatusContract {

    public HanokStatusContract() {
    }

    public static abstract class HanokStatusEntry implements BaseColumns {
        public static final String TABLE_NAME = "HanokStatus";
        public static final String COLUMN_NAME_HANOKNUM = "hanoknum";
        public static final String COLUMN_NAME_ADDR = "addr";
        public static final String COLUMN_NAME_PLLOTAGE = "pllotage";
        public static final String COLUMN_NAME_TOTAR = "totar";
        public static final String COLUMN_NAME_BUILDAREA = "buildarea";
        public static final String COLUMN_NAME_FLOOR = "floor";
        public static final String COLUMN_NAME_FLOOR2 = "floor2";
        public static final String COLUMN_NAME_USE = "use";
        public static final String COLUMN_NAME_STRUCTURE = "structure";
        public static final String COLUMN_NAME_PLANTYPE = "plantype";
        public static final String COLUMN_NAME_BUILDDATE = "builddate";
        public static final String COLUMN_NAME_NOTE = "note";
    }
}
