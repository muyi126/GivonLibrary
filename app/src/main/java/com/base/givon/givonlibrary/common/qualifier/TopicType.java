package com.base.givon.givonlibrary.common.qualifier;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@StringDef({
        TopicType.TOPIC_TYPE_RECENT,
        TopicType.TOPIC_TYPE_VOTE,
        TopicType.TOPIC_TYPE_NOBODY,
        TopicType.TOPIC_TYPE_JOBS
})
public @interface TopicType {
    String TOPIC_TYPE_RECENT = "recent";

    String TOPIC_TYPE_VOTE = "vote";

    String TOPIC_TYPE_NOBODY = "bobody";

    String TOPIC_TYPE_JOBS = "jobs";
}
