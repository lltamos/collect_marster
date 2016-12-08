package com.master.app.view;

import android.content.Context;
import android.view.View;

import java.util.List;

/**
 * Created by vijay on 24-05-2015.
 */
public interface FormWidgetFactory {
    List<View> getViewsFromJson(String stepName, Context context, String hintstr, CommonListener listener) throws Exception;
}
