package com.base.givon.givonlibrary.common.qualifier;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@StringDef({
        UserTopicType.USER_TOPIC_TYPE,
        UserTopicType.USER_TOPIC_FOLLOW_TYPE,
        UserTopicType.USER_TOPIC_FAVORITE_TYPE
})
public @interface UserTopicType {
    String USER_TOPIC_TYPE = "user_topic_type";

    String USER_TOPIC_FOLLOW_TYPE = "user_topic_follow_type";

    String USER_TOPIC_FAVORITE_TYPE = "user_topic_favorite_type";

}
