package com.master.presenter;

import com.master.contract.MvpPresenter;
import com.master.model.MainModel;
import com.master.ui.viewer.MainVIew;

/**
 * @param
 * @author Litao-pc on 2016/10/27.
 *         ~
 */

public class MainPresenter extends MvpPresenter<MainVIew,MainModel> {

    public MainPresenter(MainModel mModel) {
        super(mModel);
    }

    public void addFormElements(String fname) {

    }

}
