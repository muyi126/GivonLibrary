package com.base.givon.givonlibrary.common.qualifier;


import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

@Retention(SOURCE)
@StringDef({
        MainTabType.MAIN_TAB_TYPE_RECOMMENDED,
        MainTabType.MAIN_TAB_TYPE_TOPICS,
        MainTabType.MAIN_TAB_TYPE_WIKI,
        MainTabType.MAIN_TAB_TYPE_ME
})
public @interface MainTabType {
    String MAIN_TAB_TYPE_RECOMMENDED = "recommended";

    String MAIN_TAB_TYPE_TOPICS = "topics";

    String MAIN_TAB_TYPE_WIKI = "wiki";

    String MAIN_TAB_TYPE_ME = "me";
}
