package com.jobayed.bloggingapp.api;

import com.jobayed.bloggingapp.domain.Role;
import com.jobayed.bloggingapp.domain.User;
import com.jobayed.bloggingapp.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController @RequiredArgsConstructor
public class UserResource {
    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getusers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/user/store")
    public ResponseEntity<User> storeUser(@RequestBody User user){
        return ResponseEntity.ok(userService.saveUser(user));
    }
    @PostMapping("/role/store")
    public ResponseEntity<Role> storeRole(@RequestBody Role role){
        return ResponseEntity.ok(userService.saveRole(role));
    }
    @PostMapping("/user/assign-role")
    public ResponseEntity<?> assignRole(@RequestBody RoleToUserForm form){
        userService.assignRole(form.getUsername(),form.getRolename());
        return ResponseEntity.ok().build();
    }
    @Data
    class RoleToUserForm{
        private String username;
        private String rolename;
    }
}
