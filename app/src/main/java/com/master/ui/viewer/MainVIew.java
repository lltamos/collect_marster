package com.master.ui.viewer;

import android.view.View;

import com.master.contract.MvpView;

import java.util.List;

/**
 * @param
 * @author Litao-pc on 2016/10/27.
 *         ~
 */

public interface MainVIew extends MvpView {

    void showExtra();

    void hideExtra();

    void addFormElements(List<View> views);

    void showToast(String message);
}
