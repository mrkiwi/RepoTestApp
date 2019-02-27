package com.test.repo.data.network.repository;

import com.test.repo.data.network.model.RepositoryModel;

import io.reactivex.Single;

public interface MainRepository {
	Single<RepositoryModel> getRepository(int count, String owner, String name);
}
