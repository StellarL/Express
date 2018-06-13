package com.example.packageapp;

/**
 * Created by 李馨 on 2018/6/9.
 */

public class Order {
    //订单id
    private int _id;
    //下单人账号id
    private int orderId;
    //下单人（收件人）姓名
    private String orderName;
    //下单人（收件人）电话
    private String orderPhone;
    //接单人（账号）id
    private int receiveId;
    //接单人（取货人）姓名
    private String receiveName;
    //接单人（取货人）电话
    private String receivePhone;
    //起点
    private String startPlace;
    //终点
    private String endPlace;
    //金额
    private int payment;
    //类型（小中大）
    private int type;
    //状态（是否接单 0未接单 1接单）
    private int state;
    //提货信息
    private String info;

    public Order() {
    }

    public Order(int _id, String startPlace, String endPlace, int payment, int type) {
        this._id = _id;
        this.startPlace = startPlace;
        this.endPlace = endPlace;
        this.payment = payment;
        this.type = type;
    }

    public Order(int _id, int orderId, String orderName, String orderPhone, int receiveId, String receiveName, String receivePhone, String startPlace, String endPlace, int payment, int type, int state, String info) {
        this._id = _id;
        this.orderId = orderId;
        this.orderName = orderName;
        this.orderPhone = orderPhone;
        this.receiveId = receiveId;
        this.receiveName = receiveName;
        this.receivePhone = receivePhone;
        this.startPlace = startPlace;
        this.endPlace = endPlace;
        this.payment = payment;
        this.type = type;
        this.state = state;
        this.info = info;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderPhone() {
        return orderPhone;
    }

    public void setOrderPhone(String orderPhone) {
        this.orderPhone = orderPhone;
    }

    public int getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(int receiveId) {
        this.receiveId = receiveId;
    }

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getReceivePhone() {
        return receivePhone;
    }

    public void setReceivePhone(String receivePhone) {
        this.receivePhone = receivePhone;
    }

    public String getStartPlace() {
        return startPlace;
    }

    public void setStartPlace(String startPlace) {
        this.startPlace = startPlace;
    }

    public String getEndPlace() {
        return endPlace;
    }

    public void setEndPlace(String endPlace) {
        this.endPlace = endPlace;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Order{" +
                "_id=" + _id +
                ", orderId=" + orderId +
                ", orderName='" + orderName + '\'' +
                ", orderPhone='" + orderPhone + '\'' +
                ", receiveId=" + receiveId +
                ", receiveName='" + receiveName + '\'' +
                ", receivePhone='" + receivePhone + '\'' +
                ", startPlace='" + startPlace + '\'' +
                ", endPlace='" + endPlace + '\'' +
                ", payment=" + payment +
                ", type=" + type +
                ", state=" + state +
                ", info='" + info + '\'' +
                '}';
    }
}
