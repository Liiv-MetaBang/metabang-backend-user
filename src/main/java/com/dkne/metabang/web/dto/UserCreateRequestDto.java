package com.dkne.metabang.web.dto;

import com.dkne.metabang.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreateRequestDto {
    private String password;
    private String user_name;
    private String account;
    private String email;
    private int role;

    @Builder
    public UserCreateRequestDto(String user_name, String password, String account, String email, int role) {
        this.user_name = user_name;
        this.password = password;
        this.account = account;
        this.email = email;
        this.role = role;
    }

    public User toEntity() {
        return User.builder()
                    .user_name(user_name)
                    .password(password)
                    .account(account)
                    .email(email)
                    .role(role)
                    .build();
    }
}
