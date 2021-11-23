package com.p29.product_ms.models;
import lombok.Data;
import org.springframework.data.annotation.Id;

public class Product {
    @Id
    private String id;
    private String productName;
    private String description;
    private Float basePrice;
    private String category;

    public Product(String id, String productName, String description, Float basePrice, String category){
        this.id          = id;
        this.productName = productName;
        this.description = description;
        this.basePrice   = basePrice;
        this.category    = category;
    }

    public String getId() {
        return id;
    }

    public String getProductName(){
        return productName;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public Float getBasePrice(){
        return basePrice;
    }
    public void setBasePrice(Float basePrice){
        this.basePrice = basePrice;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String category){
        this.category = category;
    }


}
