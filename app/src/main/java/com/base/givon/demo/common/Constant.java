package com.base.givon.demo.common;

import android.os.Environment;

import java.io.File;

public final class Constant {
    public static final int PAGE_SIZE = 10;
    public static final String LAUNCH_TAG = "launch_tag";
    public static final String TAG = "_tag";
    public static final String DOWNLOAD_FILE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()
            + File.separator + "File" + File.separator + "download";
}