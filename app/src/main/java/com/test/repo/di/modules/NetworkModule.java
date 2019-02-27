package com.test.repo.di.modules;

import android.content.Context;

import com.apollographql.apollo.ApolloClient;
import com.test.repo.data.local.Constants;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import timber.log.Timber;

@Module
public class NetworkModule {

    @Provides
    @Singleton
    final ApolloClient providesApollo(final Context _context) {
        return ApolloClient.builder()
                .serverUrl(Constants.BASE_SERVER_URL)
                .okHttpClient(createCommonClient(_context))
                .build();
    }

    private OkHttpClient createCommonClient(final Context _context) {
        final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(message -> Timber.d(message));
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .followRedirects(false)
                .followSslRedirects(false)
                .retryOnConnectionFailure(true)
                .writeTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .connectTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(apiKeyInterceptor)
                .addInterceptor(loggingInterceptor)
                .build();
    }

    private Interceptor apiKeyInterceptor = chain -> {
        Request original = chain.request();
        Request.Builder requestBuilder = original.newBuilder();
        requestBuilder.header("Authorization", "bearer " + Constants.AUTH_TOKEN);
        Request request = requestBuilder.build();
        return chain.proceed(request);
    };
}
