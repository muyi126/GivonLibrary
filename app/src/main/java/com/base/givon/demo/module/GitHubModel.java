package com.base.givon.demo.module;

import com.base.givon.demo.api.GitHubApi;

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
public class GitHubModel extends com.base.givon.demo.common.base.BaseModel<GitHubApi,GitHubModel> {

    public GitHubModel(@NonNull Context context) {
        super(context);
    }

    @Override
    protected Class<com.base.givon.demo.api.GitHubApi> getServiceClass() {
        return com.base.givon.demo.api.GitHubApi.class;
    }

    public Observable<com.base.givon.demo.api.entity.RepositoriesEntity> getSearchRepositories(String qkey, int page, int psize){
        return getService().searchGitHubPoj(qkey,page,psize);
    }
}
