package com.dkne.metabang.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dkne.metabang.service.UserService;
import com.dkne.metabang.web.dto.UserUpdateRequestDto;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("v1")
public class UserController {

	private final UserService userService;
	
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    
    @PostMapping("/user/{user_id}")
    public int updateUser(@PathVariable  int user_id, @RequestBody UserUpdateRequestDto requestDto){
        return userService.update(user_id , requestDto);
    }
}
