package com.realname.marketclient.bean;

import java.util.Date;
import java.lang.*;

public class Comment {
    private Integer commentId;

    private Integer userId;

    private String commentContent;

    private Date commentDate;

    private Integer productId;

    private String userName;

    private String userImg;

    public Comment(Integer userId, String commentContent, Date commentDate, Integer productId) {
        this.userId = userId;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
        this.productId = productId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", userId=" + userId +
                ", commentContent='" + commentContent + '\'' +
                ", commentDate=" + commentDate +
                ", productId=" + productId +
                ", userName='" + userName + '\'' +
                ", userImg='" + userImg + '\'' +
                '}';
    }
}