package com.master.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.master.R;
import com.master.app.tools.ActionBarManager;
import com.master.app.weight.SearchBar;
import com.master.app.weight.SearchListView;
import com.master.contract.BaseActivity;
import com.master.ui.adapter.MapListAdapter;

import butterknife.BindView;

public class ExportListActivity extends BaseActivity {
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
        title.setText("导出地图");
        SearchBar searchBar = new SearchBar(this);
        mListView.addHeaderView(searchBar);
        TextView textView = new TextView(this);
        textView.setText("注：地图导出后，将生成JSON文件，存储在**路径");
        textView.setTextColor(Color.RED);
        textView.setPadding(32, 0, 0, 0);
        mListView.addHeaderView(textView);
        mListView.setAdapter(new MapListAdapter());
        mListView.setEnableRefresh(false);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_export_list;
    }
}
