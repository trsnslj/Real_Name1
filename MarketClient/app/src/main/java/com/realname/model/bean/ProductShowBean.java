package com.realname.model.bean;


import java.util.List;

public class ProductShowBean {
    private List<ProductsBean> productsBean;

    public List<ProductsBean> getProductsBean() {
        return productsBean;
    }

    public void setProductsBean(List<ProductsBean> productsBean) {
        this.productsBean = productsBean;
    }

    public static class ProductsBean {
        private Integer productId;
        private String productName;
        private String productDes;
        private double productRealprice;
        private double productSaleprice;
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
            this.productName = productName;
        }

        public String getProductDes() {
            return productDes;
        }

        public void setProductDes(String productDes) {
            this.productDes = productDes;
        }

        public double getProductRealprice() {
            return productRealprice;
        }

        public void setProductRealprice(double productRealprice) {
            this.productRealprice = productRealprice;
        }

        public double getProductSaleprice() {
            return productSaleprice;
        }

        public void setProductSaleprice(double productSaleprice) {
            this.productSaleprice = productSaleprice;
        }

        public String getProductImg1() {
            return productImg1;
        }

        public void setProductImg1(String productImg1) {
            this.productImg1 = productImg1;
        }

        public String getProductImg2() {
            return productImg2;
        }

        public void setProductImg2(String productImg2) {
            this.productImg2 = productImg2;
        }

        public String getProductImg3() {
            return productImg3;
        }

        public void setProductImg3(String productImg3) {
            this.productImg3 = productImg3;
        }

        public Integer getProductStock() {
            return productStock;
        }

        public void setProductStock(Integer productStock) {
            this.productStock = productStock;
        }
    }
    /**
     * [
     *   {
     *     "productId": 1,
     *     "productName": "乐事薯片（原味）",
     *     "productDes": "",
     *     "productRealprice": 3,
     *     "productSaleprice": 2,
     *     "productImg1": "shupian.jpg",
     *     "productImg2": "shupian.jpg",
     *     "productImg3": "shupian.jpg",
     *     "productStock": 100
     *   },
     *   {
     *     "productId": 2,
     *     "productName": "可口可乐350ml",
     *     "productDes": "",
     *     "productRealprice": 3,
     *     "productSaleprice": 2,
     *     "productImg1": "kele.png",
     *     "productImg2": "kele.png",
     *     "productImg3": "kele.png"
     *   },
     *   {
     *     "productId": 3,
     *     "productName": "维达纸巾",
     *     "productDes": "",
     *     "productRealprice": 1.5,
     *     "productSaleprice": 1,
     *     "productImg1": "weida.png",
     *     "productImg2": "weida.png",
     *     "productImg3": "weida.png"
     *   },
     *   {
     *     "productId": 4,
     *     "productName": "海飞丝",
     *     "productDes": "",
     *     "productRealprice": 20,
     *     "productSaleprice": 18,
     *     "productImg1": "haifeisi.png",
     *     "productImg2": "haifeisi.png",
     *     "productImg3": "haifeisi.png"
     *   },
     *   {
     *     "productId": 5,
     *     "productName": "飘柔",
     *     "productDes": "",
     *     "productRealprice": 20,
     *     "productSaleprice": 18,
     *     "productImg1": "piaorou.png",
     *     "productImg2": "piaorou.png",
     *     "productImg3": "piaorou.png"
     *   },
     *   {
     *     "productId": 6,
     *     "productName": "沙宣",
     *     "productRealprice": 25,
     *     "productSaleprice": 22,
     *     "productImg1": "shaxuan.png",
     *     "productImg2": "shaxuan.png",
     *     "productImg3": "shaxuan.png"
     *   }
     * ]
     */
}
