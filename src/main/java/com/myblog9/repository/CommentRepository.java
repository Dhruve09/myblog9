package com.myblog9.repository;

import com.myblog9.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    //building custom method to build a query to search a particular data based on a particular column of ur table
    List<Comment> findByPostId(long postId);  //it willl automatically work like select * from comment where postId=1
}
