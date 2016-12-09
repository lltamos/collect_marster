package com.master.app.view;

import android.content.Context;
import android.view.View;

import com.master.app.inter.CommonListener;
import com.master.bean.Fields;

import java.util.List;

/**
 * Created by vijay on 24-05-2015.
 */
public interface FormWidgetFactory {
    List<View> getViewsFromJson(Context context, Fields fields, CommonListener listener) throws Exception;
}
