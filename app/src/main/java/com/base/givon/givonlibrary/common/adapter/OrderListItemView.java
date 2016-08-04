package com.base.givon.givonlibrary.common.adapter;


import com.base.givon.givonlibrary.R;
import com.base.givon.givonlibrary.api.entity.element.Order;
import com.base.givon.givonlibrary.api.entity.element.OrderHotelNameList;
import com.base.givon.givonlibrary.common.base.BaseAdapterItemView;
import com.base.givon.givonlibrary.common.qualifier.ClickType;
import com.base.givon.givonlibrary.common.utils.StringUtil;
import com.base.givon.givonlibrary.common.utils.TimeUtil;
import com.base.givon.givonlibrary.common.widget.TextDescribeBodyView;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;

/**
 * Copyright 2016 Givon All rights reserved.
 * Givon PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 * @author givon
 * @version 1.0
 * @七月 16/7/7 下午2:34 - Guzhu
 * @email:muyi126@163.com
 */
public class OrderListItemView extends BaseAdapterItemView<Order> {

    @BindView(R.id.dt_order_num)
    TextDescribeBodyView dt_order_num;
    @BindView(R.id.dt_order_time)
    TextDescribeBodyView dt_order_time;
    @BindView(R.id.dt_order_location)
    TextDescribeBodyView dt_order_location;

    @BindView(R.id.tv_state)
    TextView tv_state;
    @BindView(R.id.tv_sure)
    TextView tv_sure;

    @BindView(R.id.tv_destination)
    TextView tv_destination;
    @BindView(R.id.tv_people_count)
    TextView tv_people_count;
    @BindView(R.id.ly_root)
    LinearLayout ly_root;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.dt_use_time)
    TextDescribeBodyView mDtUseTime;

    public OrderListItemView(Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.order_list_item;
    }

    @Override
    public void bind(Order order) {
        if (null != order) {
            dt_order_num.setText(StringUtil.getDefultString(order.getOrderNumber()));
            tv_people_count.setText(order.getAdultCount() + "大" + order.getChildCount() + "小");
            if (!StringUtil.isEmpty(order.getShiftTime())) {
                String ShiftTime = order.getShiftTime().replace("-", ".");
                dt_order_time.setText(ShiftTime);
            } else {
                dt_order_time.setText("");
            }
            setViewTime(tv_time, order);
//            tv_time.setText("任务时间 " + StringUtil.getDefultString(order.getTaskStartTime()) + "至" + StringUtil.getDefultString(order.getPlanCompleteTime()));
            tv_destination.setText(StringUtil.getDefultString(order.getTaskName()));
            //0 接 1 送 2 市内中转 3 跟团
            switch (order.getTaskType()) {
                case 0://接
                    tv_state.setText("接");
                    tv_state.setBackgroundResource(R.drawable.circle_shape_green);
                    dt_order_location.setDescribeText("目的地:");
                    //0 机场  1 火车站
                    if (order.getTaskPosition() == 1) {
                        dt_order_time.setDescribeText("到站时间:");
                    } else if (order.getTaskPosition() == 0) {
                        dt_order_time.setDescribeText("抵达时间:");
                    }
                    mDtUseTime.setVisibility(GONE);
                    break;
                case 1://送
                    tv_state.setText("送");
                    tv_state.setBackgroundResource(R.drawable.circle_shape_red);
                    if (order.getTaskPosition() == 1) {
                        dt_order_time.setDescribeText("发车时间:");
                    } else if (order.getTaskPosition() == 0) {
                        dt_order_time.setDescribeText("起飞时间:");
                    }
                    dt_order_location.setDescribeText("上车点:");
                    mDtUseTime.setVisibility(GONE);
                    break;
                case 2://2 市内中转
                    tv_state.setText("市");
                    tv_state.setBackgroundResource(R.drawable.circle_shape_blue);
                    dt_order_time.setDescribeText("集合时间:");
                    dt_order_time.setText(StringUtil.getDefultString(order.getTogetherTime()).replace("-", "."));
                    String loc = order.getTogetherPositionListToString();
                    dt_order_location.setText(StringUtil.getDefultString(loc));
                    dt_order_location.setDescribeText("目的地:");
                    mDtUseTime.setVisibility(GONE);

                    break;
                case 3://3 跟团
                    tv_state.setText("团");
                    tv_state.setBackgroundResource(R.drawable.circle_shape_yellow);
                    dt_order_time.setDescribeText("集合时间:");
                    dt_order_time.setText(StringUtil.getDefultString(order.getTogetherTime()).replace("-", "."));
                    dt_order_location.setDescribeText("集合地点:");
                    dt_order_location.setText(StringUtil.getDefultString(order.getTogetherPosition()));
                    mDtUseTime.setVisibility(VISIBLE);
                    mDtUseTime.setText(StringUtil.getDefultString(order.getUseBusStartTime()).replace("-", ".") +
                            " 至" + StringUtil.getDefultString(order.getUseBusEndTime()).replace("-", "."));
                    break;

            }
            //1 待确认 2 已确认 3 已完成
            switch (order.getOrderStatus()) {
                case 1:
                    tv_sure.setVisibility(GONE);
                    break;
                case 2:
                    tv_sure.setVisibility(VISIBLE);
                    break;
                case 3:
                    tv_sure.setVisibility(GONE);
                    break;
            }

            if (null != order.getHotelNameList()) {
                StringBuffer stringBuffer = new StringBuffer();
                for (OrderHotelNameList orderHotelNameList : order.getHotelNameList()) {
                    stringBuffer.append(orderHotelNameList.getHotelName() + ",");
                }
                if (stringBuffer.length() > 0) {
                    stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                    dt_order_location.setText(stringBuffer.toString());
                } else {
                    dt_order_location.setText("无");
                }
            }
            ly_root.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {

                    notifyItemAction(ClickType.CLICK_TYPE_NORMAL_CLICKED);
                }
            });

        }

    }


    public void setViewTime(TextView view, Order order) {
        if (!StringUtil.isEmpty(order.getPlanCompleteTime()) && !StringUtil.isEmpty(order.getTaskStartTime())) {
            String time_1 = TimeUtil.getTimeString2String(order.getTaskStartTime(), "yyyy-MM-dd HH:mm:ss", "yyyy.M.d HH:mm");
            String time_2 = TimeUtil.getTimeString2String(order.getPlanCompleteTime(), "yyyy-MM-dd HH:mm:ss", "yyyy.M.d HH:mm");
            view.setText("任务时间 " + time_1 + "至" + time_2);
        } else {
            view.setText("");
        }
    }
}
