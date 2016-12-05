package com.master.app.orm;

import android.content.Context;

import com.master.app.SynopsisObj;
import com.master.bean.MapInfo;

import java.util.List;

/**
 * @param
 * @author Litao-pc on 2016/11/22.
 *         执行表操作
 */

public class DBHelper extends OrmDatabaseHelper {
    private static DBHelper db;
    private static final String DEF_DB_NAME = "def_db";
    private static final int DB_VERSION = 1;


    private DBHelper(Context context) {
        super(context, DEF_DB_NAME, null, DB_VERSION);
    }

    public static synchronized DBHelper builder() {
        if (db == null) {
            db = new DBHelper(SynopsisObj.getAppContext());
        }
        return db;
    }

    @Override
    public void createTables(List tables) {
        tables.add(MapInfo.class);
    }

    @Override
    public void updateTables(List tables) {
    }
}