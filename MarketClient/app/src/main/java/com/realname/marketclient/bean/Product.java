package com.realname.marketclient.bean;

import java.math.BigDecimal;

public class Product {
    private Integer productId;

    private String productName;

    private String productDes;

    private BigDecimal productRealprice;

    private BigDecimal productSaleprice;

    private String productImg1;

    private String productImg2;

    private String productImg3;

    private Integer productStock;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getProductDes() {
        return productDes;
    }

    public void setProductDes(String productDes) {
        this.productDes = productDes == null ? null : productDes.trim();
    }

    public BigDecimal getProductRealprice() {
        return productRealprice;
    }

    public void setProductRealprice(BigDecimal productRealprice) {
        this.productRealprice = productRealprice;
    }

    public BigDecimal getProductSaleprice() {
        return productSaleprice;
    }

    public void setProductSaleprice(BigDecimal productSaleprice) {
        this.productSaleprice = productSaleprice;
    }

    public String getProductImg1() {
        return productImg1;
    }

    public void setProductImg1(String productImg1) {
        this.productImg1 = productImg1 == null ? null : productImg1.trim();
    }

    public String getProductImg2() {
        return productImg2;
    }

    public void setProductImg2(String productImg2) {
        this.productImg2 = productImg2 == null ? null : productImg2.trim();
    }

    public String getProductImg3() {
        return productImg3;
    }

    public void setProductImg3(String productImg3) {
        this.productImg3 = productImg3 == null ? null : productImg3.trim();
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productDes='" + productDes + '\'' +
                ", productRealprice=" + productRealprice +
                ", productSaleprice=" + productSaleprice +
                ", productImg1='" + productImg1 + '\'' +
                ", productImg2='" + productImg2 + '\'' +
                ", productImg3='" + productImg3 + '\'' +
                ", productStock=" + productStock +
                '}';
    }
}