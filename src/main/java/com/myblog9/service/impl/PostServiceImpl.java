package com.myblog9.service.impl;

import com.myblog9.entity.Post;
import com.myblog9.exceptions.ResourceNotFound;
import com.myblog9.payload.PostDto;
import com.myblog9.payload.PostResponse;
import com.myblog9.repository.PostRepository;
import com.myblog9.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepository postRepository;

   @Autowired
   private ModelMapper modelMapper;
    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post savedPost = postRepository.save(post);

        PostDto dto = new PostDto();

        dto.setId(savedPost.getId());
        dto.setTitle(savedPost.getTitle());
        dto.setDescription(savedPost.getDescription());
        dto.setContent(savedPost.getContent());


        return dto;
    }

    @Override
    public void deletePost(long id) {
        postRepository.deleteById(id);

    }

    @Override
    public PostDto updatePost(long id, PostDto postDto) {
       Post post = postRepository.findById(id).orElseThrow(
               ()->new ResourceNotFound("Post Not Found with id:"+id)
       );

       post.setTitle(postDto.getTitle());
       post.setContent(postDto.getContent());
       post.setDescription(postDto.getDescription());

        Post updatePost = postRepository.save(post);
        PostDto dto = mapToDto(updatePost);
        return dto;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Post not found with id" + id)
        );

        PostDto dto = mapToDto(post);
        return dto;
    }

//    @Override
//    public List<PostDto> getPost(int pageNo, int pageSize, String sortBy, String sortDir)
     @Override
     public PostResponse getPost(int pageNo, int pageSize, String sortBy, String sortDir)

    {

       //using ternary operator i.e after ? if the condition is true first operation will be performed and id false second operation will be performed
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        //PageRequest is used to take input of pageno. and size,and to convert String sortBy to Sort obj Sort.by id used(we r converting to sort obj bcauce syntax of pageRequest.of takes sort obj.)
     // Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

      //thn we will pass the pageable obj to findall whose return type is Page of posts
      Page<Post> pagePost = postRepository.findAll(pageable);

       //getContent() is used to convert page obj into lists
       List<Post> posts = pagePost.getContent();

        //converting entity obj to dto as return type of find all() is List so we used streamapi to coverting all entity into dto
        List<PostDto> postDtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());
       // return postDtos;
        PostResponse postResponse=new PostResponse();
        postResponse.setPostDto(postDtos);
        postResponse.setPageNo(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLast(pagePost.isLast());

        return postResponse;
    }

    PostDto mapToDto(Post post){
        PostDto dto = modelMapper.map(post, PostDto.class);

        //or


//        PostDto dto = new PostDto();
//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());

        return dto;
    }

   Post mapToEntity(PostDto postDto){
       Post post = modelMapper.map(postDto, Post.class);
//       Post post = new Post();
//       post.setTitle(postDto.getTitle());
//       post.setDescription(postDto.getDescription());
//       post.setContent(postDto.getContent());
        return post;
   }
}
