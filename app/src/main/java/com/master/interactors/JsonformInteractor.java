package com.master.interactors;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.master.app.Constants;
import com.master.app.inter.CommonListener;
import com.master.app.view.EditTextFactory;
import com.master.app.view.FormWidgetFactory;
import com.master.bean.Fields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vijay on 5/19/15.
 */
public class JsonformInteractor {

    private static final String TAG = "JsonFormInteractor";
    private static final Map<String, FormWidgetFactory> map = new HashMap<>();
    private static final JsonformInteractor INSTANCE = new JsonformInteractor();

    private JsonformInteractor() {
        registerWidgets();
    }

    private void registerWidgets() {
        map.put(Constants.EDIT_TEXT, new EditTextFactory());
    }

    public List<View> fetchFormElements(Context context, List<Fields> parentJson, CommonListener listener) {
        Log.d(TAG, "fetchFormElements called");
        List<View> viewsFromJson = new ArrayList<>(5);
        try {

            for (int i = 0; i < parentJson.size(); i++) {
                Fields child = parentJson.get(i);

                try {
                    String type = child.getFcode().equals("") ? Constants.EDIT_TEXT : "";
                    List<View> views = map.get(type).getViewsFromJson(context, child, null);
                    if (views.size() > 0) {
                        viewsFromJson.addAll(views);
                    }
                } catch (Exception e) {
                    Log.d(TAG,
                            "构建view失败 : "
                                    + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            Log.d(TAG, " 构建view失败 : " + e.getMessage());
            e.printStackTrace();
        }
        return viewsFromJson;
    }

    public static JsonformInteractor getInstance() {
        return INSTANCE;
    }
}
