package com.test.repo.data.network.repository;


import com.apollographql.apollo.ApolloCall;
import com.apollographql.apollo.ApolloClient;
import com.apollographql.apollo.api.Response;
import com.apollographql.apollo.rx2.Rx2Apollo;
import com.test.repo.data.network.model.RepositoryModel;
import com.test.repo.data.network.transformer.RepositoryTransformer;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;
import test.FindRepositoryQuery;


public class MainRepositoryImpl implements MainRepository {

	private ApolloClient apolloClient;
	private RepositoryTransformer repositoryTransformer;

	@Inject
	public MainRepositoryImpl(final ApolloClient _apolloClient, final RepositoryTransformer _repositoryTransformer) {
		this.apolloClient = _apolloClient;
		this.repositoryTransformer = _repositoryTransformer;
	}

	@Override
	public Single<RepositoryModel> getRepository(int count, String owner, String name) {
		FindRepositoryQuery query = FindRepositoryQuery.builder().first(count).name(name).owner(owner).build();
		ApolloCall<FindRepositoryQuery.Data> findCall = apolloClient.query(query);

		Observable<Response<FindRepositoryQuery.Data>> dataObservable = Rx2Apollo.from(findCall);
		return Single.fromObservable(dataObservable).compose(repositoryTransformer.transform());
	}
}
