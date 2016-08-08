package com.base.givon.demo.api.entity.element;

import java.util.List;

public class OrderTouristGroupList {
    private String shiftTime;
    private List<OrderTouristGroupListMemberList> memberList;
    private int adultCount;
    private String shiftNumber;
    private String memberName;
    private int childCount;
    private String hotelAddress;
    private String remark;
    private String hotelName;
    private String togetherPosition;//市转  目的地
    private String guideMobileNumber;//市转  导游电话
    private String togetherTime;//市转  集合时间
    private String guideName;//市转  导游名称

    public String getShiftTime() {
        return this.shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    public List<OrderTouristGroupListMemberList> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<OrderTouristGroupListMemberList> memberList) {
        this.memberList = memberList;
    }

    public int getAdultCount() {
        return this.adultCount;
    }

    public void setAdultCount(int adultCount) {
        this.adultCount = adultCount;
    }

    public String getShiftNumber() {
        return this.shiftNumber;
    }

    public void setShiftNumber(String shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public String getMemberName() {
        return this.memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getChildCount() {
        return this.childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public String getHotelAddress() {
        return this.hotelAddress;
    }

    public void setHotelAddress(String hotelAddress) {
        this.hotelAddress = hotelAddress;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getHotelName() {
        return this.hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getTogetherPosition() {
        return togetherPosition;
    }

    public void setTogetherPosition(String togetherPosition) {
        this.togetherPosition = togetherPosition;
    }

    public String getGuideMobileNumber() {
        return guideMobileNumber;
    }

    public void setGuideMobileNumber(String guideMobileNumber) {
        this.guideMobileNumber = guideMobileNumber;
    }

    public String getTogetherTime() {
        return togetherTime;
    }

    public void setTogetherTime(String togetherTime) {
        this.togetherTime = togetherTime;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }
}
