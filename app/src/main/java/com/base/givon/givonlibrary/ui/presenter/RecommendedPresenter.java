package com.base.givon.givonlibrary.ui.presenter;

import com.base.givon.givonlibrary.api.entity.element.Topic;
import com.base.givon.givonlibrary.common.base.BaseRxPresenter;
import com.base.givon.givonlibrary.ui.view.RecommendedFragment;
import com.github.pwittchen.prefser.library.Prefser;

import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.functions.Action2;
import rx.functions.Func0;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/23 下午2:27 - Guzhu
 * @email:muyi126@163.com
 */
public class RecommendedPresenter extends BaseRxPresenter<RecommendedFragment> {
    private static final int REQUEST_ID = 1;

    @Inject
    TopicModel topicModel;

    @Inject
    TokenModel tokenModel;

    @Inject
    Prefser prefser;

    protected int pageIndex = 1;

    @Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);

        restartableLatestCache(REQUEST_ID,
                new Func0<Observable<List<Topic>>>() {
                    @Override
                    public Observable<List<Topic>> call() {
                        return topicModel.getTopicsByExcellent(pageIndex)
                                .map(new Func1<TopicEntity, List<Topic>>() {
                                    @Override
                                    public List<Topic> call(TopicEntity topicEntity) {
                                        return topicEntity.getData();
                                    }
                                })
                                .compose(new SchedulerTransformer<List<Topic>>())
                                .compose(new TokenGeneratorTransformer<List<Topic>>(tokenModel, prefser));
                    }
                },
                new Action2<RecommendedFragment, List<Topic>>() {
                    @Override
                    public void call(RecommendedFragment recommendedFragment, List<Topic> topics) {
                        recommendedFragment.onChangeItems(topics, pageIndex);
                    }
                },
                new Action2<RecommendedFragment, Throwable>() {
                    @Override
                    public void call(RecommendedFragment recommendedFragment, Throwable throwable) {
                        recommendedFragment.onNetworkError(throwable, pageIndex);
                    }
                });
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
