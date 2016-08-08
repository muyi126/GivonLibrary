package com.base.givon.baseobj.common.widget;


import android.content.Context;
import android.graphics.Rect;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/26 上午11:49 - Guzhu
 * @email:muyi126@163.com 只去控制EditTextView的属性
 */
public class EditDescribeBodyView extends DescribeViewBuild implements DescribeBodyView {
    private EditText mEditText;

    public EditDescribeBodyView(Context context) {
        super(context);
    }

    public EditDescribeBodyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public EditDescribeBodyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }


    @Override
    public void initView(Context context, AttributeSet attrs) {
        mEditText = new EditText(context, attrs);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mEditText.setLayoutParams(layoutParams);
        mEditText.setGravity(Gravity.CENTER_VERTICAL);
        setDescribeBodyView(this);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (gainFocus) {
            mEditText.setFocusable(true);
            mEditText.setFocusableInTouchMode(true);
            mEditText.requestFocus();
            mEditText.requestFocusFromTouch();
            if (mEditText.getText().length() > 0) {
                mEditText.setSelection(mEditText.getText().length());
            }
        }
    }

    public void addTextChangedListener(TextWatcher textWatcher) {
        mEditText.addTextChangedListener(textWatcher);
    }

    public String getText() {
        return mEditText.getText().toString();
    }

    public void setEditEnable(boolean editEnable) {
        mEditText.setEnabled(editEnable);
    }

    @Override
    public View getBodyView(Context context) {
        return mEditText;
    }

    public void setText(CharSequence sequence) {
        mEditText.setText(sequence);
    }
}
