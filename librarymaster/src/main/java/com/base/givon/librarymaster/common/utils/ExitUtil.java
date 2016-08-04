package com.base.givon.librarymaster.common.utils;



import com.base.givon.librarymaster.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import java.util.Stack;

/**
 * 退出 注销 返回 处理类
 * <p/>
 * Copyright 2015 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @十月 15/10/4 下午2:58 - Guzhu
 * @email:muyi126@163.com
 */

public class ExitUtil {

    public  Stack<Activity> activityList;
    private static ExitUtil instance;
    private boolean flag;

    private ExitUtil() {
    }

    public synchronized static ExitUtil getInstance() {
        if (instance == null) {
            instance = new ExitUtil();
        }
        return instance;
    }

    public void addInstance(Activity activity){
        if(activityList==null){
            activityList=new Stack<Activity>();
        }
        activityList.add(activity);
    }

    /**
     * 获取当前的activity
     * @return
     */
    public Activity currentActivity(){
        Activity activity=activityList.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity(){
        Activity activity=activityList.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity){
        if(activity!=null){
            activityList.remove(activity);
            AlertUtil.clearDialog();
            activity.finish();
            activity=null;
        }
    }
    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls){
        for (Activity activity : activityList) {
            if(activity.getClass().equals(cls) ){
                finishActivity(activity);
            }
        }
    }
    /**
     * 结束所有Activity
     */
    public void ExitApp(){
        if(activityList==null)
            return;
        int listSize = activityList.size();
        for (int i = 0; i < listSize; i++){
            if (null != activityList.get(i)){
                activityList.get(i).finish();
            }
        }
        activityList.clear();
    }

    /**
     * 退出整个应用,带提示功能
     * @param context
     * @param exit_msg_res
     * @return
     */
    public  boolean exitShowDialog(final Context context,int exit_msg_res) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getResources().getString(exit_msg_res));
        builder.setPositiveButton(context.getResources().getString(R.string.ok),
                new OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        flag = true;
                        ExitApp();
                        android.os.Process.killProcess(android.os.Process.myPid());
                        System.exit(0);
                    }
                });
        builder.setNeutralButton(context.getResources().getString(R.string.cancle),
                new OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        flag = false;
                    }
                });
        builder.show();
        return flag;
    }

    /**
     * 退出整个应用Toast显示
     * @param context
     * @return
     */
    public  boolean exitShowToast(Context context) {

        if (!ToolsUtil.isFastDoubleClick(2000)) {
            AlertUtil.showToast(context, "再按一次退出程序");
        } else {
            ExitApp();
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
        return true;

    }

    /**
     * 清除图片缓存暂时不弄进度
     * @param context
     */
    public void clearCache(final Context context){



    }


    /**
     * 清除app缓存
     */
    public void clearAppCache(Context context)
    {

    }
}
