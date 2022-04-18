package com.techelevator.dao;

import com.techelevator.model.Likes;
import com.techelevator.model.Photos;

public interface LikesDao {

    int addLike(Photos photos, int userId);

    void unlikeByPhotoId(int photoId, String username);

    int checkIfLiked(int photoId, String username);

    int getLikeCountByPhotoId(int photoId);

}
