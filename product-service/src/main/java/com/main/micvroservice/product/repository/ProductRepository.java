package com.main.micvroservice.product.repository;


import com.main.micvroservice.product.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
