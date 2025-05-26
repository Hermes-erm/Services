package com.service.product;

import com.service.product.model.ProductModel;
import com.service.product.model.ProductRepo;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

@SpringBootApplication
public class ProductApplication {

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }

    @PostConstruct
    public void doSome() {
        MappingMongoConverter mappingMongoConverter = applicationContext.getBean(MappingMongoConverter.class);
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
        // this.productRepo.save(new ProductModel("Shoe", "nothing to say about it..!",
        // 4.5f));
    }
}
