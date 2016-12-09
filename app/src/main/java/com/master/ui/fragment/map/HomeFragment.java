package com.master.ui.fragment.map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.master.R;
import com.master.app.tools.CommonUtils;
import com.master.contract.BaseFragment;
import com.master.ui.activity.FtpManagerActivity;
import com.master.ui.activity.map.NewMapActivity;
import com.master.ui.activity.map.OptionsWorkActivity;
import com.master.ui.activity.map.UncapMapActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Litao-pc on 2016/10/27.
 *         ~
 */

public class HomeFragment extends BaseFragment {
    @BindView(R.id.title)
    TextView title;

    @Override
    public void bindView(Bundle savedInstanceState) {
        title.setText("地图");
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragmeng_home;
    }



    @OnClick({R.id.atv1, R.id.atv2, R.id.atv3, R.id.atv4,R.id.atv5,R.id.act_export})
    public void onClick(View view) {
        Intent intent= new Intent(mContext, UncapMapActivity.class);
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.atv1:
                CommonUtils.toActivity(mContext,new Intent(mContext, OptionsWorkActivity.class));
                break;
            case R.id.atv2:
                CommonUtils.toActivity(mContext,new Intent(mContext, NewMapActivity.class));
                break;
            case R.id.atv3:
                bundle.putString("maptype", "bg");
                intent.putExtras(bundle);
                CommonUtils.toActivity(mContext,intent);
                break;
            case R.id.atv4:
                bundle.putString("maptype", "gl");
                intent.putExtras(bundle);
                CommonUtils.toActivity(mContext,intent);
                break;
            case R.id.atv5:
                bundle.putString("maptype", "online");
                intent.putExtras(bundle);
                CommonUtils.toActivity(mContext,intent);
                break;
            case R.id.act_export:
                CommonUtils.toActivity(mContext,new Intent(mContext, FtpManagerActivity.class));
                break;
        }
    }
}
