package com.techelevator.controller;

import com.techelevator.dao.CommentsDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Comments;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;


@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping(value = "/comments")
public class CommentsController {
    private CommentsDao commentsDao;
    private UserDao userDao;

    public CommentsController(CommentsDao commentsDao, UserDao userDao) {
        this.commentsDao = commentsDao;
        this.userDao = userDao;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Comments createComment(@Valid @RequestBody Comments comments, Principal principal){
       return commentsDao.createComment(comments, principal.getName());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public List<Comments> getAllComments (@Valid @PathVariable int id) {
        return commentsDao.getCommentsByPhotoId(id);
    }


    @RequestMapping(value = "", method = RequestMethod.PUT)
    public void updateComment (@Valid @RequestBody Comments comments) {
        commentsDao.updateComment(comments);
    }

    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
    public void deleteComment (@Valid @PathVariable int id) {
        commentsDao.deleteComment(id);
    }

}

