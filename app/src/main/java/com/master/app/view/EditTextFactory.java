package com.master.app.view;

import android.content.Context;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;

import com.master.R;
import com.master.app.inter.CommonListener;
import com.master.bean.Fields;
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
    public List<View> getViewsFromJson(Context context, Fields fields, CommonListener listener) throws Exception {
        int minLength = MIN_LENGTH;
        int maxLength = MAX_LENGTH;
        List<View> views = new ArrayList<>(1);

        MaterialEditText editText = (MaterialEditText) LayoutInflater.from(context).inflate(
                R.layout.item_edit_text, null);
        InputFilter[] filters = {new InputFilter.LengthFilter(20)};
        editText.setHint("输入" + fields.getFNameCHS());
        editText.setFloatingLabelText(fields.getFNameCHS());
        editText.setSingleLine();
        editText.setFilters(filters);
        editText.setId(ViewUtil.generateViewId());
        editText.setMaxCharacters(maxLength);
        editText.setMinCharacters(minLength);
        views.add(editText);
        return views;
    }


}
