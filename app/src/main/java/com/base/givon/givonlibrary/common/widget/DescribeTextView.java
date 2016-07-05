package com.base.givon.givonlibrary.common.widget;

import com.base.givon.givonlibrary.R;
import com.base.givon.givonlibrary.common.utils.StringUtil;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
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
public class DescribeTextView extends RelativeLayout {
    private int mDescribeTextColor;
    private String mDescribeString;
    private int mDescribeTextSize;
    private int mDescribeTextWidth;
    private int mDescribeTextHeight;
    private TextView mTextView;
    private TextView mDescribeText;
    private int mBackGround;
    private String mHint;
    private int mTextColor;
    private int mTextSize = 15;

    public DescribeTextView(Context context) {
        super(context);
    }

    public DescribeTextView(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.textViewStyle);
    }

    public DescribeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.TextView, 0, 0);
        mHint = a.getString(R.styleable.TextView_android_hint);
        mBackGround = a.getResourceId(R.styleable.TextView_android_background, 0);
        mTextColor = a.getColor(R.styleable.TextView_android_textColor, getResources().getColor(R.color.black_55));
        mTextSize = a.getDimensionPixelSize(R.styleable.TextView_android_textSize, mTextSize);
        a.recycle();
        final TypedArray b = context.obtainStyledAttributes(
                attrs, R.styleable.DescribeTextView, 0, 0);
        mDescribeString = b.getString(R.styleable.DescribeTextView_giv_dt_describeText);
        mDescribeTextSize = b.getDimensionPixelSize(R.styleable.DescribeTextView_giv_dt_describeText_size, mTextSize);
        mDescribeTextColor = b.getColor(R.styleable.DescribeTextView_giv_dt_describeText_color, getResources().getColor(R.color.black_55));
        mDescribeTextWidth = b.getDimensionPixelSize(R.styleable.DescribeTextView_giv_dt_describeText_width, Utils.dip2px(80));
        mTextView = new TextView(context, attrs, android.R.attr.textViewStyle);
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
            mTextView.setHint(mHint);
        }
        if (mTextColor != 0) {
            mTextView.setTextColor(mTextColor);
        } else {
            mTextView.setTextColor(Color.BLACK);
        }
        if (mBackGround != 0) {
            mTextView.setBackgroundResource(mBackGround);
        } else {
            mTextView.setBackgroundResource(0);
        }
        if (mTextSize != 0) {
            mTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        }
        mTextView.setPadding(Utils.dip2px(5), 0, 0, Utils.dip2px(5));
        mTextView.setGravity(Gravity.CENTER_VERTICAL);
        addView(mTextView, editTextParams);
        LayoutParams mDescribeParams = new LayoutParams(mDescribeTextWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
        mDescribeParams.addRule(CENTER_VERTICAL);
        mDescribeText = new TextView(context);
        mDescribeText.setId(R.id.giv_id_editText);
        mDescribeText.setTextColor(mDescribeTextColor);
        mDescribeText.setText(mDescribeString);
        mDescribeText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mDescribeTextSize);
        mDescribeParams.setMargins(Utils.dip2px(15), 0, 0, Utils.dip2px(7));
        addView(mDescribeText, mDescribeParams);
        setClickable(false);
    }


    public String getText() {
        return mTextView.getText().toString().trim();
    }

    public void setText(CharSequence sequence) {
        mTextView.setText(sequence);
    }


    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        mTextView.setOnClickListener(l);
        mDescribeText.setOnClickListener(l);
    }
}
