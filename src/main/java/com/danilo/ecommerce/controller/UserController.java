package com.danilo.ecommerce.controller;

import com.danilo.ecommerce.domain.user.User;
import com.danilo.ecommerce.dto.UserDTO;
import com.danilo.ecommerce.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);
        URI loc = URI.create("/user/" + user.getId());
        return ResponseEntity.created(loc).body(user);
    }

}
