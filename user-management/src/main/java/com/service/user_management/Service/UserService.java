package com.service.user_management.Service;

import com.service.user_management.DTO.APIResponse;
import com.service.user_management.DTO.UserDTO;
import com.service.user_management.Repository.UserModel;
import com.service.user_management.Repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//@Data
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private JWTService jwtService;

    private final MongoTemplate mongoTemplate;

    UserService(MongoTemplate mongoTemplate) { // or u can use lombok, with ArgsConstructor!
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private ResponseEntity<APIResponse> response(HttpStatus status, String message, Object data) {
//        return new APIResponse(message, data);
        return ResponseEntity.status(status).body(new APIResponse(message, data));
    }

    private String encodePassword(String userPassword) {
        return this.passwordEncoder.encode(userPassword);
    }

    private boolean verifyPassword(String userPass, String encodedPass) {
        return this.passwordEncoder.matches(userPass, encodedPass);
    }

    public UserModel addNewUser(UserModel user) {
        List<UserModel> existUsers = this.repository.findByName(user.getName());
        if (!existUsers.isEmpty()) {
            existUsers.getFirst().setPassword(null);
            return existUsers.getFirst(); // user exists!
        }
        user.setPassword(this.encodePassword(user.getPassword()));
        UserModel updatedUser = this.repository.save(user);
        updatedUser.setPassword(null);
        return null; // no user exists!
    }

    public ResponseEntity<APIResponse> login(UserDTO user, HttpServletResponse response) {
        List<UserModel> existUsers = this.repository.findByName(user.getName());
        if (existUsers.isEmpty())
            return this.response(HttpStatus.NOT_FOUND, "No user found!", null);
        boolean isPassMatch = this.verifyPassword(user.getPassword(), existUsers.getFirst().getPassword());
        if (isPassMatch) {
            Cookie cookie = new Cookie("_token", this.jwtService.generateToken(user));
            cookie.setHttpOnly(true);
//            cookie.setPath("/");
            response.addCookie(cookie);
            return this.response(HttpStatus.ACCEPTED, "User authenticated!", user);
        }
        return this.response(HttpStatus.BAD_REQUEST, "Password incorrect!", null);
    }
}
