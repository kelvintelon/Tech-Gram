package com.techelevator.dao;

import com.techelevator.model.Photos;

import java.util.List;

public interface PhotosDao {

    List<Photos> getAllPhotos();

    Photos addPhoto(Photos photos, int userId);

    List<Photos> getPhotosByUsername(String username);

    Photos getPhotoByPhotoId(int photoId);

    void deletePhotoByPhotoId(int photoId);

    void updateCaptionByPhotoId(Photos photos, int photoId);
}
