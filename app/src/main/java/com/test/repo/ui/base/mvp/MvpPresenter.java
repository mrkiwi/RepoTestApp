package com.test.repo.ui.base.mvp;

import android.support.annotation.NonNull;

import io.reactivex.disposables.Disposable;

public interface MvpPresenter<V extends MvpView> {

    void attachView(final V view);

    V getView();

    boolean isViewAttached();

    void addDisposable(@NonNull final Disposable subscription);

    void removeDisposable(@NonNull final Disposable subscription);

    void detachView();

    void destroy();
}
