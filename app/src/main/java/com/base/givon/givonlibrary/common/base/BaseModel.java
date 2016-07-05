package com.base.givon.givonlibrary.common.base;

import com.base.givon.givonlibrary.common.App;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public abstract class BaseModel<T, R extends BaseModel> {
    private T service;
    @Inject
    Retrofit mRetrofit;

    public BaseModel(@NonNull Context context) {
        mRetrofit = App.mApp.getRetrofit();
        service = mRetrofit.create(getServiceClass());
    }

    //给service赋值
    protected abstract Class<T> getServiceClass();

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