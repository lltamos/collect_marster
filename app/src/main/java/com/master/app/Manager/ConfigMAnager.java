package com.master.app.Manager;

import android.content.Context;

import com.master.app.tools.ContextUtils;
import com.master.app.tools.JsonUtils;
import com.master.bean.CodesInfo;
import com.master.bean.CodesList;
import com.master.bean.Fields;
import com.master.bean.FieldsList;
import com.master.bean.Table;
import com.master.bean.TableContext;
import com.master.bean.TableContextList;
import com.master.bean.TableList;

import java.util.List;

/**
 * @param
 * @author Litao-pc on 2016/11/30.
 *         ~
 */

public class ConfigMAnager {

    private static ConfigMAnager cfg;

    String json1 = "Catsic_Codes.json";
    String json2 = "Catsic_Fields.json";
    String json3 = "Catsic_Tables.json";
    String json4 = "Catsic_TablesContext.json";
    private List<CodesInfo> codeList;
    private List<Fields> fieldList;
    private List<Table> tableList;
    private List<TableContext> tablecontextList;

    private ConfigMAnager() {
    }


    public static synchronized ConfigMAnager create() {
        if (cfg == null) {
            cfg = new ConfigMAnager();
        }
        return cfg;
    }

    //初始化配置,只能执行一次
    public void initVariablesCfg(Context context) {
        codeList = JsonUtils.toObject(ContextUtils.loadJSONFromAsset(context, json1), CodesList.class).getContent();
        fieldList = JsonUtils.toObject(ContextUtils.loadJSONFromAsset(context, json2), FieldsList.class).getContent();
        tableList = JsonUtils.toObject(ContextUtils.loadJSONFromAsset(context, json3), TableList.class).getContent();
        tablecontextList = JsonUtils.toObject(ContextUtils.loadJSONFromAsset(context, json4), TableContextList.class).getContent();
    }

    public List<CodesInfo> getCodeList() {
        return codeList;
    }


    public List<Fields> getFieldList() {
        return fieldList;
    }


    public List<Table> getTableList() {
        return tableList;
    }


    public List<TableContext> getTablecontextList() {
        return tablecontextList;
    }


}
