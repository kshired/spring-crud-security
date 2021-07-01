package com.example.crud.dto;

import com.example.crud.domain.Comments;
import lombok.Getter;

@Getter
public class CommentsResponseDto {
    private String content;
    private String author;

    public CommentsResponseDto(Comments comments){
        this.content = comments.getContent();
        this.author = comments.getUser().getUsername();
    }
}
