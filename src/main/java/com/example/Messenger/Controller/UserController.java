package com.example.Messenger.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Messenger.DTO.RequestDTO;
import com.example.Messenger.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    
    @Autowired
    UserService userService;

    @PostMapping("/add_comment")
    public String add_comment(@RequestBody RequestDTO requestDTO){
        return userService.add_comment(requestDTO);
    }

    @GetMapping("/get_comment")
    public List<String> get_comment(@RequestParam("q") String comment_to){
        return userService.get_comment(comment_to);
    }
}
