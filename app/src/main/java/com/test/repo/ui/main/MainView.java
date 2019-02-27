package com.test.repo.ui.main;

import com.test.repo.data.network.model.ReleaseModel;
import com.test.repo.ui.base.mvp.MvpView;

import java.util.ArrayList;

public interface MainView extends MvpView<MainPresenter> {
	void setRepositoryData(String name, String descr);
	void setReleases(ArrayList<ReleaseModel> list);
}
