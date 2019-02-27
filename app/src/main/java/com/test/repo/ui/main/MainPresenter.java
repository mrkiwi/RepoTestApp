package com.test.repo.ui.main;

import android.content.Intent;

import com.test.repo.data.local.Constants;
import com.test.repo.data.network.model.RepositoryModel;
import com.test.repo.data.network.repository.MainRepository;
import com.test.repo.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter extends BasePresenter<MainView> {
	private MainRepository mMainRepository;

	@Inject
	public MainPresenter(final MainRepository _mainRepository) {
		this.mMainRepository = _mainRepository;
	}

	void checkIntent(Intent intent) {
		if(intent.hasExtra(Constants.ARG_REPOSITORY)) {
			RepositoryModel repositoryModel = intent.getParcelableExtra(Constants.ARG_REPOSITORY);
			getView().setRepositoryData(repositoryModel.getName(), repositoryModel.getDescription());
			getView().setReleases(repositoryModel.getReleases());
		}
	}
}
