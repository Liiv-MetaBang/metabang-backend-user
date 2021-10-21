package com.dkne.metabang.service;

import com.dkne.metabang.web.dto.UserCreateRequestDto;
import com.dkne.metabang.web.dto.UserResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dkne.metabang.domain.user.User;
import com.dkne.metabang.domain.user.UserRepository;
import com.dkne.metabang.web.dto.UserUpdateRequestDto;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	
	@Transactional
    public int update(int user_id, UserUpdateRequestDto requestDto) {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + user_id));

        user.update(requestDto.getEmail(), requestDto.getAccount());
        return user_id;
    }

    @Transactional(readOnly = true)
    public UserResponseDto findById(int id) {
        User entity = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new UserResponseDto(entity);
    }

    @Transactional
    public void delete (int id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));
        userRepository.delete(user);
    }

    public int create(UserCreateRequestDto requestDto) {
        return userRepository.save(requestDto.toEntity()).getUser_id();
    }
}
