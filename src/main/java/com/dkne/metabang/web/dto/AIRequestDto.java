package com.dkne.metabang.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AIRequestDto {
    int gender;
    int age;
    int reason;

    @Builder
    public AIRequestDto(int gender, int age, int reason) {
        this.gender = gender;
        this.age = age;
        this.reason = reason;
    }
}
