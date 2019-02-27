package com.test.repo.ui.start;

import com.test.repo.data.network.model.RepositoryModel;
import com.test.repo.ui.base.mvp.MvpView;

public interface StartView extends MvpView<StartPresenter> {
	void loadRepository(RepositoryModel repositoryModel);
	void showError();
	void showAuthTokenError();
	void showProgress();
	void hideProgress();
}
