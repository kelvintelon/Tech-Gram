package com.techelevator.controller;

import com.techelevator.dao.PhotosDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Photos;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping(path = "/photos")
@CrossOrigin
public class PhotosController {

    private PhotosDao photosDao;
    private UserDao userDao;

    public PhotosController(PhotosDao photosDao, UserDao userDao) {
        this.photosDao = photosDao;
        this.userDao = userDao;
    }

    @RequestMapping(path= "", method = RequestMethod.GET)
    public List<Photos> getAllPhotos() {
        return photosDao.getAllPhotos();
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "", method = RequestMethod.POST)
    public Photos addPhotos(@Valid Principal principal, @RequestBody Photos photos) {
       return photosDao.addPhoto(photos, userDao.findIdByUsername(principal.getName()));
    }

    @RequestMapping(path = "/user/{name}", method = RequestMethod.GET)
    public List<Photos> getPhotosByUserId(@Valid @PathVariable String name) {
        return photosDao.getPhotosByUsername(name);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Photos getPhotoByPhotoId(@Valid @PathVariable int id) {
        return photosDao.getPhotoByPhotoId(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deletePhotoByPhotoId(@Valid @PathVariable int id) {
        photosDao.deletePhotoByPhotoId(id);

        // when more information is added to the database, deleting photos will be a lot
        // more complicated due to constraints

    }

    @RequestMapping(path = "/{photoId}", method = RequestMethod.PUT)
    public void updateCaptionByPhotoId(@Valid @RequestBody Photos photos, @PathVariable int photoId) {
        photosDao.updateCaptionByPhotoId(photos, photoId);

        // right now the updateCaptionByPhotoId method also updates the timestamp to current time,
        // might need to fix that moving forward
    }

    @RequestMapping(path = "/photoCount", method = RequestMethod.GET)
    public int getPhotoCount(@Valid Principal principal) {
        return photosDao.getPhotoCountByUsername(principal.getName());
    }

    @RequestMapping(path = "/photoCount/{username}", method = RequestMethod.GET)
    public int getPhotoCount(@Valid @PathVariable String username) {
        return photosDao.getPhotoCountByOtherUserUsername(username);
    }

}
