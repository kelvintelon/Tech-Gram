package com.techelevator.dao;

import com.techelevator.model.Likes;
import com.techelevator.model.Photos;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JdbcLikesDao implements LikesDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcLikesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addLike(Photos photos, int userId) {
        String sql = "INSERT INTO likes (photo_id, is_active, user_id, date_and_time) " +
                "VALUES (?, true, ?, CURRENT_TIMESTAMP) RETURNING like_id";
        int newId = jdbcTemplate.queryForObject(sql, Integer.class, photos.getPhoto_id(), userId);
        return newId;
    }

    @Override
    public int checkIfLiked(int photoId, String username) {
        String sql = "SELECT count(*) FROM likes " +
                "WHERE photo_id = ? AND is_active = true AND user_id = " +
                "(SELECT user_id FROM users WHERE username = ?)";
        int number = jdbcTemplate.queryForObject(sql, Integer.class, photoId, username);
        return number;
    }

    @Override
    public void unlikeByPhotoId(int photoId, String username) {
        String sql =
                " DELETE FROM likes " +
                " WHERE photo_id = ? AND user_id = (SELECT user_id FROM users WHERE username = ?); ";
        jdbcTemplate.update(sql, photoId, username);
    }

    @Override
    public int getLikeCountByPhotoId(int photoId) {
        String sql = "SELECT count(*) FROM likes WHERE photo_id = ?;";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, photoId);
        return count;
    }


}
