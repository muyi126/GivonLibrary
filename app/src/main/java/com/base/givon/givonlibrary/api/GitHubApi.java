package com.base.givon.givonlibrary.api;

import com.base.givon.givonlibrary.api.entity.RepositoriesEntity;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/30 下午6:50 - Guzhu
 * @email:muyi126@163.com
 * //component
 */
public interface GitHubApi {
//    @GET(search/repositories?q=language&sort=0&order=desc&page=2&per_page=1)
    @GET("search/repositories")
    Observable<RepositoriesEntity> searchGitHubPoj(@Query("q") String qKey,@Query("page") int page,@Query("per_page") int per_page);


}
