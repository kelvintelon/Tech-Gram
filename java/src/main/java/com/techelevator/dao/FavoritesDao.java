package com.techelevator.dao;

import com.techelevator.model.Photos;

import java.util.List;

public interface FavoritesDao {

    List<Photos> getFavoritePhotosByUsername(String usernameWhoFavorited);

    void removeFromFavorites(int photoId);

    int addToFavorites(int photoId, String username);
}
