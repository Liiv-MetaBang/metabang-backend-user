package com.dkne.metabang.web.dto;

import com.dkne.metabang.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    //private int user_id;
    private String user_name;
    private String account;
    private String email;
    private int role;

    public UserResponseDto(User entity) {
        this.user_name = entity.getUser_name();
        this.account = entity.getAccount();
        this.email = entity.getEmail();
        this.role = entity.getRole();
    }

}
