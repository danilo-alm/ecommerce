package com.danilo.ecommerce.controller;

import com.danilo.ecommerce.domain.user.User;
import com.danilo.ecommerce.dto.UserRequestDTO;
import com.danilo.ecommerce.dto.UserResponseDTO;
import com.danilo.ecommerce.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.net.URI;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponseDTO> getUser(
        @RequestParam(required = false) BigInteger id,
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String email,
        @RequestParam(required = false) String phoneNumber
    ) {
        if (id != null) {
            return ResponseEntity.ok(userService.getById(id));
        }
        if (username != null) {
            return ResponseEntity.ok(userService.getByUsername(username));
        }
        if (email != null) {
            return ResponseEntity.ok(userService.getByEmail(email));
        }
        if (phoneNumber != null) {
            return ResponseEntity.ok(userService.getByPhoneNumber(phoneNumber));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserRequestDTO userDTO) {
        User user = userService.createUser(userDTO);
        URI loc = URI.create("/user/" + user.getId());
        return ResponseEntity.created(loc).body(user);
    }

}
