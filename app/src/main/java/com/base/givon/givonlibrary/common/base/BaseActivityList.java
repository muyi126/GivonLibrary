package com.base.givon.givonlibrary.common.base;

import com.base.givon.givonlibrary.R;
import com.base.givon.givonlibrary.common.Constant;
import com.base.givon.givonlibrary.common.widget.recycle.DividerItemDecoration;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.kennyc.view.MultiStateView;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.nlopez.smartadapters.SmartAdapter;
import io.nlopez.smartadapters.adapters.RecyclerMultiAdapter;
import io.nlopez.smartadapters.utils.ViewEventListener;
import io.nlopez.smartadapters.views.BindableLayout;
import nucleus.presenter.Presenter;

import static com.kennyc.view.MultiStateView.VIEW_STATE_CONTENT;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/19 下午4:27 - Guzhu
 * @email:muyi126@163.com
 */
public abstract class BaseActivityList<PresenterType extends Presenter, ItemType> extends BaseActivity<PresenterType> implements ViewEventListener<ItemType> {
    @BindView(R.id.recycler_view)
    public RecyclerView mRecyclerView;
    @BindView(R.id.refresh)
    public MaterialRefreshLayout mRefresh;
    @BindView(R.id.multiStateView)
    public MultiStateView mMultiStateView;
    @BindView(R.id.empty_refresh)
    public MaterialRefreshLayout empty_refresh;
    protected List<ItemType> listItems;

    protected int pageIndex = 0;

    protected RecyclerMultiAdapter adapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.base_normal_list_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST, R.drawable.line_tr);
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (null != getValueClass() && null != getItemViewClass()) {
            adapter = SmartAdapter.empty()
                    .map(getValueClass(), getItemViewClass())
                    .listener(this)
                    .into(mRecyclerView);
        }

        MaterialRefreshListener listener = setMaterialRefreshListener();
        mRefresh.setMaterialRefreshListener(listener);
        empty_refresh.setMaterialRefreshListener(listener);
        mRefresh.autoRefresh();
    }


    public void onChangeItems(List<ItemType> list, int pageIndex) {
        if (null == list) {
            mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
            return;
        }
        if (list.size() < Constant.PAGE_SIZE) {
            mRefresh.setLoadMore(false);
        } else {
            mRefresh.setLoadMore(true);
        }
        if (pageIndex == 0) {
            if (0 == list.size()) {
                mMultiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
            } else {
                this.listItems = list;
                adapter.setItems(this.listItems);
                mMultiStateView.setViewState(VIEW_STATE_CONTENT);
            }
            refreshFinish();

        } else {
            this.listItems.addAll(list);
            adapter.notifyDataSetChanged();
            refreshFinish();
        }

    }

    public void changeItem(int postion) {
        adapter.notifyItemChanged(postion);
    }

    public ItemType getItemData(int postion) {
        return listItems.get(postion);
    }

    public void refreshFinish() {
        mRefresh.finishRefresh();
        empty_refresh.finishRefresh();
        mRefresh.finishRefreshLoadMore();
    }

    protected abstract Class<? extends BindableLayout> getItemViewClass();

    protected abstract Class getValueClass();

    public abstract MaterialRefreshListener setMaterialRefreshListener();

    public abstract void onItemViewEvent(int clickEvent, ItemType o, int postion, View view);

    @Override
    public void onViewEvent(int clickEvent, ItemType o, int postion, View view) {
        onItemViewEvent(clickEvent, o, postion, view);
    }
}
