package com.myblog9.service.impl;

import com.myblog9.entity.Comment;
import com.myblog9.entity.Post;
import com.myblog9.exceptions.ResourceNotFound;
import com.myblog9.payload.CommentDto;
import com.myblog9.repository.CommentRepository;
import com.myblog9.repository.PostRepository;
import com.myblog9.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    private ModelMapper modelMapper;


    //This is constructor based injection it works as a @Autowired.
    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository,ModelMapper modelMapper)
    {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = maptoEntity(commentDto);

        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("POst not found with id" + postId)
        );

        comment.setPost(post); //setting the comment to the post i.e mapping the commnt wid postId

        Comment savedComment = commentRepository.save(comment);

        CommentDto dto = maptoDto(savedComment);



        return dto;
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post is not available with this id" + postId)
        );

        List<Comment> comments = commentRepository.findByPostId(postId);
        List<CommentDto> commentDto = comments.stream().map(comment -> maptoDto(comment)).collect(Collectors.toList());
        return commentDto;
    }

    @Override
    public CommentDto getCommentsById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post is not found with id" + postId)
        );

        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFound("Comment is not found with id" + commentId)
        );
        CommentDto commentDto = maptoDto(comment);

        return commentDto;
    }

    @Override
    public List<CommentDto> getAllCommentsById() {
        List<Comment> comments = commentRepository.findAll();

        List<CommentDto> comentsDto = comments.stream().map(comment -> maptoDto(comment)).collect(Collectors.toList());

        return comentsDto;
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post is not found with id" + postId)
        );


        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFound("Comment is not found with id" + commentId)
        );

        commentRepository.deleteById(commentId);

    }

    private Comment maptoEntity(CommentDto commentDto){
        Comment comment= modelMapper.map(commentDto, Comment.class);

        return comment;
    }

    private CommentDto maptoDto(Comment savedComment){
        CommentDto dto = modelMapper.map(savedComment, CommentDto.class);
        return dto;
    }
}
