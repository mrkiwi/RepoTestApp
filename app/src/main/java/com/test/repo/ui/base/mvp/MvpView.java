package com.test.repo.ui.base.mvp;

import android.support.annotation.LayoutRes;

public interface MvpView<P extends MvpPresenter> {
    @LayoutRes
    int getLayoutId();
    P getPresenter();

    void showMessage(String message);
}
