package com.service.user_management.Controller;

import com.service.user_management.DTO.APIResponse;
import com.service.user_management.Repository.UserModel;
import com.service.user_management.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<APIResponse> newUser(@RequestBody UserModel user) {
        return ResponseEntity.ok(new APIResponse("user registered successfully!", this.userService.addNewUser(user)));
    }
}
