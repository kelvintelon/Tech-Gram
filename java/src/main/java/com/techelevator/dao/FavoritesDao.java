package com.techelevator.dao;

import java.util.List;

public interface FavoritesDao {


    void removeFromFavorites(int photoId);

    int addToFavorites(int photoId, String username);
}
