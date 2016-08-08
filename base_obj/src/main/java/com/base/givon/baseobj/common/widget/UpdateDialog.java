package com.base.givon.baseobj.common.widget;

import com.base.givon.baseobj.R;
import com.base.givon.baseobj.common.Constant;
import com.base.givon.baseobj.common.utils.FileUtil;
import com.base.givon.baseobj.common.utils.StringUtil;
import com.base.givon.baseobj.common.utils.ToolsUtil;
import com.lzy.okhttputils.OkHttpUtils;
import com.lzy.okhttputils.callback.FileCallback;
import com.lzy.okhttputils.request.BaseRequest;
import com.rey.material.widget.TextView;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/21 下午5:55 - Guzhu
 * @email:muyi126@163.com
 */
public class UpdateDialog extends Dialog {
    private android.widget.TextView tv_content;
    private TextView tv_cancel;
    private TextView tv_sure;
    private android.widget.TextView tv_title;
    private TextDescribeBodyView tv_version_name;
    private TextDescribeBodyView tv_version_size;
    private LinearLayout ly_title;
    private String content;
    private String title;
    private String version_name;
    private long version_size;
    private View line;
    private int content_gravity = -111111;
    private BaseDialogOnClickListener baseDialogOnClickListener;
    private BaseDialogOnBackPressed baseDialogOnBackPressed;
    private BaseDialogType baseDialogType = BaseDialogType.BASE_DIALOG_TYPE_TWO_BUTTON;
    private boolean isDownload = false;

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

    public UpdateDialog(Context context) {
        this(context, R.style.selectorDialog);
    }

    public UpdateDialog(Context context, int theme) {
        super(context, theme);
    }

    protected UpdateDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected UpdateDialog(Context context, boolean cancelable) {
        super(context, cancelable, null);
    }

    public void setContentGravity(int gravity) {
        content_gravity = gravity;
        if (null != tv_content) {
            if (-111111 != gravity) {
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

    public void showData(String title, String content, String version_name, long version_size) {
        this.content = content;
        this.title = title;
        this.version_name = version_name;
        this.version_size = version_size;
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
            if (-111111 != content_gravity) {
                tv_content.setGravity(content_gravity);
            }
        }

        if (null != tv_version_name && !StringUtil.isEmpty(version_name)) {
            tv_version_name.setText(version_name);
        }

        if (null != tv_version_size && 0 != version_size) {
            tv_version_size.setText(FileUtil.getFormatSize(version_size));
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
        setContentView(R.layout.giv_update_dialog);
        ly_title = (LinearLayout) findViewById(R.id.ly_title);
        tv_title = (android.widget.TextView) findViewById(R.id.tv_title);
        tv_content = (android.widget.TextView) findViewById(R.id.tv_content);
        tv_cancel = (TextView) findViewById(R.id.tv_cancel);
        tv_sure = (TextView) findViewById(R.id.tv_sure);
        tv_version_size = (TextDescribeBodyView) findViewById(R.id.tv_version_size);
        tv_version_name = (TextDescribeBodyView) findViewById(R.id.tv_version_name);
        line = findViewById(R.id.line);
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (baseDialogType == BaseDialogType.BASE_DIALOG_TYPE_ONE_BUTTON) {
//                    dismiss();
//                }
                if (null != baseDialogOnClickListener) {
                    baseDialogOnClickListener.onSureOnClick(UpdateDialog.this);
                }
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != baseDialogOnClickListener) {
                    dismiss();
                    if (isDownload) {
                        OkHttpUtils.getInstance().cancelTag(this);
                    }
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


    public void updateProgress(float progress, long total) {
        tv_content.setText(String.format("正在下载：(%s/%s)",
                FileUtil.getFormatSize(progress),
                FileUtil.getFormatSize(total)));
    }

    public void downloadUrl(String url,String fileName) {
        OkHttpUtils.get(url)//
                .tag(this)//
                .execute(new DownloadFileCallBack(Constant.DOWNLOAD_FILE_PATH + File.separator, fileName));
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


    public class DownloadFileCallBack extends FileCallback {

        public DownloadFileCallBack(String destFileDir, String destFileName) {
            super(destFileDir, destFileName);
        }

        @Override
        public void onBefore(BaseRequest request) {
            isDownload = true;
            tv_sure.setEnabled(false);
            tv_content.setText("正在下载中");
        }

        @Override
        public void onResponse(boolean isFromCache, final File file, Request request, Response response) {
            isDownload = false;
            tv_sure.setEnabled(true);
            tv_sure.setText("安装");
            tv_sure.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToolsUtil.installAPK(file, getContext());
                }
            });
            tv_content.setText("下载完成");
            tv_content.setGravity(Gravity.CENTER);
        }

        @Override
        public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
            updateProgress(currentSize, totalSize);
        }

        @Override
        public void onError(boolean isFromCache, Call call, @Nullable Response response, @Nullable Exception e) {
            super.onError(isFromCache, call, response, e);
            isDownload = false;
            tv_content.setText("下载出错");
            tv_sure.setEnabled(true);
        }
    }
}