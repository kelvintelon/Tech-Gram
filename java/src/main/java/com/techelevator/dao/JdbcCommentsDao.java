package com.techelevator.dao;

import com.techelevator.model.Comments;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcCommentsDao implements CommentsDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcCommentsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Comments> getCommentsByPhotoId(int id){
        List<Comments> comments = new ArrayList<>();
        String sql = "SELECT comment_id, photo_id, user_id, text, date_and_time " +
                "FROM comments " +
                "WHERE photo_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        while(result.next()) {
            Comments comment = mapRowToComments(result);
            comment.setUsername(getUsernameByUserId(comment.getUser_id()));
            comments.add(comment);
        }
        return comments;
    }

    @Override
    public Comments createComment(Comments comments, String username) {
        Comments comment = null;
        String sql = "INSERT INTO comments (photo_id, user_id, text, date_and_time) " +
                "VALUES (?, (SELECT user_id FROM users WHERE username = ?), ?, CURRENT_TIMESTAMP) RETURNING comment_id";
        int newId = jdbcTemplate.queryForObject(sql, Integer.class, comments.getPhoto_id(), username, comments.getText());
        return getCommentByCommentId(newId);
    }

    @Override
    public void updateComment(Comments comments){
        String sql = "UPDATE comments SET text = ? " +
                "WHERE comments.comment_id = ?";
        jdbcTemplate.update(sql,comments.getText(), comments.getComment_id());
    }

    @Override
    public void deleteComment(int commentId){
        String sql = "DELETE FROM comments " +
                "WHERE comments.comment_id = ?";
        jdbcTemplate.update(sql, commentId);
    }

    @Override
    public String getUsernameByUserId(int userId) {
        String sql = "SELECT username FROM users WHERE user_id = ?;";
        String username = jdbcTemplate.queryForObject(sql, String.class, userId);
        return username;
    }

    @Override
    public Comments getCommentByCommentId(int id) {
        Comments comments = null;
        String sql = "SELECT comment_id, photo_id, user_id, text, date_and_time " +
                "FROM comments " +
                "WHERE comment_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        while(result.next()) {
            comments = mapRowToComments(result);
            comments.setUsername(getUsernameByUserId(comments.getUser_id()));

        }
        return comments;
    }

    @Override
    public List<Comments> getTwoComments(int photoId) {
        List<Comments> comments = new ArrayList<>();
        String sql = "SELECT comment_id, photo_id, user_id, text, date_and_time " +
                "FROM comments " +
                "WHERE photo_id = 15 " +
                "ORDER BY  date_and_time DESC " +
                "LIMIT 2 ";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, photoId);
        while(result.next()) {
            Comments comment = mapRowToComments(result);
            comment.setUsername(getUsernameByUserId(comment.getUser_id()));
            comments.add(comment);
        }
        return comments;
    }

    private Comments mapRowToComments(SqlRowSet rs) {
        Comments comments = new Comments();
        comments.setComment_id(rs.getInt("comment_id"));
        comments.setPhoto_id(rs.getInt("photo_id"));
        comments.setUser_id(rs.getInt("user_id"));
        comments.setText(rs.getString("text"));
        comments.setDate_and_time(rs.getTimestamp("date_and_time"));
        return comments;
    }

}
