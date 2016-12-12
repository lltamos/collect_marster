package com.master.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.master.R;
import com.master.app.inter.CommonListener;
import com.master.app.orm.DbHelperDbHelper;
import com.master.app.tools.AppManager;
import com.master.app.tools.GPSUtils;
import com.master.app.tools.ObjectUtils;
import com.master.app.view.JsonApi;
import com.master.app.weight.APSTSViewPager;
import com.master.app.weight.AdvancedPagerSlidingTabStrip;
import com.master.bean.Fields;
import com.master.model.MainModelImpl;
import com.master.presenter.MainPresenter;
import com.master.ui.adapter.TabsAdapter;
import com.master.ui.viewer.MainVIew;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class MainActivity extends MvpActivity<MainPresenter> implements MainVIew, JsonApi, CommonListener {

    public static MainActivity S_MainActivity;

    public static String tname;
    @BindView(R.id.tabs)
    AdvancedPagerSlidingTabStrip tabs;

    @BindView(R.id.iv_Btn)
    ImageView ivBtn;

    @BindView(R.id.viewPager)
    APSTSViewPager viewPager;

    @BindView(R.id.ll_container)
    LinearLayout llContainer;

    private BottomSheetDialog sheet;
    private List<View> views = new ArrayList<>();
    @BindView(R.id.save)
    Button save;
    private BottomSheetBehavior behavior;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(new MainModelImpl());

    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        S_MainActivity = this;
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), this));
        tabs.setViewPager(viewPager);
        viewPager.setOffscreenPageLimit(5);
        sheet = new BottomSheetDialog(this);
        save.setOnClickListener(this);
        ivBtn.setOnClickListener(this);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!GPSUtils.isOpenGPSsetting(this)) GPSUtils.openGPSSettings(this);
    }

    @Override
    public void showExtra() {
        sheet.setContentView(R.layout.mainact_item_dialog);
        sheet.show();
    }

    @Override
    public void hideExtra() {
        sheet.dismiss();
    }


    @Override
    public void addFormElements(List<View> views) {

        llContainer.removeAllViews();
        for (View view : views) {
            llContainer.addView(view);
        }
        this.views.clear();
        this.views.addAll(views);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public List<Fields> getArguments(String fname, List<Fields> list) {
        return presenter.getFieldsParam(fname, list);
    }

    @Override
    public JSONObject getStep(String stepName) {
        return null;
    }

    @Override
    public void writeValue(String tName, int key) {
        if (!AppManager.checkWorkMap()) {
            showToast("请先设置工作地图");
            return;
        }
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < list.size(); i++) {
            //因为属性表中主键自动增长，我们需要跳过主键跳过主键

            String tag = (String) list.get(i).getTag(key);
            if (ObjectUtils.isNullOrEmptyString(tag)) continue;
            if (list.get(i) instanceof EditText) {
                String s = ((EditText) list.get(i)).getText().toString();
                if (ObjectUtils.isNullOrEmptyString(s)) {
                    showToast("录入信息不完整...");
                    return;
                }
                map.put(tag, s);
            }
        }

        DbHelperDbHelper.open().addTablEntry(tname, map);

//        StringBuffer sql = new StringBuffer().append("insert into " + tname + " (");
//        for (int i = 0; i < list.size(); i++) {
//            String fname = (String) list.get(i).getTag(R.id.fname);
//            if (i == list.size() - 1) {
//                sql.append(" " + fname + ") ");
//            } else
//                sql.append(" " + fname + ",");
//        }
//        LoggerUtils.d(sClassName, sql.toString());
//        sql.append(" " + "values" + "(");
//        for (int i = 0; i < list.size(); i++) {
//            View temp = list.get(i);
//            if (i == list.size() - 1) {
//                if (temp instanceof EditText) {
//                    String s = ((EditText) temp).getText().toString();
//                    if (ObjectUtils.isNullOrEmptyString(s)) {
//                        showToast("录入信息不完整...");
//                        return;
//                    }
//                    sql.append(s + " );");
//                }
//            } else {
//                if (temp instanceof EditText) {
//                    String s = ((EditText) temp).getText().toString();
//                    if (ObjectUtils.isNullOrEmptyString(s)) {
//                        showToast("录入信息不完整...");
//                        return;
//                    }
//                    sql.append(s + ", ");
//                }
//            }
//            LoggerUtils.d(sClassName, sql.toString());
//        }

    }


    @Override
    public String currentJsonState() {
        return null;
    }

    @Override
    public String getCount() {
        return null;
    }

    @Override
    public void onClick(View v) {
        if (v == ivBtn) {
            showExtra();
        }
        if (v == save) {
            writeValue(tname, R.id.fname);
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String sInfo = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        String sInfo = "noll thing！";
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        String Type = (String) buttonView.getTag(R.id.key);
    }

    public void changetabs(int item) {
        viewPager.setCurrentItem(item);
    }

    private List<View> list;

    public void builderSheet(List<Fields> fieldsList) {
        list = presenter.builderSheetElement(this, fieldsList);
    }

    public void show(View v) {
        behavior = BottomSheetBehavior.from(v);
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }
}
