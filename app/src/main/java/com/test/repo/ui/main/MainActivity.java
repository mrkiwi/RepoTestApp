package com.test.repo.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.test.repo.R;
import com.test.repo.data.local.Constants;
import com.test.repo.data.network.model.ReleaseModel;
import com.test.repo.data.network.model.RepositoryModel;
import com.test.repo.ui.base.BaseActivity;
import com.test.repo.ui.main.adapter.ReleaseAdapter;

import java.util.ArrayList;

import butterknife.BindView;


public class MainActivity extends BaseActivity<MainPresenter> implements MainView  {

	public static Intent launch(Context context, RepositoryModel repositoryModel) {
		Intent intent = new Intent(context, MainActivity.class);
		Bundle bundle = new Bundle();
		bundle.putParcelable(Constants.ARG_REPOSITORY, repositoryModel);
		intent.putExtras(bundle);
		return intent;
	}

	@BindView(R.id.tv_name) protected AppCompatTextView tvName;
	@BindView(R.id.tv_descr) protected AppCompatTextView tvDescr;
	@BindView(R.id.rv_list) protected RecyclerView rvList;

	private ReleaseAdapter mAdapter;

	@Override
	protected void initUI(Bundle savedInstanceState) {
		rvList.setLayoutManager(new LinearLayoutManager(this));
		mAdapter = new ReleaseAdapter(R.layout.item_release, null);
		rvList.setAdapter(mAdapter);
		getPresenter().checkIntent(getIntent());
	}

	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void setRepositoryData(String name, String descr) {
		tvName.setText(name);
		tvDescr.setText(descr);
	}

	@Override
	public void setReleases(ArrayList<ReleaseModel> list) {
		mAdapter.setNewData(list);
	}
}
