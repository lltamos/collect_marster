package com.master.app.Manager;

import com.master.bean.Fields;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title:${type_inName}<p/>
 * <p>Description:<p/>
 * <p>Company: </p>
 *
 * @author litao
 * @mail llsmpsvn@gmail.com
 * @date on 2016/12/5
 */
public class FieldWordsDirs {

    private static FieldWordsDirs f;
    private Map<String, Fields> map = new HashMap<>();

    public synchronized static FieldWordsDirs get() {
        if (f == null) {
            f = new FieldWordsDirs();
            f.initGenerateDict();
        }
        return f;
    }

    private FieldWordsDirs() {
    }

    //创建字段词典
    private Map<String, Fields> initGenerateDict() {
        map.clear();
        List<Fields> list = ConfigMAnager.create().getFieldList();
        for (Fields f : list) {
            String fName = f.getFName();
            map.put(fName, f);
        }
        return map;
    }

    public Fields fieldsFormFName(String fname) {
        return map.get(fname);
    }

}
