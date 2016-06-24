package com.base.givon.givonlibrary.ui.view;


import com.base.givon.givonlibrary.R;
import com.base.givon.givonlibrary.common.base.BaseActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.Bind;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/21 上午8:56 - Guzhu
 * @email:muyi126@163.com
 */
public class MainActivity extends BaseActivity {
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.viewpagertab)
    SmartTabLayout mSmartTabLayout;

    @Override
    protected int getLayoutResId() {
        return R.layout.main;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupTabView();
    }

    protected void setupTabView() {
        final LayoutInflater layoutInflater = LayoutInflater.from(this);
        final int[] tabIcons = {R.drawable.ic_recommended, R.drawable.ic_topics, R.drawable.ic_wiki, R.drawable.ic_me};
        FragmentPagerItems pages = FragmentPagerItems.with(this)
                .add("Recommend", RecommendedFragment.class)
                .add("Tipoc", RecommendedFragment.class)
                .create();
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(),
                pages);
        mViewPager.setOffscreenPageLimit(pages.size());
        mViewPager.setAdapter(adapter);
        mSmartTabLayout.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView(ViewGroup container, int position, PagerAdapter pagerAdapter) {
                View view = layoutInflater.inflate(R.layout.custom_tab_icon, container, false);
                ImageView iconView = (ImageView) view.findViewById(R.id.iv_icon);
                iconView.setBackgroundResource(tabIcons[position % tabIcons.length]);
                return view;
            }
        });
        mSmartTabLayout.setViewPager(mViewPager);

    }

}
