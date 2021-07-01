package com.example.crud.web;

import com.example.crud.dto.CommentsResponseDto;
import com.example.crud.dto.PostsListResponseDto;
import com.example.crud.dto.PostsResponseDto;
import com.example.crud.service.CommentsService;
import com.example.crud.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final PostsService postsService;
    private final CommentsService commentsService;

    @GetMapping("/")
    public String index(Model model){
        List<PostsListResponseDto> all = postsService.findAllDes();
        model.addAttribute("posts",all);
        return "index";
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable("id") Long id, Model model){
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
