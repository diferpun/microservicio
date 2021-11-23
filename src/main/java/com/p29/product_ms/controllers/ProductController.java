package com.p29.product_ms.controllers;

import com.p29.product_ms.exceptions.ProductNotFoundException;
import com.p29.product_ms.models.Product;
import com.p29.product_ms.repositories.ProductRepository;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @PostMapping("/product")
    Product newProduct(@RequestBody Product product){

        Product uniqueProduct = productRepository.findById(product.getId()).orElse(null);
        if (uniqueProduct == null) {
            return productRepository.save(product);

        }
        else{
            throw  new ProductNotFoundException( "Existe ya un producto con el ID: " + uniqueProduct.getId());
        }


    }

    @GetMapping("/getProduct/{id}")
    Product getProduct(@PathVariable String id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("No se encontró un producto con el id: " + id));
    }

    @GetMapping("/products")
    List<Product> getAllProduct(@RequestParam("id") Optional<String> id) {
        if (id.isPresent()) {
            return productRepository.findByRegexId(id.get());
        } else {
            return productRepository.findAll();
        }
    }

    @PutMapping("/updateProduct/{id}")
    Product changeProduct(@PathVariable String id, @RequestBody Product product){
        Product uniqueProduct = productRepository.findById(id).orElse(null);
        if (uniqueProduct == null) {
            throw new ProductNotFoundException( "No hay producto con: " + id);
        }
        if (uniqueProduct.getProductName() != product.getProductName()) {
            uniqueProduct.setProductName(product.getProductName());
        }
        if (uniqueProduct.getDescription() != product.getDescription()) {
            uniqueProduct.setDescription(product.getDescription());
        }
        if (uniqueProduct.getBasePrice() != product.getBasePrice()) {
            uniqueProduct.setBasePrice(product.getBasePrice());
        }
        if (uniqueProduct.getCategory() != product.getCategory()) {
            uniqueProduct.setCategory(product.getCategory());
        }
        if (product.getId() != null) {
            if (!product.getId().isEmpty() && (uniqueProduct.getId() != product.getId())) {
                throw new ProductNotFoundException("No se puede modificar el ID a un producto");
            }
        }

        productRepository.save(uniqueProduct);
        return uniqueProduct;
    }

    @DeleteMapping("/deleteProduct/{id}")
    Product eraseProduct(@PathVariable String id) {
        Product uniqueProduct = productRepository.findById(id).orElse(null);
        if (uniqueProduct == null) {
            throw new ProductNotFoundException("No hay producto en la base de datos " + id);
        }

        productRepository.delete(uniqueProduct);
        return uniqueProduct;
    }


}
