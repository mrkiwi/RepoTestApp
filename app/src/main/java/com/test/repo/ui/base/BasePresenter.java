package com.test.repo.ui.base;

import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import com.test.repo.ui.base.mvp.MvpPresenter;
import com.test.repo.ui.base.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private final CompositeDisposable disposables = new CompositeDisposable();

    private V mvpView;

    @CallSuper
    @Override
    public void attachView(final V view) {
        mvpView = view;
    }

    @Override
    public final V getView() {
        return mvpView;
    }

    protected void onMvpViewAttach(final boolean isFirstTime) { }

    @Override
    public final boolean isViewAttached() {
        return mvpView != null;
    }

    @Override
    public final void addDisposable(@NonNull final Disposable disposable) {
        disposables.add(disposable);
    }


    @Override
    public void removeDisposable(@NonNull final Disposable subscription) {
        disposables.remove(subscription);
    }

    @Override
    public void detachView() {
        mvpView = null;
    }

    @Override
    public void destroy() {
        disposables.clear();
    }
}