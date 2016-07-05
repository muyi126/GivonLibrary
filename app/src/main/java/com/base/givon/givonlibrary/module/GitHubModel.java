package com.base.givon.givonlibrary.module;

import com.base.givon.givonlibrary.api.GitHubApi;
import com.base.givon.givonlibrary.api.entity.RepositoriesEntity;
import com.base.givon.givonlibrary.common.base.BaseModel;

import android.content.Context;
import android.support.annotation.NonNull;

import rx.Observable;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/30 上午11:31 - Guzhu
 * @email:muyi126@163.com
 */
public class GitHubModel extends BaseModel<GitHubApi,GitHubModel>{

    public GitHubModel(@NonNull Context context) {
        super(context);
    }

    @Override
    protected Class<GitHubApi> getServiceClass() {
        return GitHubApi.class;
    }

//    public void getSearchRepositories(String qkey, int page, int psize, Observer<RepositoriesEntity> observer){
//        setSubscribe(getService().searchGitHubPoj(qkey,page,psize),observer);
//    }

    public Observable<RepositoriesEntity> getSearchRepositories(String qkey, int page, int psize){
        return getService().searchGitHubPoj(qkey,page,psize);
    }
}
