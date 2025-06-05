package com.service.cart;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

@SpringBootApplication
public class CartApplication {

    @Autowired
    public ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }

    @PostConstruct
    public void configDefClass() {
        MappingMongoConverter mongoConverter = context.getBean(MappingMongoConverter.class);
        mongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }
}
