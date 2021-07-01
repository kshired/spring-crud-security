package com.example.crud.dto;

import com.example.crud.domain.Posts;
import com.example.crud.domain.User;
import lombok.Getter;

import java.beans.ConstructorProperties;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String author;
    private String modifiedDate;

    @ConstructorProperties({"@class","id","title","author","modifiedDate"})
    public PostsListResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getUser().getUsername();
        // 방금전 < 1 , 1<= N분전< 60, 60 <= 1시간 < 120 .....  < 1440,
        this.modifiedDate = transferTime(entity.getLastModifiedDate());
    }

    private String transferTime(LocalDateTime time){
        final int SEC = 60;
        final int MIN = 60;
        final int HOUR = 24;
        final int DAY = 30;
        final int MONTH = 12;

        String msg = null;

        LocalDateTime now = LocalDateTime.now();
        long diffTime = ChronoUnit.SECONDS.between(time, now);

        if (diffTime < SEC){
            msg = diffTime + "초전";
        } else if ((diffTime /= SEC) < MIN) {
            msg = diffTime + "분 전";
        } else if ((diffTime /= MIN) < HOUR) {
            msg = (diffTime) + "시간 전";
        } else if ((diffTime /= HOUR) < DAY) {
            msg = (diffTime) + "일 전";
        } else if ((diffTime /= DAY) < MONTH) {
            msg = (diffTime) + "개월 전";
        } else{
            long year = ChronoUnit.YEARS.between(time,now);
            msg = year + "년 전";
        }

        return msg;

    }
}
