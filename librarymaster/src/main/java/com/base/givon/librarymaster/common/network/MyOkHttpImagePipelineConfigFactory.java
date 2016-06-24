package com.base.givon.librarymaster.common.network;
import android.content.Context;

import com.facebook.imagepipeline.core.ImagePipelineConfig;

import okhttp3.OkHttpClient;
/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @六月 16/6/20 下午1:31 - Guzhu
 * @email:muyi126@163.com
 * 自定义fresco网络加载库为okhttp3
 */


public class MyOkHttpImagePipelineConfigFactory {

    public static ImagePipelineConfig.Builder newBuilder(Context context, OkHttpClient okHttpClient) {
        return ImagePipelineConfig.newBuilder(context).setNetworkFetcher(new MyOkHttpNetworkFetcher(okHttpClient));
    }

}
