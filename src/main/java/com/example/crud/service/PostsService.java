package com.example.crud.service;

import com.example.crud.domain.Posts;
import com.example.crud.domain.User;

import com.example.crud.dto.PostsSaveRequestDto;
import com.example.crud.dto.PostsUpdateRequestDto;
import com.example.crud.repository.PostsRepository;
import com.example.crud.util.PostSearch;
import com.example.crud.util.PostSpec;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        System.out.println(" = " + post.getCreatedDate());
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

    public Posts findById(Long id) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return post;
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

    public Page<Posts> findBySearch(PostSearch postSearch, Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 10, Sort.by(Sort.Order.desc("id")));

        Specification<Posts> spec = null;

        if (postSearch.getTitle() != null) {
            spec = Specification.where(PostSpec.likePostsTitle(postSearch.getTitle()));
        } else if (postSearch.getContent() != null) {
            spec = Specification.where(PostSpec.likePostsContent(postSearch.getContent()));
        } else if (postSearch.getAuthor() != null) {
            spec = Specification.where(PostSpec.equalAuthorName(postSearch.getAuthor()));
        }

        return postsRepository.findAll(spec, pageable);
    }
}
