package com.example.demo.products.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="TITLE")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String title;

    @Column(name="DESCRIPTION")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    @Column(name = "PRICE")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer price;

    @Column(name = "DISCOUNTPERCENTAGE")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double discountpercentage;

    @Column(name = "RATING")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double rating;

    @Column(name = "STOCK")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer stock;

    @Column(name = "BRAND")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String brand;

    @Column(name = "CATEGORY")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String category;

    @Column(name = "THUMBNAIL")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String thumbnail;

    @Column(name = "IMAGES")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> images;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getDiscountpercentage() {
        return discountpercentage;
    }

    public void setDiscountpercentage(Double discountpercentage) {
        this.discountpercentage = discountpercentage;
    }

    public Product() {

    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}
