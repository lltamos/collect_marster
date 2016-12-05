package com.master.dao;

import com.master.app.orm.DBHelper;
import com.master.app.orm.OrmDaoSupport;
import com.master.app.orm.OrmDatabaseHelper;
import com.master.bean.MapInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @param
 * @author Litao-pc on 2016/11/30.
 *         ~
 */

public class MapDao<T> extends OrmDaoSupport<T> {

    private static MapDao mapDao;

    private MapDao(Class cls) {
        super(cls);
    }


    public static MapDao get() {

        if (mapDao != null) ;
        else {
            mapDao = new MapDao(MapInfo.class);
        }
        return mapDao;

    }

    @Override
    protected OrmDatabaseHelper getHelper() {
        return DBHelper.builder();
    }


    //新增地图
    public boolean inserts(T obj) {
        try {
            super.insert(obj);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //查询所有bean
    public List querysAll() {
        try {
            List<T> ts = super.queryAll();
            return ts;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
