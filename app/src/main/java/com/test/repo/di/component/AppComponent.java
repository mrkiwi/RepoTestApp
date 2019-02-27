package com.test.repo.di.component;

import com.test.repo.RepoApp;
import com.test.repo.di.modules.ActivityModule;
import com.test.repo.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, AppModule.class, ActivityModule.class})
interface AppComponent extends AndroidInjector<RepoApp> {
    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<RepoApp> { }
}
