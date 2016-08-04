package com.base.givon.librarymaster.common.widget;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/8 上午7:53 - Guzhu
 * @email:muyi126@163.com
 */


import com.base.givon.librarymaster.R;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class WaitingDialog extends Dialog implements View.OnClickListener {
    private TextView tvMsg;
    private ImageView ivClose;
    private ImageView ivLoading;
    private boolean cancelEnable =false;
    private LinearLayout ly_close;

    public WaitingDialog(Context ctx) {
        super(ctx);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initViews(ctx);
    }

    public WaitingDialog(Context ctx, boolean cancelEnable) {
        super(ctx);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.cancelEnable = cancelEnable;
        initViews(ctx);
    }

    public void initViews(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_waiting_dialog, null);
        tvMsg = (TextView) view.findViewById(R.id.tv_msg);
        ivClose = (ImageView) view.findViewById(R.id.iv_close);
        ivLoading = (ImageView) view.findViewById(R.id.iv_loading);
        ly_close = (LinearLayout) view.findViewById(R.id.ly_close);
        ivClose.setOnClickListener(this);
        if (!cancelEnable) {
            ly_close.setVisibility(View.GONE);
            setCancelable(false);
            setOnKeyListener(new OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    if (keyCode == KeyEvent.KEYCODE_SEARCH) {
                        return true;
                    } else {
                        return false; // 默认返回 false
                    }
                }
            });
        }
        setContentView(view);
        Window window = getWindow();
        window.setGravity(Gravity.CENTER);
        // //设置SelectPicPopupWindow弹出窗体动画效果
        // window.setWindowAnimations(R.style.PopupAnimation_SetTip);
        // //设置SelectPicPopupWindow弹出窗体的背景
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        LayoutParams lp = window.getAttributes();
        // 设置SelectPicPopupWindow弹出窗体的宽
        lp.width = LayoutParams.WRAP_CONTENT;
        // 设置SelectPicPopupWindow弹出窗体的高
        lp.height = LayoutParams.WRAP_CONTENT;

        AnimationDrawable ad = (AnimationDrawable) ivLoading.getDrawable();
        ad.start();
    }

    public void setMessage(String msg) {
        tvMsg.setText(msg);
    }

    @Override
    public void onClick(View v) {
        if (v == ivClose) {
            dismiss();
        }
    }
}