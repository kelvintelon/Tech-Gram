package com.techelevator.controller;

import com.techelevator.dao.CommentsDao;
import com.techelevator.dao.FavoritesDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Authority;
import com.techelevator.model.Comments;
import com.techelevator.model.Favorites;
import com.techelevator.model.User;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
//@RequestMapping(value = "/favorites")
public class FavoritesController {

    private FavoritesDao favoritesDao;
    private UserDao userDao;

    public FavoritesController (FavoritesDao favoritesDao, UserDao userDao) {
        this.favoritesDao = favoritesDao;
        this.userDao = userDao;
    }
    @RequestMapping(value = "/photodetails/{photoId}", method = RequestMethod.DELETE)
    public void deleteFromFavorites(@Valid @PathVariable int photoId) {
        favoritesDao.removeFromFavorites(photoId);
    }

    @RequestMapping(value = "/photodetails/{photoId}", method = RequestMethod.POST)
    public int addFavorite(@Valid @PathVariable int photoId, Principal principal){
        return favoritesDao.addToFavorites(photoId, principal.getName());
    }

}
