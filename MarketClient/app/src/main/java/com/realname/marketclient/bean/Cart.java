package com.realname.marketclient.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Cart implements Serializable {
    private Integer cartId;

    private Integer userId;

    private Integer productId;

    private Integer productNum;

    private String productImg;

    private String productName;

    private String productDes;

    private BigDecimal productSaleprice;

    private boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public Cart(Integer productId, Integer productNum) {
        this.productId = productId;
        this.productNum = productNum;
    }

    public Cart(Integer userId, Integer productId, Integer productNum, boolean isSelected, BigDecimal productSaleprice) {
        this.userId = userId;
        this.productId = productId;
        this.productNum = productNum;
        this.isSelected = isSelected;
        this.productSaleprice=productSaleprice;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", userId=" + userId +
                ", productId=" + productId +
                ", productNum=" + productNum +
                ", productImg='" + productImg + '\'' +
                ", productName='" + productName + '\'' +
                ", productDes='" + productDes + '\'' +
                ", productSaleprice=" + productSaleprice +
                ", isSelected=" + isSelected +
                '}';
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes;
    }

    public BigDecimal getProductSaleprice() {
        return productSaleprice;
    }

    public void setProductSaleprice(BigDecimal productSaleprice) {
        this.productSaleprice = productSaleprice;
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }
}