package com.base.givon.baseobj.common.qualifier;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@IntDef({
        ClickType.CLICK_TYPE_USER_CLICKED,
        ClickType.CLICK_TYPE_TOPIC_CLICKED,
        ClickType.CLICK_TYPE_NORMAL_CLICKED
})
public @interface ClickType {
    int CLICK_TYPE_USER_CLICKED = 1000;
    int CLICK_TYPE_TOPIC_CLICKED = 1001;
    int CLICK_TYPE_NORMAL_CLICKED = 1002;
}