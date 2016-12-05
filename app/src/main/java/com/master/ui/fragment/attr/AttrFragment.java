package com.master.ui.fragment.attr;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.master.R;
import com.master.app.tools.CommonUtils;
import com.master.app.weight.CustomTextView;
import com.master.contract.BaseFragment;
import com.master.ui.activity.PropertyListActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.master.R.id.ct_roadAttr;

/**
 * @author Litao-pc on 2016/10/27.
 *         ~
 */

public class AttrFragment extends BaseFragment {


    @BindView(R.id.ct_workAttr)
    CustomTextView ctWorkAttr;
    @BindView(R.id.ct_roadAttr)
    CustomTextView ctRoadAttr;

    @Override
    public void bindView(Bundle savedInstanceState) {
        toggleColor(true);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_attr;
    }


    void toggleColor(boolean tag) {
        if (tag) {
            ctWorkAttr.setSolidColor(Color.parseColor("#adadad"));
            ctRoadAttr.setSolidColor(Color.parseColor("#ffffff"));
        } else {
            ctRoadAttr.setSolidColor(Color.parseColor("#adadad"));
            ctWorkAttr.setSolidColor(Color.parseColor("#ffffff"));
        }
    }


    @OnClick({R.id.ct_roadAttr, R.id.ct_workAttr, R.id.tv_1, R.id.tv_2, R.id.tv_3, R.id.tv_4, R.id.tv_5, R.id.tv_6, R.id.tv_7, R.id.tv_8})
    public void onClick(View view) {
        switch (view.getId()) {
            case ct_roadAttr:
                toggleColor(true);
                break;
            case R.id.ct_workAttr:
                toggleColor(false);
                break;
            case R.id.tv_1:
            case R.id.tv_2:
            case R.id.tv_3:
            case R.id.tv_4:
                String s = ((AppCompatTextView) view).getText().toString();
                Intent in = new Intent(mContext, PropertyListActivity.class);
                in.putExtra("title", s);
                CommonUtils.toActivity(mContext, in);
                break;
            case R.id.tv_6:
                break;
            case R.id.tv_7:
            case R.id.tv_8:

        }
    }
}
