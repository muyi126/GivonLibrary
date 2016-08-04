package com.base.givon.librarymaster.common.transformer;



import com.base.givon.librarymaster.api.entity.HttpResult;
import com.base.givon.librarymaster.common.net.HttpResultFunc;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/8 上午6:25 - Guzhu
 * @email:muyi126@163.com
 */
public class BaseApiTransformer<T> implements Observable.Transformer<HttpResult<T>, T> {
    @Override
    public Observable<T> call(Observable<HttpResult<T>> httpResultObservable) {
        return httpResultObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).map(new HttpResultFunc<T>());
    }


//    @Override
//    public HttpResult<T> call(Observable<T> observable) {
//        return observable.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()).map((new HttpResultFunc<HttpResult<T>>());
//    }
//
//    @Override
//    public Observable<T> call(Observable<HttpResult<T>> httpResultObservable) {
//        return null;
//    }
}
