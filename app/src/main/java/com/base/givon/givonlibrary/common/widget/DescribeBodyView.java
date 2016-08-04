package com.base.givon.givonlibrary.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/26 上午11:47 - Guzhu
 * @email:muyi126@163.com
 */
public interface DescribeBodyView {
    public View getBodyView(Context context);
    public void initView(Context context, AttributeSet attrs);

}
