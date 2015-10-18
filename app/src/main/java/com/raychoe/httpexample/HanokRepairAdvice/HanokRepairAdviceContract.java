package com.raychoe.httpexample.HanokRepairAdvice;

import android.provider.BaseColumns;

/**
 * Created by Ray Choe on 2015-10-17.
 */
public class HanokRepairAdviceContract {

    public HanokRepairAdviceContract() {
    }

    public static abstract class HanokRepairAdviceEntry implements BaseColumns {
        public static final String TABLE_NAME = "HanokRepairAdviceEntry";
        public static final String COLUMN_NAME_HANOKNUM = "hanoknum";
        public static final String COLUMN_NAME_SN = "sn";
        public static final String COLUMN_NAME_ADDR = "addr";
        public static final String COLUMN_NAME_ITEM = "item";
        public static final String COLUMN_NAME_CONSTRUCTION = "construction";
        public static final String COLUMN_NAME_REQUEST = "request";
        public static final String COLUMN_NAME_REVIEW = "review";
        public static final String COLUMN_NAME_RESULT = "result";
        public static final String COLUMN_NAME_LOANDEC = "loandec";
        public static final String COLUMN_NAME_NOTE = "note";
    }
}
