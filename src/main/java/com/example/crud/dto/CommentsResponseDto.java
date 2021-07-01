package com.example.crud.dto;

import com.example.crud.domain.Comments;
import lombok.Getter;

@Getter
public class CommentsResponseDto {
    private Long id;
    private String content;
    private String author;

    public CommentsResponseDto(Comments comments){
        this.id = comments.getId();
        this.content = comments.getContent();
        this.author = comments.getUser().getUsername();
    }
}
