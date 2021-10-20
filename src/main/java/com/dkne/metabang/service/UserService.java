package com.dkne.metabang.service;

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
}
