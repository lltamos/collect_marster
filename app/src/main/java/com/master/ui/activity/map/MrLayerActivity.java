package com.master.ui.activity.map;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.master.R;
import com.master.app.tools.ActionBarManager;
import com.master.app.weight.SearchListView;
import com.master.contract.BaseActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class MrLayerActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listview)
    SearchListView mListView;



    @Override
    public void bindView(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        ActionBarManager.initBackTitle(getSupportActionBar());
        title.setText("图层管理");
        Bundle bundle = this.getIntent().getExtras();
        ArrayList maplayerinfo = bundle.getStringArrayList("maplayerinfo");
        setContentView(R.layout.activity_maplayerinfo);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_maplayerinfo;
    }
}
