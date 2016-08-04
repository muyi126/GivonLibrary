package com.base.givon.givonlibrary.ui.orderList.fragment;

import com.base.givon.givonlibrary.R;
import com.base.givon.givonlibrary.api.entity.OrderListEntity;
import com.base.givon.givonlibrary.api.entity.element.Order;
import com.base.givon.givonlibrary.common.Constant;
import com.base.givon.givonlibrary.common.adapter.OrderListItemView;
import com.base.givon.givonlibrary.common.base.LazyFragment;
import com.base.givon.givonlibrary.common.provider.BusProvider;
import com.base.givon.givonlibrary.common.widget.recycle.DividerItemDecoration;
import com.base.givon.givonlibrary.module.EventObj.EventSwitchDateType;
import com.base.givon.givonlibrary.ui.orderList.ActOrderLsitActivity;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.kennyc.view.MultiStateView;
import com.orhanobut.logger.Logger;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.nlopez.smartadapters.SmartAdapter;
import io.nlopez.smartadapters.adapters.RecyclerMultiAdapter;
import io.nlopez.smartadapters.utils.ViewEventListener;
import nucleus.factory.PresenterFactory;
import nucleus.factory.RequiresPresenter;

import static com.base.givon.givonlibrary.common.qualifier.ClickType.CLICK_TYPE_NORMAL_CLICKED;
import static com.base.givon.givonlibrary.module.EventObj.EventSwitchDateType.ORDER_NUM_TAG;
import static com.base.givon.givonlibrary.module.EventObj.EventSwitchDateType.SELECT_DATE_EVENT_TAG;
import static com.base.givon.givonlibrary.module.EventObj.EventSwitchDateType.SWITCH_DATE_EVENT_TAG;
import static com.kennyc.view.MultiStateView.VIEW_STATE_CONTENT;
import static com.kennyc.view.MultiStateView.VIEW_STATE_ERROR;
import static com.kennyc.view.MultiStateView.VIEW_STATE_LOADING;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/7 下午2:08 - Guzhu
 * @email:muyi126@163.com
 */
@RequiresPresenter(FraOrderListPresenter.class)
public class OrderListFragment extends LazyFragment<FraOrderListPresenter> implements ViewEventListener<Order> {
    private boolean isPrepared;

    RecyclerMultiAdapter adapter;

    @BindView(R.id.multiStateView)
    MultiStateView multiStateView;

    @BindView(R.id.refresh)
    MaterialRefreshLayout refreshView;

    @BindView(R.id.empty_refresh)
    MaterialRefreshLayout empty_refresh;

    @BindView(R.id.recycler_view)
    RecyclerView orderListView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    public int mTag;
    String tripDate = "";
    private ActOrderLsitActivity.LaunchTag dateType;//（0：今日；1：全部)


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getAppComponent().inject(this);
        try {
            mTag = (int) getArguments().get(Constant.TAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
        BusProvider.getInstance().register(this);


    }

    public int getmTag() {
        return mTag;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.base_normal_list_view, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getActivity() instanceof ActOrderLsitActivity) {
            dateType = ((ActOrderLsitActivity) getActivity()).getLaunchTag();
        } else {
            dateType = ActOrderLsitActivity.LaunchTag.LUNCH_TAG_FROM_TODAY;
        }
        if (toolbarView != null) {
            toolbarView.setVisibility(View.GONE);
        }
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST, R.drawable.line_tr);
        orderListView.addItemDecoration(dividerItemDecoration);
        orderListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = SmartAdapter.empty()
                .map(Order.class, OrderListItemView.class)
                .listener(this)
                .into(orderListView);
        MaterialRefreshListener listener = new MaterialRefreshListener() {
            @Override
            public void onRefresh(MaterialRefreshLayout materialRefreshLayout) {
                getPresenter().refresh(dateType, mTag, tripDate);
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                super.onRefreshLoadMore(materialRefreshLayout);
                getPresenter().nextPage(dateType, mTag, tripDate);
            }
        };
        refreshView.setMaterialRefreshListener(listener);
        empty_refresh.setMaterialRefreshListener(listener);

        isPrepared = true;
        lazyLoad();
    }

    @Override
    protected void injectorPresenter() {
        super.injectorPresenter();
        final PresenterFactory<FraOrderListPresenter> superFactory = super.getPresenterFactory();
        setPresenterFactory(new PresenterFactory<FraOrderListPresenter>() {
            @Override
            public FraOrderListPresenter createPresenter() {
                FraOrderListPresenter fraOrderListPresenter = superFactory.createPresenter();
                getApiComponent().inject(fraOrderListPresenter);
                return fraOrderListPresenter;
            }
        });
    }

    @Override
    protected void lazyLoad() {
        //当显示View时才会调用
        if (!isPrepared || !isVisible) {
            return;
        }

        if (!canLoadData(multiStateView, adapter)) {
            return;
        }

        multiStateView.setViewState(VIEW_STATE_LOADING);
        refreshView.autoRefresh();
    }


    public void onChangeItems(OrderListEntity orderListEntity, int pageIndex) {
        if (null == orderListEntity) {
            multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
            return;
        }
        if (null == orderListEntity.getDriverOrderList()) {
            multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
            return;
        }
        List<Order> repositories = orderListEntity.getDriverOrderList();
        if (repositories.size() < Constant.PAGE_SIZE) {
            refreshView.setLoadMore(false);
        } else {
            refreshView.setLoadMore(true);
        }
        if (pageIndex == 0) {
            if (0 == repositories.size()) {
                multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
            } else {
                adapter.setItems(repositories);
                multiStateView.setViewState(VIEW_STATE_CONTENT);
            }
            refreshFinish();
            refreshView.finishRefreshLoadMore();
            //只有刷新时 ORDER_NUM_TAG 的数据才有效
            Bundle bundle = new Bundle();
            bundle.putSerializable(Constant.TAG, orderListEntity);
            BusProvider.getInstance().post(ORDER_NUM_TAG, new EventSwitchDateType(bundle));
        } else {
            adapter.addItems(repositories);
            refreshView.finishRefreshLoadMore();
        }

    }

    private void refreshFinish() {
        refreshView.finishRefresh();
        empty_refresh.finishRefresh();
    }

    public void onNetworkError(Throwable throwable, int pageIndex) {
        Logger.e(throwable.getMessage());
        if (pageIndex == 0) {
            multiStateView.setViewState(VIEW_STATE_ERROR);
        }
    }

    @Override
    public void onViewEvent(int i, Order order, int postion, View view) {
        if (i == CLICK_TYPE_NORMAL_CLICKED) {
//            navigator.navigateToOrderDetail(getActivity(), order.getId() + "", LaunchType.LAUNCH_TYPE_FROM_NOMEL);
        }


    }

    @Subscribe(thread = EventThread.MAIN_THREAD, tags = {@Tag(SWITCH_DATE_EVENT_TAG)})
    public void onSwitchDate(EventSwitchDateType result) {
        if (null != result.getBundle()) {
            try {
                dateType = (ActOrderLsitActivity.LaunchTag) result.getBundle().get(Constant.TAG);
                tripDate = "";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        refreshView.autoRefresh();
    }

    @Subscribe(thread = EventThread.MAIN_THREAD, tags = {@Tag(SELECT_DATE_EVENT_TAG)})
    public void onSelectDate(EventSwitchDateType result) {
        if (null != result.getBundle()) {
            try {
                tripDate = (String) result.getBundle().get(Constant.TAG);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        refreshView.autoRefresh();

    }


    @OnClick(R.id.retry)
    public void retry() {
        multiStateView.setViewState(VIEW_STATE_LOADING);
        refreshView.autoRefresh();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
    }
}
