package com.service.user_management.Service;

import com.service.user_management.Repository.UserModel;
import com.service.user_management.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
//@Data
public class UserService {
    @Autowired
    private UserRepository repository;

    private final MongoTemplate mongoTemplate;

    UserService(MongoTemplate mongoTemplate) { // or u can use lombok, with ArgsConstructor!
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

}
