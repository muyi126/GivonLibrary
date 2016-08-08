package com.base.givon.demo.api.entity.element;

public class OrderShiftData {
    private String shiftTime;
    private String shiftTerminal;
    private String shiftTimeBuffer;
    private String shiftNumber;

    public String getShiftTime() {
        return this.shiftTime;
    }

    public void setShiftTime(String shiftTime) {
        this.shiftTime = shiftTime;
    }

    public String getShiftNumber() {
        return this.shiftNumber;
    }

    public void setShiftNumber(String shiftNumber) {
        this.shiftNumber = shiftNumber;
    }

    public String getShiftTimeBuffer() {
        return shiftTimeBuffer;
    }

    public void setShiftTimeBuffer(String shiftTimeBuffer) {
        this.shiftTimeBuffer = shiftTimeBuffer;
    }

    public String getShiftTerminal() {
        return shiftTerminal;
    }

    public void setShiftTerminal(String shiftTerminal) {
        this.shiftTerminal = shiftTerminal;
    }
}
