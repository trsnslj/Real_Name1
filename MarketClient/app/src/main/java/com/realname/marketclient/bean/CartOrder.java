package com.realname.marketclient.bean;

import java.util.List;

public class CartOrder {
    private List<Cart> carts;
    private Order order;

    @Override
    public String toString() {
        return "CartOrder{" +
                "carts=" + carts +
                ", order=" + order +
                '}';
    }

    public CartOrder(List<Cart> carts, Order order) {
        this.carts = carts;
        this.order = order;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
