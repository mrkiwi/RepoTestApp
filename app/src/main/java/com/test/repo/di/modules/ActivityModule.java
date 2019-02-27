package com.test.repo.di.modules;

import com.test.repo.di.scopes.ActivityScope;
import com.test.repo.ui.main.MainActivity;
import com.test.repo.ui.start.StartActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ActivityScope
    @ContributesAndroidInjector
    abstract MainActivity mainActivity();

    @ActivityScope
    @ContributesAndroidInjector
    abstract StartActivity startActivity();

}
