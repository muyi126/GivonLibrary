package com.base.givon.demo.common.internal.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module(includes = AppModule.class)
public class ApiModule {
    @Provides
    @Singleton
    com.base.givon.demo.module.GitHubModel provideTopicModelByAuth(@com.base.givon.demo.common.internal.di.qualifier.ForApplication Context context) {
        return new com.base.givon.demo.module.GitHubModel(context);
    }

    @Provides
    @Singleton
    com.base.givon.demo.module.DrBaseModel provideDrBaseModel(@com.base.givon.demo.common.internal.di.qualifier.ForApplication Context context) {
        return new com.base.givon.demo.module.DrBaseModel(context);
    }


//
//
//    @Provides
//    @Singleton
//    TopicModel provideTopicModel(@ForApplication Context context, Prefser prefser) {
//        return new TopicModel(context, new GuestTokenProvider(prefser));
//    }
//
//    @Provides
//    @Singleton
//    TokenModel provideTokenModel(@ForApplication Context context) {
//        return new TokenModel(context, null);
//    }
//
//    @Provides
//    @Singleton
//    @Named(AUTH_TYPE_USER)
//    UserModel provideUserModelByAuth(@ForApplication Context context) {
//        return new UserModel(context, null);
//    }
//
//    @Provides
//    @Singleton
//    UserModel provideUserModelByUser(@ForApplication Context context, Prefser prefser) {
//        return new UserModel(context, new GuestTokenProvider(prefser));
//    }
}