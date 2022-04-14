package com.techelevator.model;

import org.springframework.data.relational.core.sql.In;

import java.sql.Timestamp;
import java.util.List;

public class Favorites {
    Integer favoriteId;
    Integer photoId;
    Integer userId;
    String text;
    Timestamp dateAndTime;
    private List<Comments> comments;
    private int likeCount;

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public Favorites() {}

    public Favorites(Integer favoriteId, Integer photoId, Integer userId, String text, Timestamp dateAndTime) {
        this.favoriteId = favoriteId;
        this.photoId = photoId;
        this.userId = userId;
        this.text = text;
        this.dateAndTime = dateAndTime;
    }

    public Integer getFavoriteId() {
        return favoriteId;
    }

    public void setFavoriteId(Integer favoriteId) {
        this.favoriteId = favoriteId;
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
        return "Favorites{" +
                "favoriteId=" + favoriteId +
                ", photoId='" + photoId +
                ", userId=" + userId +
                ", text=" + text +
                ", dateAndTime=" + dateAndTime +
                "}";
    }
}
