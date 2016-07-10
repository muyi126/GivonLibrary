package com.base.givon.givonlibrary.common.transformer;

import com.base.givon.givonlibrary.api.entity.element.Repositories;
import com.base.givon.givonlibrary.common.net.HttpResultFunc;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SchedulerTransformer<T> implements Observable.Transformer<T, T> {
    @Override
    public Observable<T> call(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }
}