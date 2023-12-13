package com.zwanan.controller;

import com.zwanan.entity.User;
import com.zwanan.mapper.UserMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/hello")
public class HelloController {

    private final UserMapper userMapper;

    public HelloController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

//    @GetMapping("/{hello}")
//    public Model hello(@PathVariable("hello") String hello, Model model) {
//        model.addAttribute("hello", hello);
//        return model;
//    }

    @GetMapping()
    public String hello(Model model) {
        List<User> allUser = userMapper.getAllUser();
        model.addAttribute("users", allUser);
        return "hello";
    }

    @GetMapping(value = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<User> json() {
        return userMapper.getAllUser();
    }
}
