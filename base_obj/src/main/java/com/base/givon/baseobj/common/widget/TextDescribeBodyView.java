package com.base.givon.baseobj.common.widget;


import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/26 上午11:49 - Guzhu
 * @email:muyi126@163.com 只去控制TextView的属性
 */
public class TextDescribeBodyView extends DescribeViewBuild implements DescribeBodyView {
    private TextView textView;

    public TextDescribeBodyView(Context context) {
        super(context);
    }

    public TextDescribeBodyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TextDescribeBodyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }


    @Override
    public void initView(Context context, AttributeSet attrs) {
        textView = new TextView(context, attrs);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(layoutParams);
        textView.setGravity(Gravity.CENTER_VERTICAL);
        setDescribeBodyView(this);
    }

    @Override
    public View getBodyView(Context context) {
        return textView;
    }

    public void setText(CharSequence sequence) {
        textView.setText(sequence);
    }
}
