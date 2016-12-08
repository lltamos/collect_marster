package com.master.app.orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.master.app.Manager.ConfigMAnager;
import com.master.app.SynopsisObj;
import com.master.app.tools.LoggerUtils;
import com.master.bean.Fields;
import com.master.bean.Maps;
import com.master.bean.TableContext;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>Title:${type_inName}<p/>
 * <p>Description:<p/>
 * <p>Company: </p>
 *
 * @author litao
 * @mail llsmpsvn@gmail.com
 * @date on 2016/12/6
 */
public class DbHelperDbHelper {

    private static final String TAG = "DbHelper";

    private static final String DATABASE_NAME = "DbHelper.db";
    private static final int DATABASE_VERSION = 1;

    private static DbHelperDbHelper dbHelperDbHelper = new DbHelperDbHelper(SynopsisObj.getAppContext());
    protected static SQLiteDatabase mDb;
    private static DbHelper mDbHelper;

    public DbHelperDbHelper(Context context) {

        mDbHelper = new DbHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
            @Override
            protected void execSqlTable(SQLiteDatabase db) {
                LoggerUtils.d("sql_T_JWD", sql_T_MAP);
                db.execSQL(sql_T_MAP);
                LoggerUtils.d("sql_T_JWD", sql_T_JWD);
                db.execSQL(sql_T_JWD);
                List<String> sqls = getsAttributeTableSQL();
                for (String s : sqls) {
                    db.execSQL(s);
                    LoggerUtils.d("sql_T_JWD", s);
                }
            }
        };
    }

    public static DbHelperDbHelper open() throws SQLException {
        mDb = mDbHelper.getWritableDatabase();
        return dbHelperDbHelper;
    }

    public void close() {
        mDb.close();
    }


    // -------------- T_MAP DEFINITIONS ------------
    public static final String MAP_TABLE = "T_MAP";

    public static final String MAP_NUMBER_COLUMN = "MAPID";
    public static final int MAP_MAPID_COLUMN_POSITION = 1;

    public static final String MAP_MAPNAME_COLUMN = "MAPNAME";
    public static final int MAP_MAPNAME_COLUMN_POSITION = 2;

    public static final String MAP_CJSJ_COLUMN = "CJSJ";
    public static final int MAP_CJSJ_COLUMN_POSITION = 3;

    public static final String MAP_SFDQSY_COLUMN = "SFDQSY";
    public static final int MAP_SFDQSY_COLUMN_POSITION = 4;


    // -------------- T_JWD DEFINITIONS ------------
    public static final String JWD_TABLE = "T_JWD";

    public static final String JWD_CROWID_COLUMN = "CROWID";
    public static final int JWD_CROWID_COLUMN_POSITION = 1;

    public static final String JWD_FID_COLUMN = "FID";
    public static final int JWD_FID_COLUMN_POSITION = 2;

    public static final String JWD_JD_COLUMN = "JD";
    public static final int JWD_JD_COLUMN_POSITION = 3;

    public static final String JWD_WD_COLUMN = "WD";
    public static final int JWD_WD_COLUMN_POSITION = 4;

    public static final String JWD_CJSJ_COLUMN = "CJSJ";
    public static final int JWD_CJSJ_COLUMN_POSITION = 5;

    private static String sql_T_JWD = "CREATE TABLE " + JWD_TABLE + " (" +
            JWD_CROWID_COLUMN + " varchar(36) PRIMARY KEY," +
            JWD_FID_COLUMN + " varchar(50) NOT NULL," +
            JWD_JD_COLUMN + " numeric(11,8)," +
            JWD_WD_COLUMN + " numeric(11,8)," +
            JWD_CJSJ_COLUMN + " Date," +
            " foreign key (" + JWD_FID_COLUMN + ") references " + MAP_TABLE + " (" + MAP_NUMBER_COLUMN + ")" +
            ")";

    private static String sql_T_MAP = "CREATE TABLE " + MAP_TABLE + " (" +
            MAP_NUMBER_COLUMN + " INTEGER  PRIMARY KEY AUTOINCREMENT," +
            MAP_MAPNAME_COLUMN + " varchar(50) NOT NULL," +
            MAP_CJSJ_COLUMN + " DATE NOT NULL," +
            MAP_SFDQSY_COLUMN + " varchar(10)" +
            ")";


// ----------------JWD HELPERS --------------------
//    public long addEvent(String Description, Date Time, String ShortDesc) {
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(EVENT_DESCRIPTION_COLUMN, Description);
//        contentValues.put(EVENT_TIME_COLUMN, Time.getTime());
//        contentValues.put(EVENT_SHORTDESC_COLUMN, ShortDesc);
//        return mDb.insert(EVENT_TABLE, null, contentValues);
//    }

    //    public long updateEvent(long rowIndex, String Description, Date Time, String ShortDesc) {
//        String where = ROW_ID + " = " + rowIndex;
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(EVENT_DESCRIPTION_COLUMN, Description);
//        contentValues.put(EVENT_TIME_COLUMN, Time.getTime());
//        contentValues.put(EVENT_SHORTDESC_COLUMN, ShortDesc);
//        return mDb.update(EVENT_TABLE, contentValues, where, null);
//    }
//
//    public boolean removeEvent(long rowIndex) {
//        return mDb.delete(EVENT_TABLE, ROW_ID + " = " + rowIndex, null) > 0;
//    }
//
//    public boolean removeAllEvent() {
//        return mDb.delete(EVENT_TABLE, null, null) > 0;
//    }
//
//    public Cursor getAllEvent() {
//        return mDb.query(EVENT_TABLE, new String[]{
//                ROW_ID,
//                EVENT_DESCRIPTION_COLUMN,
//                EVENT_TIME_COLUMN,
//                EVENT_SHORTDESC_COLUMN
//        }, null, null, null, null, null);
//    }
//
//    public Cursor getEvent(long rowIndex) {
//        Cursor res = mDb.query(EVENT_TABLE, new String[]{
//                ROW_ID,
//                EVENT_DESCRIPTION_COLUMN,
//                EVENT_TIME_COLUMN,
//                EVENT_SHORTDESC_COLUMN
//        }, ROW_ID + " = " + rowIndex, null, null, null, null);
//
//        if (res != null) {
//            res.moveToFirst();
//        }
//        return res;
//    }
//
//    // ----------------MAP HELPERS --------------------
    public long addMAp(String mapName, Date cjsj, String sfdqsy) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MAP_MAPNAME_COLUMN, mapName);
        contentValues.put(MAP_CJSJ_COLUMN, cjsj.getTime());
        contentValues.put(MAP_SFDQSY_COLUMN, sfdqsy);
        return mDb.insert(MAP_TABLE, null, contentValues);
    }

    //
//    public long updateCall(long rowIndex, String Number, Date Time, Float value, long longnumber) {
//        String where = ROW_ID + " = " + rowIndex;
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(CALL_NUMBER_COLUMN, Number);
//        contentValues.put(CALL_TIME_COLUMN, Time.getTime());
//        contentValues.put(CALL_VALUE_COLUMN, value);
//        contentValues.put(CALL_LONGNUMBER_COLUMN, longnumber);
//        return mDb.update(CALL_TABLE, contentValues, where, null);
//    }
//
    public boolean removeCall(String rowIndex) {
        return mDb.delete(MAP_TABLE, MAP_NUMBER_COLUMN + " = " + rowIndex, null) > 0;
    }

    public boolean removeAllCall() {
        return mDb.delete(MAP_TABLE, null, null) > 0;
    }

    public List<Maps> getAllMap() {
        List<Maps> list = new ArrayList<>();
        String sql = "select * from " + MAP_TABLE;
        Cursor cursor = mDb.rawQuery(sql, null);
        int count = cursor.getCount();
        while (cursor.moveToNext()) {
            Maps m = new Maps();
            m.mId = cursor.getInt(cursor.getColumnIndex(MAP_NUMBER_COLUMN));
            m.mName = cursor.getString(cursor.getColumnIndex(MAP_MAPNAME_COLUMN));
            m.cjsj = cursor.getString(cursor.getColumnIndex(MAP_CJSJ_COLUMN));
            m.dqsy = cursor.getString(cursor.getColumnIndex(MAP_SFDQSY_COLUMN));
            list.add(m);
            LoggerUtils.d("getAllMap length", m.toString());
        }

        cursor.close();
        return list;

//        return
    }
//
//    public Cursor getCall(long rowIndex) {
//        Cursor res = mDb.query(CALL_TABLE, new String[]{
//                ROW_ID,
//                CALL_NUMBER_COLUMN,
//                CALL_TIME_COLUMN,
//                CALL_VALUE_COLUMN,
//                CALL_LONGNUMBER_COLUMN
//        }, ROW_ID + " = " + rowIndex, null, null, null, null);
//
//        if (res != null) {
//            res.moveToFirst();
//        }
//        return res;
//    }


    private abstract class DbHelper extends SQLiteOpenHelper {


        public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {

            execSqlTable(db);

        }

        protected abstract void execSqlTable(SQLiteDatabase db);

        @Override
        public void onOpen(SQLiteDatabase db) {
            super.onOpen(db);
            if (!db.isReadOnly()) {
                db.execSQL("PRAGMA foreign_keys=ON;");
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading from version " +
                    oldVersion + " to " +
                    newVersion + ", which will destroy all old data");


//            db.execSQL("DROP TABLE IF EXISTS " + MAP_TABLE + ";");
//            db.execSQL("DROP TABLE IF EXISTS " + JWD_TABLE + ";");
//            onCreate(db);
        }
    }

    private List<String> getsAttributeTableSQL() {
        List<String> list = new ArrayList<>();

        List<TableContext> tableContexts = ConfigMAnager.create().getTablecontextList();

        if (tableContexts.size() > 1) {
            LoggerUtils.d("tableContexts长度：", "构建失败");
        }
        LoggerUtils.d("tableContexts长度：", tableContexts.size() + "");
        for (TableContext t : tableContexts) {
            String tName = t.getTName(); //表名
            String sql = "CREATE TABLE " + tName + "(";
            List<Fields> fields = t.getFields();
            for (int i = 0; i < fields.size(); i++) {
                Fields f = fields.get(i);
                String fName = f.getFName();
                if (i == 0) {
                    sql = sql + fName + " " + "VARCHAR(20) NOT NULL PRIMARY KEY,";
                } else {
                    sql = sql + fName + " " + "TEXT NOT NULL,";
                }
            }
            int length = sql.length();
            sql = sql.substring(0, length - 1);
            sql = sql + ");";
            list.add(sql);
        }
        return list;
    }
}
