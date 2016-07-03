package com.base.givon.givonlibrary.common.base;

//import org.estgroup.phphub.BuildConfig;
//import org.estgroup.phphub.api.RequestInterceptorImpl;
//import org.estgroup.phphub.common.provider.TokenProvider;

import com.base.givon.givonlibrary.BuildConfig;
import com.base.givon.givonlibrary.common.App;
import com.base.givon.givonlibrary.common.provider.TokenProvider;
import com.lzy.okhttputils.OkHttpUtils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

//import eu.unicate.retroauth.AuthRestAdapter;
//import eu.unicate.retroauth.interceptors.TokenInterceptor;

public abstract class BaseModel<T, R extends BaseModel> {
    private T service;
    @Inject
    Retrofit mRetrofit;

    public BaseModel(@NonNull Context context) {

//        this.tokenProvider = tokenProvider;
        mRetrofit = App.mApp.getRetrofit();
        service = mRetrofit.create(getServiceClass());
//        this.service = authRestAdapter.create(context, TokenInterceptor.BEARER_TOKENINTERCEPTOR, getServiceClass());
    }

    //给service赋值
    protected abstract Class<T> getServiceClass();

    //
//    public RequestInterceptorImpl asRequestInterceptor() {
//        return requestInterceptor;
//    }
//
//    public R once() {
//        RequestInterceptorImpl requestInterceptor = localRequestInterceptor.get();
//        if (requestInterceptor == null) {
//            localRequestInterceptor.set(new RequestInterceptorImpl());
//        }
//        return (R) this;
//    }
//
//    public R setToken(final String token) {
//        RequestInterceptorImpl requestInterceptor = localRequestInterceptor.get();
//        if (requestInterceptor != null) {
//            requestInterceptor.setTokenProvider(new TokenProvider() {
//                @Override
//                public String getToken() {
//                    return token;
//                }
//            });
//        }
//        return (R) this;
//    }
//
//    public R ignoreToken() {
//        RequestInterceptorImpl requestInterceptor = localRequestInterceptor.get();
//        if (requestInterceptor != null) {
//            requestInterceptor.setTokenProvider(null);
//        }
//        return (R) this;
//    }
//
//    private TokenProvider TokenProvider() {
//        RequestInterceptorImpl requestInterceptor = localRequestInterceptor.get();
//        if (requestInterceptor != null) {
//            return requestInterceptor.getTokenProvider();
//        }
//
//        return tokenProvider;
//    }
//
    public T getService() {
            return service;
    }

    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }

}