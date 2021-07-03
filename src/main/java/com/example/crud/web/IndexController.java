package com.example.crud.web;

import com.example.crud.domain.Posts;
import com.example.crud.dto.CommentsResponseDto;
import com.example.crud.dto.PostsListResponseDto;
import com.example.crud.dto.PostsResponseDto;
import com.example.crud.service.CommentsService;
import com.example.crud.service.PostsService;
import com.example.crud.util.Paging;
import com.example.crud.util.PostSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final PostsService postsService;
    private final CommentsService commentsService;

    @GetMapping("/")
    public String index(@ModelAttribute("postSearch") PostSearch postSearch, Model model, Pageable pageable){
        Page<Posts> all = postsService.findBySearch(postSearch, pageable);
        List<PostsListResponseDto> posts = all.stream().map(PostsListResponseDto::new).collect(Collectors.toList());
        Paging paging = new Paging(all.getTotalPages(), all.getNumber(), all.isFirst(), all.isLast());

        model.addAttribute("posts",posts);
        model.addAttribute("paging",paging);
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable("id") Long id, Model model){
        PostsResponseDto dto = new PostsResponseDto(postsService.findById(id));
        model.addAttribute("post",dto);

        return "posts-update";
    }

    @GetMapping("/posts/{id}")
    public String postsDetail(@PathVariable("id") Long id, Model model){
        PostsResponseDto dto = new PostsResponseDto(postsService.findById(id));
        List<CommentsResponseDto> comments = commentsService.findCommentsByPostId(id);
        model.addAttribute("post",dto);
        model.addAttribute("comments",comments);

        return "posts-detail";
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }


}
