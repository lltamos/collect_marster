package com.master.ui.activity.map;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.master.R;
import com.master.app.Constants;
import com.master.app.SynopsisObj;
import com.master.app.tools.ActionBarManager;
import com.master.app.weight.SearchBar;
import com.master.app.weight.SearchListView;
import com.master.bean.Table;
import com.master.contract.BaseActivity;

import butterknife.BindView;

public class CollectListActivity extends BaseActivity {
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
        Bundle bundle = getIntent().getExtras();
        Table o = (Table) bundle.get(Constants.SELECT_COLLECT_LABE);
        assert o != null;
        title.setText(o.getTNameCHS());
        SearchBar searchBar = new SearchBar(this);
        mListView.addHeaderView(searchBar);
        View list_item = View.inflate(this, R.layout.list_item_1, null);
        mListView.addHeaderView(list_item);
        mListView.setAdapter(new CollectAdapter());
        mListView.setEnableRefresh(false);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_collect_list;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    private class CollectAdapter extends BaseAdapter {


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
            View v = View.inflate(SynopsisObj.getAppContext(), R.layout.ac_collect_item, null);
            return v;
        }
    }


}
