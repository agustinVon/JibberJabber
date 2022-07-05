package com.agustinvon.JibberJabber.controller;

import com.agustinvon.JibberJabber.model.dto.UserDTO;
import com.agustinvon.JibberJabber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserDTO getCurrentUser(Principal principal) {
        return userService.getUserInformation(principal.getName());
    }

    @GetMapping("/{userId}")
    public UserDTO getUser(@PathVariable String userId) {
        return userService.getUserInformation(userId);
    }
}
