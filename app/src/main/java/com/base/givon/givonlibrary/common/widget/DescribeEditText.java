package com.base.givon.givonlibrary.common.widget;

import com.base.givon.givonlibrary.R;
import com.base.givon.givonlibrary.common.utils.StringUtil;
import com.base.givon.givonlibrary.common.utils.UiUtil;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * <p/>
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @一月 16/1/2 上午10:04 - Guzhu
 * @email:muyi126@163.com
 */
public class DescribeEditText extends RelativeLayout {
    private int mDescribeTextColor;
    private String mDescribeString;
    private int mDescribeTextSize;
    private int mDescribeTextWidth;
    private int mDescribeTextHeight;
    private EditText mEditText;
    private TextView mDescribeText;
    private int mBackGround;
    //整个View的bg
    private int mDeLyBackGround;
    private String mHint;
    private int mTextColor;
    private int mTextSize = 15;

    public DescribeEditText(Context context) {
        super(context);
    }

    public DescribeEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public DescribeEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.TextView, 0, 0);
        mHint = a.getString(R.styleable.TextView_android_hint);
        mBackGround = a.getResourceId(R.styleable.TextView_android_background, 0);
        mTextColor = a.getColor(R.styleable.TextView_android_textColor, getResources().getColor(R.color.tab_text_enabled));
        mTextSize = a.getDimensionPixelSize(R.styleable.TextView_android_textSize, mTextSize);
        a.recycle();
        final TypedArray b = context.obtainStyledAttributes(
                attrs, R.styleable.DescribeEditText, 0, 0);
        mDescribeString = b.getString(R.styleable.DescribeEditText_giv_de_describeText);
        mDescribeTextSize = b.getDimensionPixelSize(R.styleable.DescribeEditText_giv_de_describeText_size, mTextSize);
        mDescribeTextColor = b.getColor(R.styleable.DescribeEditText_giv_de_describeText_color, getResources().getColor(R.color.tab_text_enabled));
        mDeLyBackGround = b.getResourceId(R.styleable.DescribeEditText_giv_de_describeText_lybg, 0);
        mDescribeTextWidth = b.getDimensionPixelSize(R.styleable.DescribeEditText_giv_de_describeText_width, UiUtil.dip2px(80));
        mEditText = new EditText(context, attrs, android.R.attr.editTextStyle);
        setBackgroundResource(0);
        b.recycle();
        init(context);
    }

    private void init(Context context) {
        setPadding(0, 0, 0, 0);
        LayoutParams editTextParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        editTextParams.addRule(CENTER_VERTICAL);
        editTextParams.addRule(RelativeLayout.RIGHT_OF, R.id.giv_id_editText);
        if (!StringUtil.isEmpty(mHint)) {
            mEditText.setHint(mHint);
        }
        if (mTextColor != 0) {
            mEditText.setTextColor(mTextColor);
        } else {
            mEditText.setTextColor(Color.BLACK);
        }
        if (mBackGround != 0) {
            mEditText.setBackgroundResource(mBackGround);
        } else {
            mEditText.setBackgroundResource(0);
        }
        if (mDeLyBackGround != 0) {
            setBackgroundResource(mDeLyBackGround);
        } else {
            setBackgroundResource(0);
        }
        if (mTextSize != 0) {
            mEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        }
        mEditText.setPadding(UiUtil.dip2px(5), 0, 0, UiUtil.dip2px(5));
        mEditText.setGravity(Gravity.CENTER_VERTICAL);
        addView(mEditText, editTextParams);

        LayoutParams mDescribeParams = new LayoutParams(mDescribeTextWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        mDescribeParams.addRule(CENTER_VERTICAL);
        mDescribeText = new TextView(context);
        mDescribeText.setId(R.id.giv_id_editText);
        mDescribeText.setTextColor(mDescribeTextColor);
        mDescribeText.setText(mDescribeString);
        mDescribeText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mDescribeTextSize);
        mDescribeParams.setMargins(UiUtil.dip2px(15), 0, 0, UiUtil.dip2px(7));
        addView(mDescribeText, mDescribeParams);
    }

    public void setEditEnable(boolean editEnable){
        mEditText.setEnabled(editEnable);
    }


    public String getText() {
        return mEditText.getText().toString().trim();
    }

    public void setText(CharSequence sequence) {
        mEditText.setText(sequence);
    }
    public void addTextChangedListener(TextWatcher textWatcher){
        mEditText.addTextChangedListener(textWatcher);
    }



}

