package com.example.crud.service;

import com.example.crud.domain.User;
import com.example.crud.dto.UserRequestDto;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void join(){
        // given
        UserRequestDto requestedUserA = UserRequestDto
                .builder()
                .username("testA")
                .password("test")
                .build();

        // when
        Long joinUserId = userService.join(requestedUserA);

        // then
        assertThat(joinUserId).isEqualTo(userService.findByUsername("testA").getId());
    }

    @Test
    public void findByUserName(){
        // given
        UserRequestDto requestedUserA = UserRequestDto
                .builder()
                .username("testA")
                .password("test")
                .build();
        Long joinUserId = userService.join(requestedUserA);

        // when
        User findUser = userService.findByUsername("testA");

        // then
        assertThat(findUser.getId()).isEqualTo(joinUserId);
    }


}