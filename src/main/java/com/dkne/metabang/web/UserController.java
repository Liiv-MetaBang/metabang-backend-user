package com.dkne.metabang.web;

import com.dkne.metabang.web.dto.UserCreateRequestDto;
import com.dkne.metabang.web.dto.UserResponseDto;
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

    // create user
    @PostMapping("/user")
    public int create(@RequestBody UserCreateRequestDto requestDto) {
        return userService.create(requestDto);
    }

    // show user info
    @GetMapping("/user/{user_id}")
    public UserResponseDto showUser(@PathVariable int user_id) {
        return userService.findById(user_id);
    }

    // update user info
    @PostMapping("/user/{user_id}")
    public int updateUser(@PathVariable  int user_id, @RequestBody UserUpdateRequestDto requestDto){
        return userService.update(user_id , requestDto);
    }

    //delecte user
    @DeleteMapping("/user/{user_id}")
    public int deleteUser(@PathVariable int user_id) {
        userService.delete(user_id);
        return user_id;
    }
}
