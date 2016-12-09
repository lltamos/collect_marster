package com.master.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.collect_master.model.MainModelImpl;
import com.master.R;
import com.master.app.inter.CommonListener;
import com.master.app.tools.GPSUtils;
import com.master.app.view.JsonApi;
import com.master.app.weight.APSTSViewPager;
import com.master.app.weight.AdvancedPagerSlidingTabStrip;
import com.master.bean.Fields;
import com.master.presenter.MainPresenter;
import com.master.ui.adapter.TabsAdapter;
import com.master.ui.viewer.MainVIew;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends MvpActivity<MainPresenter> implements MainVIew, JsonApi, CommonListener {

    public static MainActivity S_MainActivity;

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
    public void writeValue(String stepName, String key, String value) throws JSONException {

    }

    @Override
    public void writeValue(String stepName, String prentKey, String childObjectKey, String childKey, String value) throws JSONException {

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
            Toast.makeText(this, "xxx", Toast.LENGTH_SHORT).show();
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


    public void builderSheet(List<Fields> fieldsList) {
        presenter.builderSheetElement(this, fieldsList);
    }

    public void show(View v) {
        BottomSheetBehavior behavior = BottomSheetBehavior.from(v);
        if (behavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        } else {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
    }
}
