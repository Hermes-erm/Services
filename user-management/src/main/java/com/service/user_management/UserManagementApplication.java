package com.service.user_management;

import com.service.user_management.DTO.UserDTO;
import com.service.user_management.Service.JWTService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

@SpringBootApplication
public class UserManagementApplication {
    @Autowired
    private ApplicationContext context;

    @Autowired
    private JWTService jwtService;

    public static void main(String[] args) {
        SpringApplication.run(UserManagementApplication.class, args);
    }

    @PostConstruct
    private void configDBClass() {
        MappingMongoConverter converter = context.getBean(MappingMongoConverter.class);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        System.out.println("here : " + this.jwtService.generateToken(new UserDTO()));
    }
}
