package com.master.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.master.R;
import com.master.app.SynopsisObj;
import com.master.app.tools.ActionBarManager;
import com.master.app.weight.SearchBar;
import com.master.app.weight.SearchListView;
import com.master.contract.BaseActivity;

import butterknife.BindView;

public class PropertyListActivity extends BaseActivity {

    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.listView)
    SearchListView mListView;


    @Override
    public void bindView(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        ActionBarManager.initBackTitle(getSupportActionBar());
        String titleName = getIntent().getStringExtra("title");
        this.title.setText(titleName);
        SearchBar searchBar = new SearchBar(this);
        mListView.addHeaderView(searchBar);
        View list_item = View.inflate(this, R.layout.list_item_2, null);
        mListView.addHeaderView(list_item);
        mListView.setAdapter(new PropertyAdapter());
        mListView.setEnableRefresh(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_property_list;
    }

    private class PropertyAdapter extends BaseAdapter {


        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View v = View.inflate(SynopsisObj.getAppContext(), R.layout.ac_property_item, null);

           v.findViewById(R.id.ctv_1).setOnClickListener(v12 -> {
               Toast.makeText(PropertyListActivity.this, "v12", Toast.LENGTH_SHORT).show();
           });
            v.findViewById(R.id.ctv_2).setOnClickListener(v1 -> {
                Toast.makeText(PropertyListActivity.this, "v11", Toast.LENGTH_SHORT).show();
            });
            return v;
        }


    }

}
