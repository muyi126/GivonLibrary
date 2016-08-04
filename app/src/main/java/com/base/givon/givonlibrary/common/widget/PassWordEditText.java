package com.base.givon.givonlibrary.common.widget;


import com.base.givon.givonlibrary.R;
import com.base.givon.givonlibrary.common.utils.StringUtil;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;


/**
 * 带显示密码按钮的EditText
 * <p/>
 * Copyright 2015 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @十月 15/10/7 上午10:40 - Guzhu
 * @email:muyi126@163.com
 */
public class PassWordEditText extends RelativeLayout implements Switch.OnCheckedChangeListener {
    /**
     * 删除按钮的引用
     */
    private Drawable mClearDrawable;
    /**
     * 控件是否有焦点
     */
    private boolean hasFoucs;
    private Switch mSwitch;
    private EditText mPsdEditText;
    private int mBackGround;
    private String mHint;
    private int mTextColor;
    private int mTextSize = 15;
    private boolean isSwitchEnable;

    public PassWordEditText(Context context) {
        this(context, null);
    }

    public PassWordEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public void setSwitchColor(int color) {
        mSwitch.setmThumbColors(Switch.createThumbColors(color, 0x66000000));
        mSwitch.setmTrackColors(Switch.createTrackColors(color, 0x33000000));
    }

//    set：属性值的集合
//
//    　　　　attrs：我们要获取的属性的资源ID的一个数组，如同ContextProvider中请求数据库时的Projection数组，就是从一堆属性中我们希望查询什么属性的值
//
//    　　　　defStyleAttr：这个是当前Theme中的一个attribute，是指向style的一个引用，当在layout xml中和style中都没有为View指定属性时，会从Theme中这个attribute指向的Style中查找相应的属性值，这就是defStyle的意思，如果没有指定属性值，就用这个值，所以是默认值，但这个attribute要在Theme中指定，且是指向一个Style的引用，如果这个参数传入0表示不向Theme中搜索默认值
//
//    　　　　defStyleRes：这个也是指向一个Style的资源ID，但是仅在defStyleAttr为0或defStyleAttr不为0但Theme中没有为defStyleAttr属性赋值时起作用
//
//    　　链接中对这个函数说明勉强过得去，这里简要概括一下。对于一个属性可以在多个地方指定它的值，如XML直接定义，style，Theme，而这些位置定义的值有一个优先级，按优先级从高到低依次是：
//
//    直接在XML中定义>style定义>由defStyleAttr和defStyleRes指定的默认值>直接在Theme中指定的值
//
//    　　看这个关系式就比较明白了，defStyleAttr和defStyleRes在前面的参数说明中已经说了，“直接在Theme中指定的值”的意思在下面的示代码中可以看到。

    public PassWordEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        final TypedArray a = context.obtainStyledAttributes(
                attrs, R.styleable.TextView, 0, 0);
        mHint = a.getString(R.styleable.TextView_android_hint);
        mBackGround = a.getResourceId(R.styleable.TextView_android_background, 0);
        mTextColor = a.getColor(R.styleable.TextView_android_textColor, 0);
        mTextSize = a.getDimensionPixelSize(R.styleable.TextView_android_textSize, mTextSize);
        mPsdEditText = new EditText(context, attrs, android.R.attr.editTextStyle);
        a.recycle();
        final TypedArray b = context.obtainStyledAttributes(
                attrs, R.styleable.PassWordEditText, 0, 0);
        isSwitchEnable = b.getBoolean(R.styleable.PassWordEditText_giv_pe_isEnableSwitch, true);
        init(context);
    }


    private void init(Context context) {
        setPadding(0, 0, 0, 0);
        LayoutParams editTextParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        editTextParams.addRule(CENTER_VERTICAL);
        editTextParams.addRule(RelativeLayout.LEFT_OF, R.id.psd_checkBox);
        if (!StringUtil.isEmpty(mHint)) {
            mPsdEditText.setHint(mHint);
        }
        if (mTextColor != 0) {
            mPsdEditText.setTextColor(mTextColor);
        } else {
            mPsdEditText.setTextColor(Color.BLACK);
        }
        //不设置背景
        mPsdEditText.setBackgroundResource(0);
//        if (mBackGround != 0) {
//            mPsdEditText.setBackgroundResource(mBackGround);
//        } else {
//            mPsdEditText.setBackgroundResource(0);
//        }
        if (mTextSize != 0) {
            mPsdEditText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        }
        addView(mPsdEditText, editTextParams);
        LayoutParams mSwitchxParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mSwitchxParams.addRule(CENTER_VERTICAL);
        mSwitchxParams.addRule(ALIGN_PARENT_RIGHT);
        //materail的控件暂时没有加入
        mSwitch = new Switch(context, R.style.Material_Widget_Switch);
        mSwitch.setId(R.id.psd_checkBox);
        mSwitchxParams.setMargins(0, 0, 0, 0);
        mSwitch.setOnCheckedChangeListener(this);
        addView(mSwitch, mSwitchxParams);
        if (isSwitchEnable) {
            mSwitch.setVisibility(VISIBLE);
        } else {
            mSwitch.setVisibility(GONE);
        }
        initEditStuts();
    }


    private void initEditStuts() {
        if (mSwitch.isChecked()) {
            //如果选中，显示密码
            mPsdEditText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        } else {
            //否则隐藏密码
            mPsdEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        mPsdEditText.setSelection(mPsdEditText.length());
    }

    @Override
    public void onCheckedChanged(Switch view, boolean checked) {
        if (view.getId() == R.id.psd_checkBox) {
            initEditStuts();
        }

    }

    public String getText() {
        return mPsdEditText.getText().toString().trim();
    }

    public void setText(CharSequence sequence) {
        mPsdEditText.setText(sequence);
    }


    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
        if (gainFocus) {
            mPsdEditText.setFocusable(true);
            mPsdEditText.setFocusableInTouchMode(true);
            mPsdEditText.requestFocus();
            mPsdEditText.requestFocusFromTouch();
            if (getText().length() > 0) {
                mPsdEditText.setSelection(getText().length());
            }
        }
    }
}
