package com.realname.marketclient.bean;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private Integer orderId;


    //@JSONField(format="yyyy-MM-dd HH:mm:ss")
    private String orderDowndate;

    private BigDecimal orderPrice;

    private Integer orderState;

    private String orderAddress;

    private String orderName;

    private String orderTel;

    private Integer postMethod;

    private Integer userId;

    private List<Orderitem> orderitemList;

    public List<Orderitem> getOrderitemList() {
        return orderitemList;
    }


    public void setOrderitemList(List<Orderitem> orderitemList) {
        this.orderitemList = orderitemList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

//    public Date getOrderDowndate() {
//        return orderDowndate;
//    }
//
//    public void setOrderDowndate(Date orderDowndate) {
//        this.orderDowndate = orderDowndate;
//    }

    public String getOrderDowndate() {
        return orderDowndate;
    }

    public void setOrderDowndate(String orderDowndate) {
        this.orderDowndate = orderDowndate;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress == null ? null : orderAddress.trim();
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName == null ? null : orderName.trim();
    }

    public String getOrderTel() {
        return orderTel;
    }

    public void setOrderTel(String orderTel) {
        this.orderTel = orderTel == null ? null : orderTel.trim();
    }

    public Integer getPostMethod() {
        return postMethod;
    }

    public void setPostMethod(Integer postMethod) {
        this.postMethod = postMethod;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Order(Integer orderId, String orderDowndate, BigDecimal orderPrice, Integer orderState, String orderAddress, String orderName, String orderTel, Integer postMethod, Integer userId) {
        this.orderId = orderId;
        //this.orderDowndate = orderDowndate;
        this.orderDowndate = orderDowndate;
        this.orderPrice = orderPrice;
        this.orderState = orderState;
        this.orderAddress = orderAddress;
        this.orderName = orderName;
        this.orderTel = orderTel;
        this.postMethod = postMethod;
        this.userId = userId;
    }

    public Order() {
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", orderDowndate=" + orderDowndate +
                ", orderPrice=" + orderPrice +
                ", orderState=" + orderState +
                ", orderAddress='" + orderAddress + '\'' +
                ", orderName='" + orderName + '\'' +
                ", orderTel='" + orderTel + '\'' +
                ", postMethod=" + postMethod +
                ", userId=" + userId +
                ", orderitemList=" + orderitemList +
                '}';
    }
}