package com.master.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * @param
 * @author Litao-pc on 2016/11/30.
 *         ~
 */

@DatabaseTable(tableName = "map_bean")
public class MapInfo {

    @DatabaseField(generatedId = true)
    private int mid;

    @DatabaseField(canBeNull = false)
    private String mmapname;

    @DatabaseField(canBeNull = false)
    private Date cjsj;

    @DatabaseField
    private boolean sfdqsy;

    public Date getCjsj() {
        return cjsj;
    }

    public void setCjsj(Date cjsj) {
        this.cjsj = cjsj;
    }

    public int getMid() {
        return mid;
    }

    public void setMid(int mid) {
        this.mid = mid;
    }

    public String getMmapname() {
        return mmapname;
    }

    public void setMmapname(String mmapname) {
        this.mmapname = mmapname;
    }

    public boolean isSfdqsy() {
        return sfdqsy;
    }


    public MapInfo() {
    }

    public MapInfo(Date cjsj, String mmapname, boolean sfdqsy) {
        this.cjsj = cjsj;
        this.mid = mid;
        this.mmapname = mmapname;
        this.sfdqsy = sfdqsy;
    }

    public void setSfdqsy(boolean sfdqsy) {
        this.sfdqsy = sfdqsy;
    }

    @Override
    public String toString() {
        return "MapInfo{" +
                "cjsj=" + cjsj +
                ", mid='" + mid + '\'' +
                ", mmapname='" + mmapname + '\'' +
                ", sfdqsy=" + sfdqsy +
                '}';
    }
}
