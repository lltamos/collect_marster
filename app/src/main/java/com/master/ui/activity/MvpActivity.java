package com.master.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.master.contract.BaseActivity;
import com.master.contract.MvpPresenter;


/**
 * Created by Litao-pc on 2016/9/7.
 */
public abstract class MvpActivity<P extends MvpPresenter> extends BaseActivity {
    protected P presenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        if (presenter == null) {
            presenter = createPresenter();
        }
        presenter.attachView(this);

        super.onCreate(savedInstanceState);
    }

    protected abstract P createPresenter();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView(false);
    }

    @Override
    public View getRootView() {
        return mRootView;
    }


}
