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
                "WHERE favorites.user_id = (SELECT user_id FROM users WHERE username = ?)";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, usernameWhoFavorited);
        while (result.next()) {
            Photos photo = mapRowToPhotos(result);
            photo.setComments(getCommentsByPhotoId(photo.getPhoto_id()));
            photo.setLikeCount(getLikeCountByPhotoId(photo.getPhoto_id()));
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
    public List<Comments> getCommentsByPhotoId(int id) {
        List<Comments> comments = new ArrayList<>();
        String sql = "SELECT comment_id, photo_id, user_id, text, date_and_time " +
                "FROM comments " +
                "WHERE photo_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, id);
        while(result.next()) {
            Comments comment = mapRowToComments(result);
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
        comments.setCommentId(rs.getInt("comment_id"));
        comments.setPhotoId(rs.getInt("photo_id"));
        comments.setUserId(rs.getInt("user_id"));
        comments.setText(rs.getString("text"));
        comments.setDateAndTime(rs.getTimestamp("date_and_time"));
        return comments;
    }
}
