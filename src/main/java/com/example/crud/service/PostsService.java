package com.example.crud.service;

import com.example.crud.domain.Posts;
import com.example.crud.domain.User;
import com.example.crud.dto.PostsListResponseDto;
import com.example.crud.dto.PostsResponseDto;
import com.example.crud.dto.PostsSaveRequestDto;
import com.example.crud.dto.PostsUpdateRequestDto;
import com.example.crud.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostsService {
    private final PostsRepository postsRepository;
    private final UserService userService;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto, String username) {
        Posts post = postsRepository.save(requestDto.toEntity());
        User user = userService.findByUsername(username);
        user.addPost(post);
        return post.getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto, String username) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        if (post.getUser().getUsername().equals(username)) {
            post.update(requestDto.getTitle(), requestDto.getContent());
            return post.getId();
        } else {
            throw new IllegalArgumentException("권한 없음!");
        }
    }

    public PostsResponseDto findById(Long id) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(post);
    }


    //@Cacheable(key="#root.methodName", value = "findAllDes")
    public List<PostsListResponseDto> findAllDes() {
        return postsRepository.findAllByOrderByIdDesc()
                .stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteById(Long id, String username) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        if (post.getUser().getUsername().equals(username)) {
            postsRepository.delete(post);
        } else {
            throw new IllegalArgumentException("권한 없음!");
        }
    }
}
