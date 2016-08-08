package com.base.givon.demo.ui.orderList;

import com.base.givon.baseobj.common.utils.UiUtil;
import com.base.givon.demo.R;
import com.base.givon.demo.api.entity.OrderListEntity;
import com.base.givon.demo.common.Constant;
import com.base.givon.demo.common.base.BaseActivity;
import com.base.givon.demo.common.provider.BusProvider;
import com.base.givon.demo.module.EventObj.EventSwitchDateType;
import com.base.givon.demo.ui.orderList.fragment.OrderListFragment;
import com.hwangjr.rxbus.annotation.Subscribe;
import com.hwangjr.rxbus.annotation.Tag;
import com.hwangjr.rxbus.thread.EventThread;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import nucleus.factory.RequiresPresenter;

import static com.base.givon.demo.module.EventObj.EventSwitchDateType.ORDER_NUM_TAG;
import static com.base.givon.demo.module.EventObj.EventSwitchDateType.SELECT_DATE_EVENT_TAG;
import static com.base.givon.demo.module.EventObj.EventSwitchDateType.SWITCH_DATE_EVENT_TAG;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/7 上午10:32 - Guzhu
 * @email:muyi126@163.com
 */
@RequiresPresenter(ActOrderListPresenter.class)
public class ActOrderLsitActivity extends BaseActivity<ActOrderListPresenter> {

    @BindView(R.id.toolbar_edit)
    TextView toolbar_edit;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.viewpagertab)
    SmartTabLayout mSmartTabLayout;
//    PopupWindow mPopupWindow;


    public static int TAG_FINISH = 1;
    public static int TAG_NOT_FINISH = 0;

    private com.base.givon.demo.common.widget.SelectDateDialog mSelectDateDialog;


    private ActOrderLsitActivity.LaunchTag mLaunchTag;

    public LaunchTag getLaunchTag() {
        return mLaunchTag;
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.act_orderlist_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        toolbarTitleView.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.mipmap.all, 0);
        toolbarTitleView.setCompoundDrawablePadding(UiUtil.dip2px(10));
        toolbarTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mLaunchTag != LaunchTag.LUNCH_TAG_FROM_TODAY) {
                    mLaunchTag = LaunchTag.LUNCH_TAG_FROM_TODAY;
                } else {
                    mLaunchTag = LaunchTag.LUNCH_TAG_FROM_ALL;
                }
                initView();
                postEvent();
//                if (null == mPopupWindow) {
//                    mPopupWindow = UiUtil.showPopupWindow(ActOrderLsitActivity.this, R.layout.dialog_order_list, toolbarTitleView, 0, 0);
//                } else {
//                    if (!mPopupWindow.isShowing()) {
//                        mPopupWindow.showAsDropDown(toolbarTitleView, 0, 0);
//                    }
//                }
//                mPopupWindow.getContentView().findViewById(R.id.item_todaylist).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (mLaunchTag != LaunchTag.LUNCH_TAG_FROM_TODAY) {
//                            mLaunchTag = LaunchTag.LUNCH_TAG_FROM_TODAY;
//                            initView();
//                            mPopupWindow.dismiss();
//                            postEvent();
//                        }
//                    }
//                });
//                mPopupWindow.getContentView().findViewById(R.id.item_alllist).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if (mLaunchTag != LaunchTag.LUNCH_TAG_FROM_ALL) {
//                            mLaunchTag = LaunchTag.LUNCH_TAG_FROM_ALL;
//                            initView();
//                            mPopupWindow.dismiss();
//                            postEvent();
//                        }
//
//                    }
//                });
            }
        });

        setupTabView();
    }

    private void postEvent() {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.TAG, mLaunchTag);
        BusProvider.getInstance().post(SWITCH_DATE_EVENT_TAG, new EventSwitchDateType(bundle));
    }

    private void initData() {
        mLaunchTag = (LaunchTag) getIntent().getSerializableExtra(Constant.LAUNCH_TAG);
        initView();
    }


    private void initView() {
        switch (mLaunchTag) {
            case LUNCH_TAG_FROM_TODAY:
                toolbar_edit.setVisibility(View.GONE);
                setTitle("今日订单");
                break;
            case LUNCH_TAG_FROM_ALL:
                setTitle("全部订单");
                toolbar_edit.setVisibility(View.VISIBLE);
                break;
        }
    }


    public static Intent getCallingIntentToToday(Context context) {
        Intent intent = new Intent(context, ActOrderLsitActivity.class);
        intent.putExtra(Constant.LAUNCH_TAG, LaunchTag.LUNCH_TAG_FROM_TODAY);
        return intent;
    }

    public static Intent getCallingIntentToAll(Context context) {
        Intent intent = new Intent(context, ActOrderLsitActivity.class);
        intent.putExtra(Constant.LAUNCH_TAG, LaunchTag.LUNCH_TAG_FROM_ALL);
        return intent;
    }


    protected void setupTabView() {
        final LayoutInflater inflater = getLayoutInflater();
        Bundle bundle_finish = new Bundle();
        bundle_finish.putInt(Constant.TAG, TAG_FINISH);
        Bundle bundle_not_finish = new Bundle();
        bundle_not_finish.putInt(Constant.TAG, TAG_NOT_FINISH);
        FragmentPagerItems pages = FragmentPagerItems.with(this)
                .add("未完成", OrderListFragment.class, bundle_not_finish)
                .add("已完成", OrderListFragment.class, bundle_finish)
                .create();
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                pages);
        mViewPager.setOffscreenPageLimit(pages.size());
        mViewPager.setAdapter(adapter);
        mSmartTabLayout.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter pagerAdapter) {
                View view = inflater.inflate(R.layout.act_orderlist_tab, container, false);
                TextView tv_info = (TextView) view.findViewById(R.id.tv_info);
                TextView tv_num = (TextView) view.findViewById(R.id.tv_num);
                if (position == 0) {
                    tv_info.setText("未完成");
                } else if (position == 1) {
                    tv_info.setText("已完成");
                }

                return view;
            }
        });

        mSmartTabLayout.setViewPager(mViewPager);
        BusProvider.getInstance().register(this);

    }

    public enum LaunchTag {
        LUNCH_TAG_FROM_TODAY(0), LUNCH_TAG_FROM_ALL(1);
        public int code;

        LaunchTag(int code) {
            this.code = code;
        }
    }


    @Subscribe(thread = EventThread.MAIN_THREAD, tags = {@Tag(ORDER_NUM_TAG)})
    public void onRefreshOrderNum(EventSwitchDateType eventSwitchDateType) {
        Bundle bundle = eventSwitchDateType.getBundle();
        OrderListEntity orderListEntity = (OrderListEntity) bundle.getSerializable(Constant.TAG);
        ((TextView) mSmartTabLayout.getTabAt(0).findViewById(R.id.tv_num)).setText(orderListEntity.getUnCompleteCount() + "");
        ((TextView) mSmartTabLayout.getTabAt(1).findViewById(R.id.tv_num)).setText(orderListEntity.getCompleteCount() + "");

    }

    @OnClick(R.id.toolbar_edit)
    public void onClickEdit() {
        if (null == mSelectDateDialog) {
            mSelectDateDialog = new com.base.givon.demo.common.widget.SelectDateDialog(this, R.style.selectorDialog);
            mSelectDateDialog.setOnSelectDateListener(new com.base.givon.demo.common.widget.SelectDateDialog.OnSelectDateListener() {

                @Override
                public void onSelectFinish(String data) {
//                tv_birthday.setText(data);
                    Bundle bundle = new Bundle();
                    bundle.putString(Constant.TAG, data);
                    BusProvider.getInstance().post(SELECT_DATE_EVENT_TAG, new EventSwitchDateType(bundle));
                }

                @Override
                public void onSelectCancel() {

                }
            });
        }
        //TODO 可以做日期选择的限定
        mSelectDateDialog.show();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BusProvider.getInstance().unregister(this);
    }
}
