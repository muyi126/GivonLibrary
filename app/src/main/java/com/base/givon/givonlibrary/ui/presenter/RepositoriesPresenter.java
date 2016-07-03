package com.base.givon.givonlibrary.ui.presenter;

import com.base.givon.givonlibrary.api.entity.RepositoriesEntity;
import com.base.givon.givonlibrary.api.entity.element.Repositories;
import com.base.givon.givonlibrary.common.base.BaseRxPresenter;
import com.base.givon.givonlibrary.common.transformer.SchedulerTransformer;
import com.base.givon.givonlibrary.module.GitHubModel;
import com.base.givon.givonlibrary.ui.view.RepositoriesFragment;

import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action2;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/3 下午2:24 - Guzhu
 * @email:muyi126@163.com
 */
public class RepositoriesPresenter extends BaseRxPresenter<RepositoriesFragment> {
    private static final int REQUEST_ID = 1;
    @Inject
    GitHubModel mGitHubModel;

    protected int pageIndex = 1;
    private String mKey = "Activity";

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
//        restartableLatestCache(REQUEST_ID, new Func0<Observable<RepositoriesEntity>>() {
//                    @Override
//                    public Observable<RepositoriesEntity> call() {
//                        return mGitHubModel.getSearchRepositories(mKey, pageIndex, 1).compose(new SchedulerTransformer<RepositoriesEntity>());
//                    }
//                }, new Action2<RepositoriesFragment, RepositoriesEntity>() {
//                    @Override
//                    public void call(RepositoriesFragment repositoriesFragment, RepositoriesEntity repositoriesEntity) {
//                        if (repositoriesEntity != null) {
//
//                        }
//                    }
//                },
//                new Action2<RepositoriesFragment, Throwable>() {
//                    @Override
//                    public void call(RepositoriesFragment repositoriesFragment, Throwable throwable) {
//                        repositoriesFragment.onNetworkError(throwable,pageIndex);
//                    }
//                }
//
//        );
        restartableLatestCache(REQUEST_ID, new Func0<Observable<List<Repositories>>>() {
                    @Override
                    public Observable<List<Repositories>> call() {
                        return mGitHubModel.getSearchRepositories(mKey, pageIndex, 20)
                                .map(new Func1<RepositoriesEntity, List<Repositories>>() {
                            @Override
                            public List<Repositories> call(RepositoriesEntity repositoriesEntity) {
                                return repositoriesEntity.getData();
                            }
                        }).compose(new SchedulerTransformer<List<Repositories>>());
                    }
                }, new Action2<RepositoriesFragment, List<Repositories>>() {
                    @Override
                    public void call(RepositoriesFragment repositoriesFragment, List<Repositories> repositories) {
                        repositoriesFragment.onChangeItems(repositories, pageIndex);
                    }
                },
                new Action2<RepositoriesFragment, Throwable>() {
                    @Override
                    public void call(RepositoriesFragment repositoriesFragment, Throwable throwable) {
                        repositoriesFragment.onNetworkError(throwable,pageIndex);
                    }
                }
        );
    }

    public void refresh() {
        pageIndex = 1;
        start(REQUEST_ID);
    }

    public void nextPage() {
        pageIndex++;
        start(REQUEST_ID);
    }
}
