package com.test.repo.di.modules;

import android.content.Context;

import com.test.repo.RepoApp;
import com.test.repo.data.network.repository.MainRepository;

import dagger.Binds;
import dagger.Module;

@Module(includes = {NetworkModule.class, RepositoryModule.class})
public abstract class AppModule {
    @Binds
    abstract Context application(final RepoApp app);

    abstract MainRepository mainRepository();
}
