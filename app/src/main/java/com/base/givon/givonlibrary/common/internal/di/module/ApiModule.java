package com.base.givon.givonlibrary.common.internal.di.module;

import com.base.givon.givonlibrary.common.internal.di.qualifier.ForApplication;
import com.base.givon.givonlibrary.module.DrBaseModel;
import com.base.givon.givonlibrary.module.GitHubModel;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.base.givon.givonlibrary.common.qualifier.AuthType.AUTH_TYPE_USER;


@Module(includes = AppModule.class)
public class ApiModule {
    @Provides
    @Singleton
    GitHubModel provideTopicModelByAuth(@ForApplication Context context) {
        return new GitHubModel(context);
    }

    @Provides
    @Singleton
    DrBaseModel provideDrBaseModel(@ForApplication Context context) {
        return new DrBaseModel(context);
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