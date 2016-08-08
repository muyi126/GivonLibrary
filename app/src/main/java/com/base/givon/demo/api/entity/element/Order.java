package com.base.givon.demo.api.entity.element;

import java.util.List;

public class Order {
    private String orderNumber;
    private int adultCount;
    private int taskPosition;//0 机场  1 火车站
    private List<OrderShiftData> shiftData;
    private int orderStatus;//1 待确认 2 已确认 3 已完成
    private int childCount;
    private String remark;
    private String shiftTime;
    private List<com.base.givon.demo.api.entity.element.OrderTouristGroupList> touristGroupList;
    private int taskType;//0 接 1 送 2 市内中转 3 跟团
    private int actionType;//0 下单 1 撤单
    private int isRead;//0未读 1已读
    private String realCompleteTime;
    private String tripDate;
    private String taskName;
    private String id;
    private String driverOrderId;//消息中心列表的的 orderId
    private String planCompleteTime;//计划结束时间
    private String tripTypeName;
    private String taskStartTime;//任务开始时间
    private String useBusStartTime;//用车时间
    private String useBusEndTime;//结束用车时间
    private String createTime;
    private String taskEndTime;
    private String togetherTime;//集合时间
    private List<com.base.givon.demo.api.entity.element.OrderHotelNameList> hotelNameList;
    private List<com.base.givon.demo.api.entity.element.OrderTogetherPositionList> togetherPositionList;//市区中转目的地集合
    private String togetherPosition;//跟团  集合地
    private String guideName;//跟团  导游名字
    private String guideMobileNumber;//跟团  导游电话

    public String getOrderNumber() {
        return this.orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getAdultCount() {
        return this.adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public int getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getChildCount() {
        return this.childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getTaskType() {
        return this.taskType;
    }

    public void setTaskType(int taskType) {
        this.taskType = taskType;
    }

    public String getRealCompleteTime() {
        return this.realCompleteTime;
    }

    public void setRealCompleteTime(String realCompleteTime) {
        this.realCompleteTime = realCompleteTime;
    }

    public String getTripDate() {
        return this.tripDate;
    }

    public void setTripDate(String tripDate) {
        this.tripDate = tripDate;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlanCompleteTime() {
        return this.planCompleteTime;
    }

    public void setPlanCompleteTime(String planCompleteTime) {
        this.planCompleteTime = planCompleteTime;
    }

    public String getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }


    public int getTaskPosition() {
        return taskPosition;
    }

    public void setTaskPosition(int taskPosition) {
        this.taskPosition = taskPosition;
    }

    public List<OrderShiftData> getShiftData() {
        return shiftData;
    }

    public void setShiftData(List<OrderShiftData> shiftData) {
        this.shiftData = shiftData;
    }

    public List<com.base.givon.demo.api.entity.element.OrderTouristGroupList> getTouristGroupList() {
        return touristGroupList;
    }

    public void setTouristGroupList(List<com.base.givon.demo.api.entity.element.OrderTouristGroupList> touristGroupList) {
        this.touristGroupList = touristGroupList;
    }

    public List<com.base.givon.demo.api.entity.element.OrderHotelNameList> getHotelNameList() {
        return hotelNameList;
    }

    public void setHotelNameList(List<com.base.givon.demo.api.entity.element.OrderHotelNameList> hotelNameList) {
        this.hotelNameList = hotelNameList;
    }

    public String getTaskStartTime() {
        return taskStartTime;
    }

    public void setTaskStartTime(String taskStartTime) {
        this.taskStartTime = taskStartTime;
    }

    public int getActionType() {
        return actionType;
    }

    public void setActionType(int actionType) {
        this.actionType = actionType;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getTripTypeName() {
        return tripTypeName;
    }

    public void setTripTypeName(String tripTypeName) {
        this.tripTypeName = tripTypeName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTaskEndTime() {
        return taskEndTime;
    }

    public void setTaskEndTime(String taskEndTime) {
        this.taskEndTime = taskEndTime;
    }

    public String getTogetherTime() {
        return togetherTime;
    }

    public void setTogetherTime(String togetherTime) {
        this.togetherTime = togetherTime;
    }

    public List<com.base.givon.demo.api.entity.element.OrderTogetherPositionList> getTogetherPositionList() {
        return togetherPositionList;
    }

    public void setTogetherPositionList(List<com.base.givon.demo.api.entity.element.OrderTogetherPositionList> togetherPositionList) {
        this.togetherPositionList = togetherPositionList;
    }

    public String getUseBusStartTime() {
        return useBusStartTime;
    }

    public void setUseBusStartTime(String useBusStartTime) {
        this.useBusStartTime = useBusStartTime;
    }

    public String getUseBusEndTime() {
        return useBusEndTime;
    }

    public void setUseBusEndTime(String useBusEndTime) {
        this.useBusEndTime = useBusEndTime;
    }

    public String getTogetherPosition() {
        return togetherPosition;
    }

    public void setTogetherPosition(String togetherPosition) {
        this.togetherPosition = togetherPosition;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public String getGuideMobileNumber() {
        return guideMobileNumber;
    }

    public void setGuideMobileNumber(String guideMobileNumber) {
        this.guideMobileNumber = guideMobileNumber;
    }

    public String getDriverOrderId() {
        return driverOrderId;
    }

    public void setDriverOrderId(String driverOrderId) {
        this.driverOrderId = driverOrderId;
    }

    public String getTogetherPositionListToString() {
        if (null == togetherPositionList) {
            return null;
        }
        if (togetherPositionList.size() == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (com.base.givon.demo.api.entity.element.OrderTogetherPositionList positionList : togetherPositionList) {
            stringBuffer.append(positionList.getTogetherPosition());
            stringBuffer.append("、");
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }
}
