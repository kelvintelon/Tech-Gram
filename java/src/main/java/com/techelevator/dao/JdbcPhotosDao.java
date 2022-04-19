package com.techelevator.dao;

import com.techelevator.model.Comments;
import com.techelevator.model.Photos;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPhotosDao implements PhotosDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcPhotosDao(JdbcTemplate jdbcTemplate) { this.jdbcTemplate = jdbcTemplate;}


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
    public List<Photos> getAllPhotos() {
        List<Photos> allPhotos = new ArrayList<>();
        String sql = "SELECT photo_id, photos.user_id, caption, image_location, date_and_time FROM photos " +
                "JOIN users ON photos.user_id = users.user_id " +
                "ORDER BY date_and_time DESC;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while (result.next()) {
            Photos photos = mapRowToPhotos(result);
            photos.setComments(getCommentsByPhotoId(photos.getPhoto_id()));
            photos.setLikeCount(getLikeCountByPhotoId(photos.getPhoto_id()));
            photos.setUsername(getUsernameByUserId(photos.getUser_id()));
            allPhotos.add(photos);
        }
        return allPhotos;
    }

    @Override
    public Photos addPhoto(Photos newPhotos, int userId) {
        String sql = "INSERT INTO photos (user_id, caption, image_location, date_and_time) " +
                "VALUES (?,?,?,CURRENT_TIMESTAMP) RETURNING photo_id;";
        int newId = jdbcTemplate.queryForObject(sql, Integer.class, userId,
                newPhotos.getCaption(), newPhotos.getImage_location());
        return getPhotoByPhotoId(newId);
    }

    @Override
    public List<Photos> getPhotosByUsername(String username) {
        List<Photos> allPhotosByUser = new ArrayList<>();
        String sql = "SELECT photo_id, photos.user_id, caption, image_location, date_and_time FROM photos " +
                "JOIN users ON photos.user_id = users.user_id " +
                "WHERE users.username = ? " +
                "ORDER BY date_and_time DESC;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        while (result.next()) {
            Photos photos = mapRowToPhotos(result);
            photos.setComments(getCommentsByPhotoId(photos.getPhoto_id()));
            photos.setLikeCount(getLikeCountByPhotoId(photos.getPhoto_id()));
            photos.setUsername(getUsernameByUserId(photos.getUser_id()));
            allPhotosByUser.add(photos);
        }
        return allPhotosByUser;
    }

    @Override
    public Photos getPhotoByPhotoId(int photoId) {
        Photos photos = null;
        String sql = "SELECT photo_id, photos.user_id, caption, image_location, date_and_time FROM photos " +
                "JOIN users ON photos.user_id = users.user_id " +
                "WHERE photo_id = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, photoId);
        if (result.next()) {
            photos = mapRowToPhotos(result);
            photos.setComments(getCommentsByPhotoId(photos.getPhoto_id()));
            photos.setLikeCount(getLikeCountByPhotoId(photos.getPhoto_id()));
            photos.setUsername(getUsernameByUserId(photos.getUser_id()));
        }
        return photos;
    }

    @Override
    public void deletePhotoByPhotoId(int photoId) {
        String sql = "BEGIN TRANSACTION;\n" +
                "\n" +
                "DELETE FROM comments \n" +
                "WHERE comments.photo_id = ?;\n" +
                "\n" +
                "DELETE FROM likes\n" +
                "WHERE likes.photo_id = ?;\n" +
                "\n" +
                "DELETE FROM favorites\n" +
                "WHERE favorites.photo_id = ?;\n" +
                "\n" +
                "DELETE FROM photos\n" +
                "WHERE photo_id = ?;\n" +
                "\n" +
                "COMMIT TRANSACTION;";
        jdbcTemplate.update(sql, photoId, photoId, photoId, photoId);
    }

    @Override
    public void updateCaptionByPhotoId(Photos photos, int photoId) {
//        photoId = photos.getPhoto_id();
        String sql = "UPDATE photos SET caption = ? WHERE photo_id = ?;";
        jdbcTemplate.update(sql, photos.getCaption(), photoId);
    }

    @Override
    public int getPhotoCountByUsername(String username) {
        String sql = "SELECT count(*) FROM photos WHERE user_id = (SELECT user_id FROM users WHERE username = ?);";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, username);
        return count;
    }

    @Override
    public int getPhotoCountByOtherUserUsername(String username) {
        String sql = "SELECT count(*) FROM photos WHERE user_id = (SELECT user_id FROM users WHERE username = ?);";
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
