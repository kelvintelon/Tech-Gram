package com.techelevator.model;

import java.sql.Timestamp;

public class Comments {
    Integer commentId;
    Integer photoId;
    Integer userId;
    String text;
    Timestamp dateAndTime;

    public Comments(){};

    public Comments(Integer commentId, Integer photoId, Integer userId, String text, Timestamp dateAndTime) {
        this.commentId = commentId;
        this.photoId = photoId;
        this.userId = userId;
        this.text = text;
        this.dateAndTime = dateAndTime;
    }
    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Integer photoId) {
        this.photoId = photoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Timestamp dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

@Override
    public String toString() {
        return "Comments{" +
                "commentId=" + commentId +
                ", photoId='" + photoId +
                ", userId=" + userId +
                ", text=" + text +
                ", dateAndTime=" + dateAndTime +
                "}";
        }
    }