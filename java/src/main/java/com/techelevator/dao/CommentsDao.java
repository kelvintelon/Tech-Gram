package com.techelevator.dao;

import com.techelevator.model.Comments;

import java.util.List;

public interface CommentsDao {
    List<Comments> getCommentsByPhotoId(int id);

    int createComment(Comments comments, String username);

    void updateComment(Comments comments);

    void deleteComment(Comments comments);
}
