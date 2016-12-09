package com.master.presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.collect_master.model.MainModelImpl;
import com.master.bean.Fields;
import com.master.contract.MvpPresenter;
import com.master.interactors.JsonformInteractor;
import com.master.model.MainModel;
import com.master.ui.viewer.MainVIew;

import java.util.List;

/**
 * @param
 * @author Litao-pc on 2016/10/27.
 *         ~
 */

public class MainPresenter extends MvpPresenter<MainVIew, MainModel> {

    public MainPresenter(MainModelImpl mModel) {
        super(mModel);
    }


    public List<Fields> getFieldsParam(String tname, @NonNull List<Fields> list) {
        return mModel.getArgsParms(tname, list);
    }

    public void builderSheetElement(Context c, List<Fields> fieldsList) {
        List<View> views = JsonformInteractor.getInstance().fetchFormElements(c, fieldsList, null);
        getView().addFormElements(views);
    }

    public void parseValue(List<View> views) {
        mModel.parseViewValue(views);
    }
}
