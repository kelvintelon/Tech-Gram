package com.techelevator.model;

import java.sql.Timestamp;

public class Likes {

    private int like_id;
    private int photo_id;
    private Boolean is_active;
    private int user_id;
    private Timestamp date_and_time;

    public Likes(int like_id, int photo_id, Boolean is_active, int user_id, Timestamp date_and_time) {
        this.like_id = like_id;
        this.photo_id = photo_id;
        this.is_active = is_active;
        this.user_id = user_id;
        this.date_and_time = date_and_time;
    }

    public Likes() {
    }

    public int getLike_id() {
        return like_id;
    }

    public void setLike_id(int like_id) {
        this.like_id = like_id;
    }

    public int getPhoto_id() {
        return photo_id;
    }

    public void setPhoto_id(int photo_id) {
        this.photo_id = photo_id;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Timestamp getDate_and_time() {
        return date_and_time;
    }

    public void setDate_and_time(Timestamp date_and_time) {
        this.date_and_time = date_and_time;
    }
}
