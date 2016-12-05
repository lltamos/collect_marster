package com.master.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.master.R;
import com.master.app.SynopsisObj;

/**
 * Created by hign on 2016/11/20.
 */

public class MapListAdapter extends BaseAdapter {
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
        View v=View.inflate(SynopsisObj.getAppContext(), R.layout.map_list_item,null);
        return v;
    }
}
