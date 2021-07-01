package com.example.crud.dto;

import com.example.crud.domain.Comments;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentsRequestDto {
    private String content;

    @Builder
    public CommentsRequestDto(String content){
        this.content = content;
    }

    public Comments toEntity(){
        return Comments
                .builder()
                .content(this.content)
                .build();
    }
}
