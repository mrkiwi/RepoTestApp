package com.test.repo.ui.start;

import com.test.repo.data.local.Constants;
import com.test.repo.data.network.repository.MainRepository;
import com.test.repo.ui.base.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class StartPresenter extends BasePresenter<StartView> {

	private MainRepository mMainRepository;

	@Inject
	public StartPresenter(final MainRepository _mainRepository) {
		this.mMainRepository = _mainRepository;
	}

	public void validate(String name, String owner) {
		getView().showProgress();
		if(Constants.AUTH_TOKEN.equals("")) {
			getView().showMessage("Invalid auth token");
			return;
		}
		if(name.isEmpty() || owner.isEmpty()) {
			getView().showError();
			return;
		}
		addDisposable(mMainRepository.getRepository(20, owner, name)
				.observeOn(AndroidSchedulers.mainThread())
				.subscribeOn(Schedulers.io())
				.subscribe(repositoryModel -> {
					if(repositoryModel != null) {
						getView().loadRepository(repositoryModel);
						getView().hideProgress();
					} else {
						getView().showError();
					}
				}, throwable -> getView().showError()));
	}
}
