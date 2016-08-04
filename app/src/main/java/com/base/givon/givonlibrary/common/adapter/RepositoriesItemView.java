package com.base.givon.givonlibrary.common.adapter;

import com.base.givon.givonlibrary.R;
import com.base.givon.givonlibrary.api.entity.element.Repositories;
import com.base.givon.givonlibrary.common.base.BaseAdapterItemView;
import com.base.givon.givonlibrary.module.GitHubModel;
import com.facebook.drawee.view.SimpleDraweeView;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import cn.bingoogolapple.badgeview.BGABadgeRelativeLayout;

import static com.base.givon.givonlibrary.common.qualifier.ClickType.CLICK_TYPE_TOPIC_CLICKED;
import static com.base.givon.givonlibrary.common.qualifier.ClickType.CLICK_TYPE_USER_CLICKED;

/**
 *
 *
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/3 下午2:49 - Guzhu
 * @email:muyi126@163.com
 */
public class RepositoriesItemView extends BaseAdapterItemView<Repositories>{

    @BindView(R.id.bga_rlyt_content)
    BGABadgeRelativeLayout topicContentView;

    @BindView(R.id.tv_title)
    TextView titleView;

    @BindView(R.id.sdv_avatar)
    SimpleDraweeView avatarView;

    @BindView(R.id.tv_description)
    TextView descriptionView;
    public RepositoriesItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.repos_item;
    }

    @Override
    public void bind(Repositories repositories) {
        Uri avatarUri = Uri.parse(repositories.getOwner().getAvatarUrl());
        avatarView.setImageURI(avatarUri);
        titleView.setText(repositories.getName());
        topicContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemAction(CLICK_TYPE_TOPIC_CLICKED);
            }
        });

        avatarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyItemAction(CLICK_TYPE_USER_CLICKED);
            }
        });

    }
}
