package com.techelevator.dao;

import com.techelevator.model.Comments;

import java.util.List;

public interface CommentsDao {
    List<Comments> getCommentsByPhotoId(int id);

    Comments createComment(Comments comments, String username);

    void updateComment(Comments comments);

    void deleteComment(int commentId);

    String getUsernameByUserId(int userId);

    Comments getCommentByCommentId(int id);

    List<Comments> getTwoComments(int photoId);
}

