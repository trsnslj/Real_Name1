package com.realname.marketclient.bean;

import java.math.BigDecimal;

public class Orderitem {
    private Integer orderitemId;

    private Integer productId;

    private Integer productCount;

    private BigDecimal orderitemPrice;

    private Integer orderId;

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getOrderitemId() {
        return orderitemId;
    }

    public void setOrderitemId(Integer orderitemId) {
        this.orderitemId = orderitemId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public BigDecimal getOrderitemPrice() {
        return orderitemPrice;
    }

    public void setOrderitemPrice(BigDecimal orderitemPrice) {
        this.orderitemPrice = orderitemPrice;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Orderitem(Integer orderitemId, Integer productId, Integer productCount, BigDecimal orderitemPrice, Integer orderId, Product product) {
        this.orderitemId = orderitemId;
        this.productId = productId;
        this.productCount = productCount;
        this.orderitemPrice = orderitemPrice;
        this.orderId = orderId;
        this.product = product;
    }

    public Orderitem() {
    }

    @Override
    public String toString() {
        return "Orderitem{" +
                "orderitemId=" + orderitemId +
                ", productId=" + productId +
                ", productCount=" + productCount +
                ", orderitemPrice=" + orderitemPrice +
                ", orderId=" + orderId +
                ", product=" + product +
                '}';
    }
}