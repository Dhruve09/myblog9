package com.myblog9.controller;

import com.myblog9.payload.PostDto;
import com.myblog9.payload.PostResponse;
import com.myblog9.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/MahaDev")
public class PostController {
    @Autowired
    private PostService postService;

    //http://localhost:8080/api/MahaDev
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> savePost(@Valid @RequestBody PostDto postDto, BindingResult result)//@Valid enables error checking in controller layer BindingResult does is if their is any errors in the PostDto those errors can be send to Postman by bindingresult
    //By putting ? in ResponseEntity<?> we can have any type of return type.                           BindingResult have an method called as haserror() it checks the error in if condition
    {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.savePost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);//201
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id ){
        postService.deletePost(id);
        return new ResponseEntity<>("Post is deleted", HttpStatus.OK);//200
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable("id") long id,  @RequestBody PostDto postDto){
        PostDto dto = postService.updatePost(id, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id){
        PostDto dto = postService.getPostById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    //i commented statements below bcase it was used before PostResponse class ,To remember the flow before PostResponse, refer to the commented part in this layer and in service layer

    //http://localhost:8080/api/MahaDev?pageNo=0&pageSize=6&sortBy=id&sortDir=asc
    //public List<PostDto> getPosts(
    @GetMapping
    public PostResponse getPost (

            @RequestParam(value = "pageNo", defaultValue ="0",required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue ="3",required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue ="id",required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue ="asc",required = false) String sortDir

    )
    {
       // List<PostDto> postDtos = postService.getPost(pageNo,pageSize,sortBy,sortDir);
        PostResponse postResponse = postService.getPost(pageNo,pageSize,sortBy,sortDir);
        return postResponse;
    }
}
