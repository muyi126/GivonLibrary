package com.base.givon.demo.common.base;

import com.base.givon.baseobj.common.base.GivBaseActivity;
import com.base.givon.baseobj.common.base.GivBaseActivityList;
import com.base.givon.demo.R;
import com.base.givon.demo.common.Constant;
import com.base.givon.demo.common.widget.recycle.DividerItemDecoration;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.kennyc.view.MultiStateView;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import butterknife.BindView;
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
public abstract class BaseActivityList<PresenterType extends Presenter, ItemType> extends GivBaseActivityList<PresenterType,ItemType> implements ViewEventListener<ItemType> {
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
