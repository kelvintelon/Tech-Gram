package com.techelevator.controller;

import com.techelevator.dao.LikesDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Photos;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@PreAuthorize("isAuthenticated()")
//@RequestMapping(path = "/likes")
@CrossOrigin
public class LikesController {

    private LikesDao likesDao;
    private UserDao userDao;

    public LikesController(LikesDao likesDao, UserDao userDao) {
        this.likesDao = likesDao;
        this.userDao = userDao;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/likes", method = RequestMethod.POST)
    public int addLike(@Valid Principal principal, @RequestBody Photos photos) {
        return likesDao.addLike(photos, userDao.findIdByUsername(principal.getName()));
    }

    @RequestMapping(path = "/unlike/{photoId}", method = RequestMethod.PUT)
    public void unlikeByPhotoId(@Valid Principal principal, @PathVariable int photoId) {
        likesDao.unlikeByPhotoId(photoId, userDao.findIdByUsername(principal.getName()));
    }

    @RequestMapping(path = "/likeCount/{photoId}", method = RequestMethod.GET)
    public int getLikeCountByPhotoId(@Valid @PathVariable int photoId) {
        return likesDao.getLikeCountByPhotoId(photoId);
    }
    
}
