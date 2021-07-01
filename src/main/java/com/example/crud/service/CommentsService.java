package com.example.crud.service;

import com.example.crud.domain.Comments;
import com.example.crud.domain.Posts;
import com.example.crud.domain.User;
import com.example.crud.dto.CommentsRequestDto;
import com.example.crud.dto.CommentsResponseDto;
import com.example.crud.repository.CommentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class CommentsService {
    private final CommentsRepository commentsRepository;
    private final UserService userService;
    private final PostsService postsService;

    @Transactional
    public Long save(CommentsRequestDto requestDto, Long postId, String username){
        Comments comments = requestDto.toEntity();
        User user = userService.findByUsername(username);
        Posts post = postsService.findById(postId);

        user.addComments(comments);
        post.addComments(comments);
        commentsRepository.save(comments);
        return comments.getId();
    }

    @Transactional
    public Long delete(Long id, String username){
        Comments comments = commentsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("없어요"));
        if(comments.getUser().getUsername().equals(username)) {
            commentsRepository.delete(comments);
            return id;
        } else{
            throw new IllegalArgumentException("없어요");
        }
    }


    public List<CommentsResponseDto> findCommentsByPostId(Long postId){
       return commentsRepository
               .findCommentsByPostId(postId)
               .stream()
               .map(CommentsResponseDto::new)
               .collect(Collectors.toList());
    }

}
