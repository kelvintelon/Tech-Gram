package com.techelevator.dao;

import com.techelevator.model.Comments;
import com.techelevator.model.Favorites;
import com.techelevator.model.Photos;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JdbcFavoritesDao implements FavoritesDao {
    private JdbcTemplate jdbcTemplate;
    public JdbcFavoritesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Photos> getFavoritePhotosByUsername(String usernameWhoFavorited) {
        List<Photos> photos = new ArrayList<>();
        String sql = "SELECT photos.photo_id, photos.user_id, photos.caption, " +
                "photos.image_location, photos.date_and_time " +
                "FROM photos " +
                "JOIN favorites ON photos.photo_id = favorites.photo_id " +
                "WHERE favorites.user_id = (SELECT user_id FROM users WHERE username = ?) " +
                "ORDER BY date_and_time DESC;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, usernameWhoFavorited);
        while (result.next()) {
            Photos photo = mapRowToPhotos(result);
            photo.setComments(getCommentsByPhotoId(photo.getPhoto_id()));
            photo.setLikeCount(getLikeCountByPhotoId(photo.getPhoto_id()));
            photo.setUsername(getUsernameByUserId(photo.getUser_id()));
            photos.add(photo);
        }
        return photos;
    }

    @Override
    public void removeFromFavorites(int photoId){
        String sql = "DELETE FROM favorites " +
                "WHERE favorites.photo_id = ?";
        jdbcTemplate.update(sql, photoId);
    }

    @Override
    public int addToFavorites(int photoId, String username){
        Favorites favorites = null;
        String sql = "INSERT INTO favorites (photo_id, user_id, date_and_time) " +
                "VALUES (?, (SELECT user_id FROM users WHERE username = ?), " +
                "CURRENT_TIMESTAMP) RETURNING favorite_id";
        Integer favoritesId = jdbcTemplate.queryForObject(sql, Integer.class, photoId, username);
        return favoritesId;
    }

    @Override
    public int checkIfExists(int photoId, String username) {
        String sql = "SELECT count(*) FROM favorites " +
                "WHERE photo_id = ? AND user_id = (SELECT user_id FROM users WHERE username = ?)";
        Integer number = jdbcTemplate.queryForObject(sql, Integer.class, photoId, username);
        return number;
    }

    @Override
    public List<Comments> getCommentsByPhotoId(int id) {
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
    public int getLikeCountByPhotoId(int photoId) {
        String sql = "SELECT count(*) FROM likes WHERE photo_id = ? AND is_active = true;";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, photoId);
        return count;
    }

    @Override
    public String getUsernameByUserId(int userId) {
        String sql = "SELECT username FROM users WHERE user_id = ?;";
        String username = jdbcTemplate.queryForObject(sql, String.class, userId);
        return username;
    }

    @Override
    public int getPhotoCountByUsername(String username) {
        String sql = "SELECT count(*) FROM favorites WHERE user_id = (SELECT user_id FROM users WHERE username = ?);";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count;
    }

    private Photos mapRowToPhotos(SqlRowSet rowSet) {
        Photos photos = new Photos();
        photos.setPhoto_id(rowSet.getInt("photo_id"));
        photos.setUser_id(rowSet.getInt("user_id"));
        photos.setCaption(rowSet.getString("caption"));
        photos.setImage_location(rowSet.getString("image_location"));
        photos.setDate_and_time(rowSet.getTimestamp("date_and_time"));
        return photos;
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
