package com.master.app.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.master.R;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rey.material.util.ViewUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vijay on 24-05-2015.
 */
public class EditTextFactory implements FormWidgetFactory {

    public static final int MIN_LENGTH = 0;
    public static final int MAX_LENGTH = 100;

    /**
     * @param stepName
     * @param context
     * @param jsonObject
     * @param listener
     * @return
     * @throws Exception
     */
    @Override
    public List<View> getViewsFromJson(String stepName, Context context, String hintstr, CommonListener listener) throws Exception {
        int minLength = MIN_LENGTH;
        int maxLength = MAX_LENGTH;
        List<View> views = new ArrayList<>(1);
        MaterialEditText editText = (MaterialEditText) LayoutInflater.from(context).inflate(
                R.layout.item_edit_text, null);
        editText.setHint("输入名字");
        editText.setFloatingLabelText(hintstr+"xnuahxa");
        editText.setId(ViewUtil.generateViewId());
        editText.setMaxCharacters(maxLength);
        editText.setMinCharacters(minLength);
        views.add(editText);
        return views;
    }


}
