package com.realname.marketclient.net;

public class API {
    //访问后台的一系列接口
    //添加用户
    public static final String INSERT_USER="/user/insertUser";
    //获取验证码
    public static final String GET_CODE="/user/getCode";
    //查询商品
    public static final String SHOW_PRODUCT="/product/show";
    //根据用户查询全部订单（包含订单项，物品）
    public static final String SHOW_ORDER="/user/orders";
    public static final String LOGIN="/user/login";
    public static final String GET_ONE_PRODUCT="/product/selectProduct";
    public static final String GET_COMMENTS="/user/getComments";
    public static final String Get_COMMENTDATE="/user/getCommentdate";
    public static final String INSERT_CART="/user/insertCart";
    public static final String TO_PAY="/SelectOrder/State";
    public static final String GET_CARTS="/user/getCarts";
//    改变订单状态
    public static final String CHANGE_STATE="/order/change_state";
    //增加评论
    public static final String ADD_COMMENT="/add/Comment";
    public static final String INSERT_ORDER="/order/insertOrder";
    public static final String DELETE_CART="/user/deleteCart";
}
