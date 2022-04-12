package com.techelevator.dao;

import com.techelevator.model.Favorites;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class JdbcFavoritesDao implements FavoritesDao {
    private JdbcTemplate jdbcTemplate;
    public JdbcFavoritesDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /*public List<Photos> getPhotosByUserId(int userIdWhoLiked) {
        List<Photos> photos = new ArrayList<Photos>();
        String sql = "SELECT photos.photo_id, photos.user_id, photos.caption, " +
                "photos.image_location, photos.date_and_time " +
                "FROM photos " +
                "JOIN favorites ON photos.photo_id = favorites.photo_id " +
                "WHERE favorites.user_id = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, userIdWhoLiked);
        while (result.next()) {
            Photos photo = mapRowToPhotos(result);
            photos.add(photo);
        }
        return photos;
    }*/

    @Override
    public void removeFromFavorites(int photoId){
        String sql = "DELETE FROM favorites " +
                "WHERE favorites.photo_id = ?";
        jdbcTemplate.update(sql, photoId);
    }

    @Override
    public int addToFavorites(int photoId, String username){
        Favorites favorites = null;
        String sql = "INSERT INTO favorites (photo_id, user_id, text, date_and_time) " +
                "VALUES (?, (SELECT user_id FROM users WHERE username = ?), 'plaseholder', " +
                "CURRENT_TIMESTAMP) RETURNING favorite_id";
        Integer favoritesId = jdbcTemplate.queryForObject(sql, Integer.class, photoId, username);
        return favoritesId;
    }

}
