package com.base.givon.demo.common;



import com.base.givon.demo.ui.view.ActLoginActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Navigator {
    @Inject
    public Navigator() {}

    public void navigateToActLogin(@NonNull Context context) {
        Intent intentToLaunch = ActLoginActivity.getCallingIntent(context);
        context.startActivity(intentToLaunch);
    }
}