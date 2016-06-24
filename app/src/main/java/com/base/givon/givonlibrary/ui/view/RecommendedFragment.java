package com.base.givon.givonlibrary.ui.view;

import com.base.givon.givonlibrary.R;
import com.base.givon.givonlibrary.api.entity.element.Topic;
import com.base.givon.givonlibrary.common.adapter.TopicItemView;
import com.base.givon.givonlibrary.common.base.LazyFragment;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.kennyc.view.MultiStateView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import icepick.Icepick;
import io.nlopez.smartadapters.SmartAdapter;
import io.nlopez.smartadapters.adapters.RecyclerMultiAdapter;
import io.nlopez.smartadapters.utils.ViewEventListener;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/21 下午6:22 - Guzhu
 * @email:muyi126@163.com
 */
public class RecommendedFragment extends LazyFragment implements ViewEventListener<Topic>{
    private boolean isPrepared;

    RecyclerMultiAdapter adapter;
    @Bind(R.id.multiStateView)
    MultiStateView mMultiStateView;
    @Bind(R.id.refresh)
    MaterialRefreshLayout mMaterialRefreshLayout;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.topic_normal_list,container,false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (toolbarView != null) {
            toolbarView.inflateMenu(R.menu.menu_publish);
            toolbarView.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
//                    navigator.navigateToPublishTopic(getContext());
                    return true;
                }
            });
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = SmartAdapter.empty()
                //Obj对应到View
                .map(Topic.class, TopicItemView.class)
                //设置监听
                .listener(this)
                //对recyclerveiw设置adapter
                .into(mRecyclerView);

        mMaterialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getPresenter().refresh();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                getPresenter().nextPage();
            }
        });

        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void injectorPresenter() {
        super.injectorPresenter();
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onViewEvent(int actionId, Topic topic, int position, View view) {

    }
}
