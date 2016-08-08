package com.base.givon.demo.ui.view;

import com.base.givon.demo.R;
import com.base.givon.demo.api.entity.element.Repositories;
import com.base.givon.demo.common.adapter.RepositoriesItemView;
import com.base.givon.demo.common.base.LazyFragment;
import com.base.givon.demo.ui.presenter.RepositoriesPresenter;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.kennyc.view.MultiStateView;
import com.orhanobut.logger.Logger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import io.nlopez.smartadapters.SmartAdapter;
import io.nlopez.smartadapters.adapters.RecyclerMultiAdapter;
import io.nlopez.smartadapters.utils.ViewEventListener;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

import static com.kennyc.view.MultiStateView.VIEW_STATE_CONTENT;
import static com.kennyc.view.MultiStateView.VIEW_STATE_ERROR;
import static com.kennyc.view.MultiStateView.VIEW_STATE_LOADING;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/3 下午1:05 - Guzhu
 * @email:muyi126@163.com
 */
@RequiresPresenter(RepositoriesPresenter.class)
public class RepositoriesFragment extends LazyFragment<RepositoriesPresenter> implements ViewEventListener {
    private boolean isPrepared;

    RecyclerMultiAdapter adapter;

    @BindView(R.id.multiStateView)
    MultiStateView multiStateView;

    @BindView(R.id.refresh)
    MaterialRefreshLayout refreshView;

    @BindView(R.id.recycler_view)
    RecyclerView topicListView;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_normal_list_view,container,false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (toolbarView != null) {
            toolbarView.inflateMenu(R.menu.menu_publish);
            toolbarView.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    switch (item.getItemId()){
                        case R.id.action_publish:
//                            navigator.navigateToActLogin(getContext());
                            break;

                    }

                    return true;
                }
            });
        }

        topicListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = SmartAdapter.empty()
                .map(Repositories.class, RepositoriesItemView.class)
                .listener(this)
                .into(topicListView);

        refreshView.setMaterialRefreshListener(new MaterialRefreshListener() {
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
        final PresenterFactory<RepositoriesPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(new PresenterFactory<RepositoriesPresenter>() {
            @Override
            public RepositoriesPresenter createPresenter() {
                RepositoriesPresenter repositoriesPresenter = superFactory.createPresenter();
                getApiComponent().inject(repositoriesPresenter);
                return repositoriesPresenter;
            }
        });
    }

    @Override
    protected void lazyLoad() {
        //当显示View时才会调用
        if(!isPrepared || !isVisible) {
            return;
        }

        if (!canLoadData(multiStateView, adapter)) {
            return;
        }

        multiStateView.setViewState(VIEW_STATE_LOADING);
        refreshView.autoRefresh();

    }


    public void onChangeItems(List<Repositories> repositories, int pageIndex) {
        if (pageIndex == 1) {
            adapter.setItems(repositories);
            multiStateView.setViewState(VIEW_STATE_CONTENT);
            refreshView.finishRefresh();
        } else {
            adapter.addItems(repositories);
            refreshView.finishRefreshLoadMore();
        }
    }

    public void onNetworkError(Throwable throwable, int pageIndex) {
        Logger.e(throwable.getMessage());
        if (pageIndex == 1) {
            multiStateView.setViewState(VIEW_STATE_ERROR);
        }
    }

    @Override
    public void onViewEvent(int i, Object o, int i1, View view) {

    }
}
