package com.base.givon.givonlibrary.common.widget;

/**
 * <p/>
 * Copyright 2015 JiaJun.Ltd  All rights reserved.
 * SiChuan YCKX.Ltd PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author Givon
 * @version 1.0
 * @八月 15/8/31 下午2:16 - Guzhu
 * @email:muyi126@163.com
 */

import kankan.wheel.widget.OnWheelChangedListener;
import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.ArrayWheelAdapter;
import kankan.wheel.widget.adapters.NumericWheelAdapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.base.givon.givonlibrary.R;
import com.base.givon.givonlibrary.common.utils.TimeUtil;
import com.rey.material.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SelectDateDialog extends Dialog {
    private Context context;
    private Button bt_sure;
    private Button bt_cancel;
    private int MAX_YEAR = 10;
    private int MAIN_YEAR;
    private WheelView day;
    private WheelView year;
    private WheelView month;
    private OnSelectDateListener onSelectDateListener;
    private String[] days;
    private String[] months;
    private String[] years;

    public OnSelectDateListener getOnSelectDateListener() {
        return onSelectDateListener;
    }

    public void setOnSelectDateListener(OnSelectDateListener onSelectDateListener) {
        this.onSelectDateListener = onSelectDateListener;
    }

    public SelectDateDialog(Context context) {
        super(context);
        this.context = context;
    }

    public SelectDateDialog(Context context, int theme) {
        super(context, theme);
        this.context = context;
    }

    protected SelectDateDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.date_layout);
        getWindow().setGravity(Gravity.BOTTOM);
        bt_sure = (Button) findViewById(R.id.bt_sure);
        bt_cancel = (Button) findViewById(R.id.bt_cancel);
        Calendar calendar = Calendar.getInstance();

        month = (WheelView) findViewById(R.id.month);
        year = (WheelView) findViewById(R.id.year);
        day = (WheelView) findViewById(R.id.day);

        OnWheelChangedListener listener = new OnWheelChangedListener() {
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                updateDays(year, month, day);
            }
        };

        // month
        int curMonth = calendar.get(Calendar.MONTH);
        months = new String[]{"-", "1月", "2月", "3月", "4月", "5月",
                "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
        month.setViewAdapter(new ArrayWheelAdapter<>(context, months));
        month.setCurrentItem(curMonth + 1);
        month.addChangingListener(listener);

        // year
        int curYear = calendar.get(Calendar.YEAR);
//        MAIN_YEAR = curYear - MAX_YEAR;
        years = new String[]{"-", "2006年", "2007年", "2008年", "2009年", "2010年",
                "2011年", "2012年", "2013年", "2014年", "2015年", "2016年"};
//        year.setViewAdapter(new NumericWheelAdapter(context, MAIN_YEAR, curYear));
        year.setViewAdapter(new ArrayWheelAdapter<>(context, years));
        year.setCurrentItem(years.length - 1);
        year.addChangingListener(listener);

        //day
        updateDays(year, month, day);
        day.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);
        bt_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = getFinalDate();
                if (null != onSelectDateListener) {
                    onSelectDateListener.onSelectFinish(date);
                    if (isShowing()) {
                        dismiss();
                    }
                }

            }
        });
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onSelectDateListener) {
                    onSelectDateListener.onSelectCancel();
                    if (isShowing()) {
                        dismiss();
                    }
                }
            }
        });

        setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (null != onSelectDateListener) {
                    onSelectDateListener.onSelectCancel();
                    if (isShowing()) {
                        dismiss();
                    }
                }
            }
        });
    }

    /**
     * Updates day wheel. Sets max days according to selected month and year
     */
    void updateDays(WheelView year, WheelView month, WheelView day) {

        Calendar datecalendar = Calendar.getInstance();
        GregorianCalendar calendar = new GregorianCalendar(datecalendar.get(Calendar.YEAR), 0, 0);
        calendar.clear(Calendar.DAY_OF_MONTH);
        if (0 == year.getCurrentItem()) {
            days = new String[]{"-"};
            day.setViewAdapter(new ArrayWheelAdapter<>(context, days));
            day.setCurrentItem(0, true);
            months = new String[]{"-"};
            month.setViewAdapter(new ArrayWheelAdapter<>(context, months));
            month.setCurrentItem(0, true);
        } else {
            if (months.length < 6) {
                months = new String[]{"-", "1月", "2月", "3月", "4月", "5月",
                        "6月", "7月", "8月", "9月", "10月", "11月", "12月"};
                month.setViewAdapter(new ArrayWheelAdapter<>(context, months));
            }

            int yearInt = Integer.valueOf(years[year.getCurrentItem()].replace("年", ""));
            calendar.set(Calendar.YEAR, yearInt);
            //如果month.getCurrentItem() ==0 那么不显示day
            if (month.getCurrentItem() != 0) {
                calendar.add(Calendar.MONTH, month.getCurrentItem() - 1);
                int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//        CLog.e("updateDays", calendar.get(Calendar.YEAR) + "  " + calendar.get(Calendar.MONTH) + "  " + calendar.get(Calendar.DAY_OF_MONTH) + "  " + maxDays);
//        day.setViewAdapter(new NumericWheelAdapter(context, 1, maxDays));
                days = new String[maxDays + 1];
                for (int i = 0; i <= maxDays; i++) {
                    if (i == 0) {
                        days[i] = "-";
                    } else {
                        days[i] = i + "日";
                    }

                }
                day.setViewAdapter(new ArrayWheelAdapter<>(context, days));
                int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
                day.setCurrentItem(curDay - 1, true);
            } else {
                days = new String[]{"-"};
                day.setViewAdapter(new ArrayWheelAdapter<>(context, days));
                day.setCurrentItem(0, true);
            }


        }


    }


    public void setDate(String date) {
        Date dates = TimeUtil.ParseStringtoDate(date, "yyyy-MM-dd");
        Calendar datecalendar = Calendar.getInstance();
        datecalendar.clear();
        datecalendar.setTimeInMillis(dates.getTime());
        int year = datecalendar.get(Calendar.YEAR);
        int moth = datecalendar.get(Calendar.MONTH);
        int day = datecalendar.get(Calendar.DAY_OF_MONTH);
        this.year.setCurrentItem(year - MAIN_YEAR);
        this.month.setCurrentItem(moth);
        this.day.setCurrentItem(day - 1);

    }

    public String getFinalDate() {
//        int fYear = year.getCurrentItem() + MAIN_YEAR;
        String fYear = years[year.getCurrentItem()].replace("年", "");
        String fMonth = months[month.getCurrentItem()].replace("月", "");
        String fDay = days[day.getCurrentItem()].replace("日", "");
        if (fYear.equals("-")) {
            return "";
        }
        String date = fYear + "";
        if (!fMonth.equals("-")) {
            date = date + "-" + fMonth;
        }
        if (!fDay.equals("-")) {
            date = date + "-" + fDay;
        }
        return date;

    }


//    /**
//     * Adapter for numeric wheels. Highlights the current value.
//     */
//    private class DateNumericAdapter extends NumericWheelAdapter {
//        // Index of current item
//        int currentItem;
//        // Index of item to be highlighted
//        int currentValue;
//
//        /**
//         * Constructor
//         */
//        public DateNumericAdapter(Context context, int minValue, int maxValue, int current) {
//            super(context, minValue, maxValue);
//            this.currentValue = current;
//            setTextSize(16);
//        }
//
//        @Override
//        protected void configureTextView(TextView view) {
//            super.configureTextView(view);
//            if (currentItem == currentValue) {
//                view.setTextColor(0xFF0000F0);
//            }
//            view.setTypeface(Typeface.SANS_SERIF);
//        }
//
//        @Override
//        public View getItem(int index, View cachedView, ViewGroup parent) {
//            currentItem = index;
//            return super.getItem(index, cachedView, parent);
//        }
//    }
//
//    /**
//     * Adapter for string based wheel. Highlights the current value.
//     */
//    private class DateArrayAdapter extends ArrayWheelAdapter<String> {
//        // Index of current item
//        int currentItem;
//        // Index of item to be highlighted
//        int currentValue;
//
//        /**
//         * Constructor
//         */
//        public DateArrayAdapter(Context context, String[] items, int current) {
//            super(context, items);
//            this.currentValue = current;
//            setTextSize(16);
//        }
//
//        @Override
//        protected void configureTextView(TextView view) {
//            super.configureTextView(view);
//            if (currentItem == currentValue) {
//                view.setTextColor(0xFF0000F0);
//            }
//            view.setTypeface(Typeface.SANS_SERIF);
//        }
//
//        @Override
//        public View getItem(int index, View cachedView, ViewGroup parent) {
//            currentItem = index;
//            return super.getItem(index, cachedView, parent);
//        }
//    }

    public interface OnSelectDateListener {
        void onSelectCancel();

        void onSelectFinish(String data);
    }
}
