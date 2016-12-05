package com.master.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.widget.ImageView;

import com.master.R;
import com.master.app.tools.GPSUtils;
import com.master.app.weight.APSTSViewPager;
import com.master.app.weight.AdvancedPagerSlidingTabStrip;
import com.master.contract.MvpPresenter;
import com.master.model.MainModel;
import com.master.presenter.MainPresenter;
import com.master.ui.adapter.TabsAdapter;
import com.master.ui.viewer.MainVIew;

import butterknife.BindView;

public class MainActivity extends MvpActivity implements MainVIew {


    @BindView(R.id.tabs)
    AdvancedPagerSlidingTabStrip tabs;

    @BindView(R.id.iv_Btn)
    ImageView ivBtn;

    @BindView(R.id.viewPager)
    APSTSViewPager viewPager;

    private BottomSheetDialog sheet;

    @Override
    protected MvpPresenter createPresenter() {

        return new MainPresenter(new MainModel());

    }

    @Override
    public void bindView(Bundle savedInstanceState) {
        viewPager.setAdapter(new TabsAdapter(getSupportFragmentManager(), this));
        tabs.setViewPager(viewPager);
        viewPager.setOffscreenPageLimit(5);
        sheet = new BottomSheetDialog(this);
        ivBtn.setOnClickListener(v ->
                showExtra()
        );

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
}
