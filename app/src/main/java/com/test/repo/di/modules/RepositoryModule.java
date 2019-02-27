package com.test.repo.di.modules;


import com.apollographql.apollo.ApolloClient;
import com.test.repo.data.network.repository.MainRepository;
import com.test.repo.data.network.repository.MainRepositoryImpl;
import com.test.repo.data.network.transformer.RepositoryTransformer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    MainRepository provideMainRepository(final ApolloClient apolloClient, final RepositoryTransformer repositoryTransformer) {
        return new MainRepositoryImpl(apolloClient, repositoryTransformer);
    }

}
