package com.techelevator.model;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class Photos {

    private int photo_id;
    private int user_id;
    private String caption;
    private String image_location;
    private Timestamp date_and_time;
    private List<Comments> comments;
    private int likeCount;
    private String username;

    public Photos(int photo_id, int user_id, String caption, String image_location, Timestamp date_and_time) {
        this.photo_id = photo_id;
        this.user_id = user_id;
        this.caption = caption;
        this.image_location = image_location;
        this.date_and_time = date_and_time;
    }

    public Photos() {

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getImage_location() {
        return image_location;
    }

    public void setImage_location(String image_location) {
        this.image_location = image_location;
    }

    public Timestamp getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(Timestamp date_and_time) {
        this.date_and_time = date_and_time;
    }
}
