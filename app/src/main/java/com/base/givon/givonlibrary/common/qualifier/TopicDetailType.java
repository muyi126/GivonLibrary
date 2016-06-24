package com.base.givon.givonlibrary.common.qualifier;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@StringDef({
        TopicDetailType.TOPIC_DETAIL_TYPE_FAVORITE,
        TopicDetailType.TOPIC_DETAIL_TYPE_FAVORITE_DEL,
        TopicDetailType.TOPIC_DETAIL_TYPE_FOLLOW,
        TopicDetailType.TOPIC_DETAIL_TYPE_FOLLOW_DEL,
        TopicDetailType.TOPIC_DETAIL_TYPE_VOTE_UP,
        TopicDetailType.TOPIC_DETAIL_TYPE_VOTE_DOWN
})
public @interface TopicDetailType {

    String TOPIC_DETAIL_TYPE_FAVORITE = "favorite";

    String TOPIC_DETAIL_TYPE_FAVORITE_DEL = "delete_favorite";

    String TOPIC_DETAIL_TYPE_FOLLOW = "follow";

    String TOPIC_DETAIL_TYPE_FOLLOW_DEL = "delete_follow";

    String TOPIC_DETAIL_TYPE_VOTE_UP = "vote_up";

    String TOPIC_DETAIL_TYPE_VOTE_DOWN = "vote_down";
}
