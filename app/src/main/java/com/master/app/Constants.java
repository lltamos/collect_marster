package com.master.app;

/**
 * Created by Litao-pc on 2016/9/12.
 */

public class Constants {
    public static final boolean S_IS_DEBUG = true;
    /**
     * @param :COLLECT_METHED 采集方式
     * 0 按时间采集
     * 1 按距离采集   default
     */
    public static final int COLLECT_METHED = 0;


    //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


    /**
     * shareprefrence 持久化配置
     *
     * @param :持久化文件名
     */
    public static final String CONFIG = "config";


    public static final String SELECT_COLLECT_TAG = "cellect";


    public static final String SELECT_COLLECT_LABE = "select_tab";

    //屏幕常亮
    public static final String SC_WAKE_LOCK = "SC_WAKE_LOCK";

    public static String ConfigSqliteDB = "config.sqlite";


    public static final String FIRST_STEP_NAME = "step1";
    public static final String EDIT_TEXT = "edit_text";
    public static final String CHECK_BOX = "check_box";
    public static final String RADIO_BUTTON = "radio";
    public static final String LABEL = "label";
    public static final String CHOOSE_IMAGE = "choose_image";
    public static final String OPTIONS_FIELD_NAME = "options";
    public static final String SPINNER = "spinner";

    public static final String CURRENT_USER_MAP = "CURRENT_USER_MAP";

    public static final int STATUS_DEFAULT_NUM = -1;
}
