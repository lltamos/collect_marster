package com.master.app.weight;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.master.R;
import com.master.app.Constants;
import com.master.app.Manager.AcquisitionPara;
import com.master.app.tools.AppManager;
import com.master.app.tools.CommonUtils;
import com.master.bean.Table;
import com.master.ui.activity.map.CollectListActivity;
import com.master.ui.activity.MainActivity;

import java.util.List;

/**
 * @param
 * @author Litao-pc on 2016/11/16.
 *         ~
 */

public class LableGradView extends GridView {

    private List<Table> dataList;
    private Context context;
    private int datasId;

    public LableGradView(Context context) {
        this(context, null);
    }


    public LableGradView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (attrs != null) {
            TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.lableGride);
            try {
                datasId = a.getResourceId(R.styleable.lableGride_datas, 0);

            } finally {

                a.recycle();
            }
        }
        this.context = context;
        if (datasId == R.array.m_line_label) {
            dataList = AcquisitionPara.getLineList();
        } else {
            dataList = AcquisitionPara.getPointList();
        }

        init();

    }

    private void init() {
//        setLayoutAnimation(getAnimationController());

        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return dataList.size();
            }

            @Override
            public Object getItem(int i) {
                return dataList.get(i);
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                CustomTextView lable;
                if (view == null) {
                    view = View.inflate(context, R.layout.label, null);
                    lable = (CustomTextView) view.findViewById(R.id.ctv_lable);
                    view.setTag(lable);
                } else {
                    lable = (CustomTextView) view.getTag();
                }

                lable.setOnClickListener(view1 -> {
                    if (datasId == R.array.m_line_label) {
                        ((MainActivity) AppManager.getAppManager().currentActivity()).hideExtra();
                        Intent intent = new Intent(AppManager.getAppManager().currentActivity(), CollectListActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable(Constants.SELECT_COLLECT_LABE, dataList.get(i));
                        intent.putExtras(b);
                        CommonUtils.toActivity(AppManager.getAppManager().currentActivity(), intent);
                    }
                    if (datasId == R.array.m_point_label) {
                        ((MainActivity) AppManager.getAppManager().currentActivity()).hideExtra();
                        BottomSheetBehavior behavior = BottomSheetBehavior.from(AppManager.getAppManager().currentActivity().findViewById(R.id.main_scroll));
                        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        } else {
                            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                    }
                });
                lable.setText(dataList.get(i).getTNameCHS());
                return view;
            }
        };
        setAdapter(adapter);
    }

    protected LayoutAnimationController getAnimationController() {
        int duration = 60;
        AnimationSet set = new AnimationSet(true);
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(duration);
        set.addAnimation(animation);

        animation = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        animation.setDuration(duration);
        set.addAnimation(animation);

        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_NORMAL);
        return controller;
    }


    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}
