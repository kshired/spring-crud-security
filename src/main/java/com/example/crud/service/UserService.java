package com.example.crud.service;

import com.example.crud.config.MyUserDetails;
import com.example.crud.domain.User;
import com.example.crud.dto.UserRequestDto;
import com.example.crud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public Long join(UserRequestDto requestDto){
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());
        requestDto.setPassword(encodedPassword);

        User user = requestDto.toEntity();
        return userRepository.save(user).getId();
    }

    public User findByUsername(String username) throws UsernameNotFoundException{
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("없음"));
    }

    public User findById(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("없어요 유저가"));
        return new MyUserDetails(user);
    }

    public void changePassword(String username, String newPassword) {
        User findUser = this.findByUsername(username);
        String encodedPassword = passwordEncoder.encode(newPassword);
        findUser.changePassword(encodedPassword);
    }
}
