package com.test.repo.ui.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.test.repo.ui.base.mvp.MvpView;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<P extends BasePresenter> extends DaggerAppCompatActivity implements MvpView<P> {

    @Inject
    P presenter;

    private Unbinder unbinder;
    private View mView;

    @Override
    @CallSuper
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupButterKnife();
        if (presenter != null) {
            presenter.attachView(this);
            presenter.onMvpViewAttach(savedInstanceState == null);
        }
        initUI(savedInstanceState);
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    private void setupButterKnife() {
        if (getLayoutId() == - 1) return;
        mView = LayoutInflater.from(this).inflate(getLayoutId(), null);
        setContentView(mView);
        unbinder = ButterKnife.bind(this);
    }

    @CallSuper
    @Override
    protected void onStart() {
        super.onStart();
    }

    @CallSuper
    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @CallSuper
    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            presenter.attachView(this);
        }
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            if (isFinishing()) presenter.destroy();
        }
        if (unbinder != null)
            unbinder.unbind();
    }

    @Override
    public void showMessage(String message) {
        Snackbar snack = Snackbar.make(mView, message, Snackbar.LENGTH_LONG);
        View view = snack.getView();
        TextView tv = view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snack.show();
    }

    protected abstract void initUI(Bundle savedInstanceState);

    public void hideKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null && inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), InputMethodManager.RESULT_UNCHANGED_SHOWN);
        }
    }
}