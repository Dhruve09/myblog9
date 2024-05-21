package com.myblog9.service;

import com.myblog9.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentService  {
    CommentDto createComment(long postId, CommentDto commentDto);
   List<CommentDto> getCommentsByPostId(long postId);

    CommentDto getCommentsById(long postId, long commentId);

    List<CommentDto> getAllCommentsById();

    void deleteCommentById(long postId, long commentId);
}
