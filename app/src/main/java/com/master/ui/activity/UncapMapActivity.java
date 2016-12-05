package com.master.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.master.R;
import com.master.app.tools.ActionBarManager;
import com.master.app.weight.SearchBar;
import com.master.app.weight.SearchListView;
import com.master.contract.BaseActivity;
import com.master.ui.adapter.MapListAdapter;

import butterknife.BindView;

/**
 * @author Litao-pc on 2016/11/7.
 *         ~
 */
public class UncapMapActivity extends BaseActivity {
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
        title.setText("打开地图");

        SearchBar searchBar = new SearchBar(this);
        mListView.addHeaderView(searchBar);
        mListView.setOnItemClickListener((parent, view, position, id) -> {

            //点击事件执行


        });

        mListView.setAdapter(new MapListAdapter());
        mListView.setEnableRefresh(false);

    }

    @Override
    public int getLayoutResId() {
        return R.layout.map_list_activity;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add, menu);
        return true;
    }

    // 菜单项被选择事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.add:
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                break;
        }
        return false;
    }


}
