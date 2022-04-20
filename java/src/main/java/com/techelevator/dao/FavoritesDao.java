package com.techelevator.dao;

import com.techelevator.model.Comments;
import com.techelevator.model.Photos;

import java.util.List;

public interface FavoritesDao {

    List<Photos> getFavoritePhotosByUsername(String usernameWhoFavorited);

    void removeFromFavorites(int photoId);

    int addToFavorites(int photoId, String username);

    int checkIfExists(int photoId, String username);

    List<Comments> getCommentsByPhotoId(int id);

    int getLikeCountByPhotoId(int photoId);

    String getUsernameByUserId(int userId);

    int getPhotoCountByUsername(String username);

}
