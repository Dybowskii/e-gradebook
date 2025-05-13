package com.e_journal.backend.User;

import com.e_journal.backend.User.dtos.UserRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping(path = "/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User CurrentUser = (User) auth.getPrincipal();
        return ResponseEntity.ok(CurrentUser);
    }


    @PostMapping
    public ResponseEntity<User> newUser(@Valid @RequestBody UserRequestDTO userRequest){
        return new ResponseEntity<>(userService.createUser(userRequest), HttpStatus.CREATED);
    }
}
