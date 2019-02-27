package com.test.repo.ui.start;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.LinearLayout;

import com.test.repo.R;
import com.test.repo.data.network.model.RepositoryModel;
import com.test.repo.ui.base.BaseActivity;
import com.test.repo.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class StartActivity extends BaseActivity<StartPresenter> implements StartView {

	@BindView(R.id.et_name) protected AppCompatEditText etName;
	@BindView(R.id.et_owner) protected AppCompatEditText etOwner;
	@BindView(R.id.ll_progressHolder) protected LinearLayout llProgressHolder;

	@Override
	protected void initUI(Bundle savedInstanceState) {

	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_start;
	}

	@OnClick(R.id.btn_load)
	public void onButtonLoadClicked() {
		hideKeyboard();
		getPresenter().validate(etName.getText().toString(), etOwner.getText().toString());
	}

	@Override
	public void loadRepository(RepositoryModel repositoryModel) {
		hideKeyboard();
		startActivity(MainActivity.launch(this, repositoryModel));
	}

	@Override
	public void showError() {
		hideProgress();
		showMessage("Something goes wrong. Check data");
	}

	@Override
	public void showAuthTokenError() {
		hideProgress();
		showMessage("Invalid auth token");
	}

	@Override
	public void showProgress() {
		llProgressHolder.setVisibility(View.VISIBLE);
	}

	@Override
	public void hideProgress() {
		llProgressHolder.setVisibility(View.GONE);
	}
}
