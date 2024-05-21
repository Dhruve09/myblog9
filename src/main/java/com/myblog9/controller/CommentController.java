package com.myblog9.controller;

import com.myblog9.payload.CommentDto;
import com.myblog9.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    private CommentService commentService;
    public CommentController(CommentService commentService) {

        this.commentService = commentService;
    }

    //http://localhost:8080/api/MahaDev/1/comments
    @PostMapping("/MahaDev/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    //http://localhost:8080/api/MahaDev/1/comments
    @GetMapping("/MahaDev/{postId}/comments")
    public List<CommentDto> getCommentByPostId(@PathVariable (value = "postId") long postId){
        return commentService.getCommentsByPostId(postId);
    }


    //http://localhost:8080/api/comments
    @GetMapping("/comments")
    public List<CommentDto> getAllCommentById(){
        return commentService.getAllCommentsById();
    }

    //http://localhost:8080/api/MahaDev/1/comments/2
    @ DeleteMapping("/MahaDev/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable (value = "postId") long postId,
                                      @PathVariable(value = "commentId") long commentId )
    {
        commentService.deleteCommentById(postId,commentId);
        return new ResponseEntity<>("Comment is deleted",HttpStatus.OK);
    }



}
