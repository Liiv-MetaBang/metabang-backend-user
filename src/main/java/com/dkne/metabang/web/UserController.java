package com.dkne.metabang.web;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dkne.metabang.service.UserService;
import com.dkne.metabang.web.dto.UserUpdateRequestDto;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
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
