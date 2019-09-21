package com.huseyinerenguler.techchallenge.models;

public class Order {

    private String date;
    private String month;
    private String marketName;
    private String orderName;
    private double productPrice;
    private String productState;
    private ProductDetail productDetail;

    public class ProductDetail {

        private String orderDetail;
        private double summaryPrice;

        public ProductDetail(String orderDetail, double summaryPrice) {
            this.orderDetail = orderDetail;
            this.summaryPrice = summaryPrice;
        }

        public String getOrderDetail() {
            return orderDetail;
        }

        public void setOrderDetail(String orderDetail) {
            this.orderDetail = orderDetail;
        }

        public double getSummaryPrice() {
            return summaryPrice;
        }

        public void setSummaryPrice(double summaryPrice) {
            this.summaryPrice = summaryPrice;
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public ProductDetail getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(ProductDetail productDetail) {
        this.productDetail = productDetail;
    }

}