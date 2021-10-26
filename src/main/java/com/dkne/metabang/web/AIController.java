package com.dkne.metabang.web;


import com.dkne.metabang.service.AIService;
import com.dkne.metabang.web.dto.AIRequestDto;
import com.dkne.metabang.web.dto.UserCreateRequestDto;
import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("v1")
public class AIController {

    private final AIService aiservice;

    @PostMapping("/recommand")
    public String recommandHouse(@RequestBody AIRequestDto requestDto) throws IOException {
        return aiservice.recommand(requestDto);
    }
}
