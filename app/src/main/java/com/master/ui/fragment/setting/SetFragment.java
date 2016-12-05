package com.master.ui.fragment.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.TextView;

import com.master.R;
import com.master.app.Constants;
import com.master.app.tools.CommonUtils;
import com.master.app.tools.PreferencesUtils;
import com.master.contract.BaseFragment;
import com.master.ui.activity.AboutActivity;
import com.master.ui.activity.AccountActivity;
import com.master.ui.activity.AdviceActivity;
import com.master.ui.activity.SettingActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author Litao-pc on 2016/10/27.
 *         ~
 */

public class SetFragment extends BaseFragment {
    @BindView(R.id.tv_collect)
    AppCompatTextView tv_collect;
    @BindView(R.id.sc_wake_lock)
    SwitchCompat scWakeLock;
    @BindView(R.id.title)
    TextView title;


    @Override
    public void bindView(Bundle savedInstanceState) {
        title.setText("设置");
        tv_collect.setOnClickListener(v -> {
            CommonUtils.toActivity(mContext, new Intent(mContext, SettingActivity.class));
        });
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_set;
    }


    @OnClick({R.id.sc_wake_lock, R.id.atv_advice, R.id.atv_account, R.id.atv_about})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sc_wake_lock:
                boolean scWakeLockChecked = scWakeLock.isChecked();

                if (PreferencesUtils.putBoolean(mContext, Constants.SC_WAKE_LOCK, scWakeLockChecked)) {
                    if (scWakeLockChecked) {
                        CommonUtils.toggleWalkLook(mContext, true);
                    } else {
                        CommonUtils.toggleWalkLook(mContext, false);
                    }
                }
                break;
            case R.id.atv_advice:
                CommonUtils.toActivity(mContext, new Intent(mContext, AdviceActivity.class));
                break;
            case R.id.atv_account:
                CommonUtils.toActivity(mContext, new Intent(mContext, AccountActivity.class));
                break;
            case R.id.atv_about:
                CommonUtils.toActivity(mContext, new Intent(mContext, AboutActivity.class));
                break;
        }
    }
}
