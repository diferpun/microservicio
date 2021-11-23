package com.p29.product_ms.repositories;

import com.p29.product_ms.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository <Product, String>  {
    @Query("{'id': {'$regex': ?0}}")
    List<Product> findByRegexId(String regexp);

}

