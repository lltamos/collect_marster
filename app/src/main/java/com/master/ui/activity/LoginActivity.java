package com.master.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.master.R;
import com.master.app.Constants;
import com.master.app.SynopsisObj;
import com.master.app.tools.ClipboardUtils;
import com.master.app.tools.CommonUtils;
import com.master.app.tools.LoggerUtils;
import com.master.app.tools.ObjectUtils;
import com.master.app.tools.PreferencesUtils;
import com.master.contract.BaseActivity;

import butterknife.BindView;

/**
 * A login screen that offers login organiz /password.
 */
public class LoginActivity extends BaseActivity {

    private String mOrignaz = "";
    private String mCaptcha = "";
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_submit)
    AppCompatButton btnSubmit;
    @BindView(R.id.tv_captcha)
    TextView tvCaptcha;
    @BindView(R.id.iv_copy)
    ImageView ivCopy;


    @Override
    public void bindView(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        ivCopy.setOnClickListener(v -> {
            copyMsgStr(this, tvCaptcha.getText().toString());
        });
        btnSubmit.setOnClickListener(v -> {
            attemptLogin(mOrignaz, mOrignaz);
        });


        if (PreferencesUtils.getBoolean(this, Constants.SC_WAKE_LOCK, false)) {
            CommonUtils.toggleWalkLook(this, true);
        }

    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }


    public void startMainView() {
        CommonUtils.toActivity(this, new Intent(this, MainActivity.class));
//        finish();
    }

    public void showMsgTitle(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    public void copyMsgStr(Context c, CharSequence text) {
        if (ObjectUtils.isNullOrEmptyString(text) || c == null) return;
        ClipboardUtils.copyText(c, text);
        showMsgTitle(c.getString(R.string.titlemsg));
    }


    public void attemptLogin(String mOrignaz, String mCapcha) {
        if (attemptLoginMsg(mOrignaz, mCapcha)) {
            startMainView();
        } else {
            Toast.makeText(SynopsisObj.getAppContext(), "请输入合法验证码", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            LoggerUtils.d(sClassName, "onKeyDown");
            finish();
        }

        return false;

    }

    public boolean attemptLoginMsg(String organiz, String captcha) {
        return true;
    }
}




