package com.service.user_management.Controller;

import com.service.user_management.DTO.APIResponse;
import com.service.user_management.DTO.UserDTO;
import com.service.user_management.Repository.UserModel;
import com.service.user_management.Service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<APIResponse> newUser(@RequestBody UserModel user) {
        return ResponseEntity.ok(new APIResponse("user registered successfully!", this.userService.addNewUser(user)));
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody UserDTO user, HttpServletResponse response) {
        return this.userService.login(user, response);
    }

    @GetMapping("/logout")
    public ResponseEntity<APIResponse> logout(HttpServletResponse response) {
        return ResponseEntity.ok(new APIResponse("logged-out successfully..", this.userService.logout(response)));
    }
}
