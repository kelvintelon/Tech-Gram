package com.techelevator.dao;

import com.techelevator.model.Comments;
import com.techelevator.model.Photos;

import java.util.List;

public interface PhotosDao {

    List<Comments> getCommentsByPhotoId(int id);

    int getLikeCountByPhotoId(int photoId);

    String getUsernameByUserId(int userId);

    List<Photos> getAllPhotos();

    Photos addPhoto(Photos photos, int userId);

    List<Photos> getPhotosByUsername(String username);

    Photos getPhotoByPhotoId(int photoId);

    void deletePhotoByPhotoId(int photoId);

    void updateCaptionByPhotoId(Photos photos, int photoId);

    int getPhotoCountByUsername(String username);

    int getPhotoCountByOtherUserUsername(String username);
}
