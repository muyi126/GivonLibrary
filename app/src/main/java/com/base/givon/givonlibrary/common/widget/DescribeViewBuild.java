package com.base.givon.givonlibrary.common.widget;


import com.base.givon.givonlibrary.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/26 上午11:57 - Guzhu
 * @email:muyi126@163.com //只去控制RelativeLayout的属性
 */
public class DescribeViewBuild extends RelativeLayout {

    DescribeBodyView mDescribeBodyView;
    private TextView mDescribeText;
    private String mDescribe_String;//描述的文字
    private float mDescribeText_Size;//描述的字体大小
    private int mDesDrawLeft_icon;//描述的左边图片
    private int mDesDrawLeft_icon_padding;//描述图片与文字的距离
    private int mDescribeText_Color;//描述的字体颜色
    private int mDescribeText_Width;//描述文字的宽度 //自适应
    private int mDescribeText_Padding_Right;//描述文字的宽度 与右边View的距离 自适应
    private int mDescribeText_Padding_Left;//描述文字的宽度 与左边View的距离 自适应
    //整个View的bg
    private int mBackGround;
    private int mDescribeText_BackGround;//描述文字的背景

    public DescribeViewBuild(Context context) {
        super(context);
    }

    public DescribeViewBuild(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DescribeViewBuild(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        final TypedArray typedArray = context.obtainStyledAttributes(
                attrs, R.styleable.DescribeBuildView, 0, 0);
        mDescribe_String = typedArray.getString(R.styleable.DescribeBuildView_giv_db_describeText);
        mDescribeText_Size = typedArray.getDimensionPixelOffset(R.styleable.DescribeBuildView_giv_db_describeText_size, 36);
        mDesDrawLeft_icon = typedArray.getResourceId(R.styleable.DescribeBuildView_giv_db_describeText_draw_left, 0);
        mDesDrawLeft_icon_padding = typedArray.getDimensionPixelSize(R.styleable.DescribeBuildView_giv_db_describeText_draw_left_padding, 0);
        mDescribeText_Padding_Right = typedArray.getDimensionPixelSize(R.styleable.DescribeBuildView_giv_db_describeText_padding_right, 0);
        mDescribeText_Padding_Left = typedArray.getDimensionPixelSize(R.styleable.DescribeBuildView_giv_db_describeText_padding_left, 0);
        mDescribeText_Color = typedArray.getColor(R.styleable.DescribeBuildView_giv_db_describeText_color, getResources().getColor(R.color.tab_text_enabled));
        mBackGround = typedArray.getResourceId(R.styleable.DescribeBuildView_giv_db_describeText_rl_bg, 0);
        mDescribeText_BackGround = typedArray.getResourceId(R.styleable.DescribeBuildView_giv_db_describeText_bg, 0);
        mDescribeText_Width = typedArray.getDimensionPixelSize(R.styleable.DescribeBuildView_giv_db_describeText_width, 0);
        typedArray.recycle();
        mDescribeText = new TextView(context);
        mDescribeText.setId(R.id.giv_id_describeText);
        mDescribeText.setTextColor(mDescribeText_Color);
        mDescribeText.setText(mDescribe_String);
        mDescribeText.setGravity(Gravity.CENTER_VERTICAL);
        mDescribeText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mDescribeText_Size);
        setBackgroundResource(0);
        if (mBackGround != 0) {
            setBackgroundResource(mBackGround);
        }
        if (mDescribeText_BackGround != 0) {
            mDescribeText.setBackgroundResource(mDescribeText_BackGround);
        }

        if (mDesDrawLeft_icon != 0) {
            mDescribeText.setCompoundDrawablesWithIntrinsicBounds(mDesDrawLeft_icon, 0, 0, 0);
            mDescribeText.setCompoundDrawablePadding(mDesDrawLeft_icon_padding);
        }
        setPadding(0, 0, 0, 0);
        if (mDescribeText_Padding_Right != 0 || mDescribeText_Padding_Left != 0) {
            mDescribeText.setPadding(mDescribeText_Padding_Left, 0, mDescribeText_Padding_Right, 0);
        }

        LayoutParams mDescribeParams;
        if (mDescribeText_Width != 0) {
            mDescribeParams = new LayoutParams(mDescribeText_Width, ViewGroup.LayoutParams.MATCH_PARENT);
        } else {
            mDescribeParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        mDescribeParams.addRule(CENTER_VERTICAL);
        addView(mDescribeText, mDescribeParams);

    }


    public void setDescribeText(CharSequence sequence) {
        mDescribeText.setText(sequence);
        ViewGroup.LayoutParams mDescribeParams = mDescribeText.getLayoutParams();
        mDescribeParams.width = ViewGroup.LayoutParams.WRAP_CONTENT;
        invalidate();
    }

    public DescribeBodyView getDescribeBodyView() {
        return mDescribeBodyView;
    }

    public void setDescribeBodyView(DescribeBodyView describeBodyView) {
        mDescribeBodyView = describeBodyView;
        LayoutParams viewparams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        viewparams.addRule(CENTER_VERTICAL);
        viewparams.addRule(RelativeLayout.RIGHT_OF, R.id.giv_id_describeText);
        if (mBackGround != 0) {
            mDescribeBodyView.getBodyView(getContext()).setBackgroundResource(0);
        }
        addView(mDescribeBodyView.getBodyView(getContext()), viewparams);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        int i = getChildCount();
        for (int k = 0; k < i; k++) {
            getChildAt(k).setVisibility(visibility);
        }
    }

}
