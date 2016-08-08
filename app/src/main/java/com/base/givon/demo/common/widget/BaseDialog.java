package com.base.givon.demo.common.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.base.givon.baseobj.R;
import com.base.givon.baseobj.common.utils.StringUtil;
import com.rey.material.widget.TextView;


/**
 * Created by givon on 15/7/31.
 */
public class BaseDialog extends Dialog {
    private android.widget.TextView tv_content;
    private TextView tv_cancel;
    private TextView tv_sure;
    private android.widget.TextView tv_title;
    private LinearLayout ly_title;
    private String content;
    private String title;
    private View line;
    private int content_gravity=-111111;
    private BaseDialogOnClickListener baseDialogOnClickListener;
    private BaseDialogOnBackPressed baseDialogOnBackPressed;
    private BaseDialogType baseDialogType = BaseDialogType.BASE_DIALOG_TYPE_TWO_BUTTON;

    public BaseDialogType getBaseDialogType() {
        return baseDialogType;
    }

    public void setBaseDialogType(BaseDialogType baseDialogType) {
        this.baseDialogType = baseDialogType;
    }

    public BaseDialogOnClickListener getBaseDialogOnClickListener() {
        return baseDialogOnClickListener;
    }

    public void setBaseDialogOnClickListener(BaseDialogOnClickListener baseDialogOnClickListener) {
        this.baseDialogOnClickListener = baseDialogOnClickListener;
    }

    public BaseDialogOnBackPressed getBaseDialogOnBackPressed() {
        return baseDialogOnBackPressed;
    }

    public void setBaseDialogOnBackPressed(BaseDialogOnBackPressed baseDialogOnBackPressed) {
        this.baseDialogOnBackPressed = baseDialogOnBackPressed;
    }

    public BaseDialog(Context context) {
        this(context, R.style.selectorDialog);
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
    }

    protected BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public void setContentGravity(int gravity) {
        content_gravity = gravity;
        if (null != tv_content) {
            if(-111111!=gravity){
                tv_content.setGravity(gravity);
            }
        }
    }

    public void showData(String content) {
        showData(null, content);
    }

    public void showData(String title, String content) {
        this.content = content;
        this.title = title;
        initView();
        super.show();
    }


    @Override
    public void onBackPressed() {
        if (null == baseDialogOnBackPressed) {

            super.onBackPressed();
        } else {
            baseDialogOnBackPressed.onBackPressed(this);
        }
    }

    public void setTitle(String title) {
        this.title = title;
        initView();
    }

    private void initView() {
        if (null == tv_content) {
            return;
        }
        if (null != tv_content && !StringUtil.isEmpty(content)) {
            tv_content.setText(content);
        }
        if (null != tv_title && !StringUtil.isEmpty(title)) {
            tv_title.setText(title);
            ly_title.setVisibility(View.VISIBLE);
        }
        if (null != tv_content) {
            if(-111111!=content_gravity){
                tv_content.setGravity(content_gravity);
            }
        }
        switch (baseDialogType) {
            case BASE_DIALOG_TYPE_ONE_BUTTON:
                line.setVisibility(View.GONE);
                tv_cancel.setVisibility(View.GONE);
                tv_sure.setVisibility(View.VISIBLE);
                break;
            case BASE_DIALOG_TYPE_TWO_BUTTON:
                line.setVisibility(View.VISIBLE);
                tv_cancel.setVisibility(View.VISIBLE);
                tv_sure.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_giv_dialog);
        ly_title = (LinearLayout) findViewById(R.id.ly_title);
        tv_title = (android.widget.TextView) findViewById(R.id.tv_title);
        tv_content = (android.widget.TextView) findViewById(R.id.tv_content);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_sure = (TextView) findViewById(R.id.tv_sure);
        line = findViewById(R.id.line);
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (baseDialogType == BaseDialogType.BASE_DIALOG_TYPE_ONE_BUTTON) {
                    dismiss();
                }
                if (null != baseDialogOnClickListener) {
                    baseDialogOnClickListener.onSureOnClick(BaseDialog.this);
                }
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != baseDialogOnClickListener) {
                    dismiss();
                    baseDialogOnClickListener.onCancelOnClick();
                }
            }
        });
        initView();
    }


    public void enableTitle() {
        if (null != ly_title && !StringUtil.isEmpty(title)) {
            ly_title.setVisibility(View.VISIBLE);
            tv_title.setText(title);
        }
    }

    public void disEnableTitle() {
        if (null != ly_title) {
            ly_title.setVisibility(View.GONE);
        }
    }

    public enum BaseDialogType {
        BASE_DIALOG_TYPE_ONE_BUTTON, BASE_DIALOG_TYPE_TWO_BUTTON

    }

    public interface BaseDialogOnClickListener {
        void onSureOnClick(Dialog v);

        void onCancelOnClick();
    }

    public interface BaseDialogOnBackPressed {
        void onBackPressed(Dialog v);
    }
}
