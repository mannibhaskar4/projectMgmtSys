package com.axidAcid.controller;

import com.axidAcid.Response.MessageResponse;
import com.axidAcid.model.Comments;
import com.axidAcid.model.User;
import com.axidAcid.request.CreateCommentRequest;
import com.axidAcid.service.CommentService;
import com.axidAcid.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<Comments> createComment(
            @RequestBody CreateCommentRequest req,
            @RequestHeader("Authorization") String jwt)throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        Comments createdComments = commentService.createComments(req.getIssueId(), user.getId(), req.getContent());
        return new ResponseEntity<>(createdComments, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentsId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId,
                                                         @RequestHeader("Authorization")String jwt)throws Exception{
        User user = userService.findUserProfileByJwt(jwt);
        commentService.deleteComments(commentId,user.getId());
        MessageResponse res =new MessageResponse();
        res.setMessage("comment deleted successfully");
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comments>> getCommentsByIssueId(@PathVariable Long issueId){
        List<Comments> comments= commentService.findCommentsByIssuesId(issueId);
        return new ResponseEntity<>(comments,HttpStatus.OK);
    }

}
