package com.techelevator.dao;

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
    public List<Photos> getAllPhotos() {
        List<Photos> allPhotos = new ArrayList<>();
        String sql = "SELECT photo_id, photos.user_id, caption, image_location, date_and_time FROM photos " +
                "JOIN users ON photos.user_id = users.user_id;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
        while (result.next()) {
            allPhotos.add(mapRowToPhotos(result));
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
                "WHERE users.username = ?;";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, username);
        while (result.next()) {
            allPhotosByUser.add(mapRowToPhotos(result));
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
        }
        return photos;
    }

    @Override
    public void deletePhotoByPhotoId(int photoId) {
        String sql = "DELETE FROM photos WHERE photo_id = ?;";
        jdbcTemplate.update(sql, photoId);
    }

    @Override
    public void updateCaptionByPhotoId(Photos photos, int photoId) {
//        photoId = photos.getPhoto_id();
        String sql = "UPDATE photos SET caption = ? WHERE photo_id = ?;";
        jdbcTemplate.update(sql, photos.getCaption(), photoId);
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
}