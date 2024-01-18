package com.example.demo.products.controllers;
import com.example.demo.products.entities.Product;
import com.example.demo.products.repositories.ProductRepository;
import com.example.demo.until.Untils;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.security.PublicKey;
import java.util.*;

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public Object[] getAllProducts(@RequestParam(value = "limit",required = false) Integer limit, @RequestParam(name = "skip", required = false) Integer skip, @RequestParam(name = "select", required = false) List<String> select) {
        Iterable<Product> products =  this.productRepository.findAll();
        int total = Untils.getSize(products);
        if (skip != null) products = Untils.sliceArray(products, skip);
        if (limit != null) products = Untils.limitArray(products,limit);
        if (select  != null) products = Untils.getMultiObjectSubset(products, select);
        Object[] arrays = {products, "total: " + total, "skip: " + skip, "limit: " + Untils.getSize(products)};
        return arrays;
    }

    @GetMapping("/products/{id}")
    public Iterable<Product> getProductById(@PathVariable("id") Integer id, @RequestParam(name = "select", required = false) List<String> select) {
        List<Product> productArray = new ArrayList<>();
        for (Product product : this.productRepository.findAll()) {
            if (product.getId().equals(id)) {
                productArray.add(product);
            }
        }
        Iterable<Product> product = productArray;
        if (select != null) product = Untils.getMultiObjectSubset(product, select);
        return product;
    }

    @GetMapping("/products/search")
    public Object[] searchProducts(@RequestParam(value = "limit",required = false) Integer limit, @RequestParam(name = "skip", required = false) Integer skip, @RequestParam(name = "select", required = false) List<String> select, @RequestParam(name = "q",required = false) String searchQuery) {
        List<Product> firstArrayProduct = new ArrayList<>();
        if (searchQuery != null) {
            for (Product product : this.productRepository.findAll()) {
                if (product.getTitle().contains(searchQuery)||product.getDescription().contains(searchQuery)) {
                    firstArrayProduct.add(product);
                }
            }
        }
        Iterable<Product> products = firstArrayProduct;
        int total = Untils.getSize(products);
        if (skip != null) products = Untils.sliceArray(products, skip);
        if (limit != null) products = Untils.limitArray(products,limit);
        if (select  != null) products = Untils.getMultiObjectSubset(products, select);
        Object[] arrays = {products, "total: " + total, "skip: " + skip, "limit: " + Untils.getSize(products)};
        return arrays;
    }

    @GetMapping("/products/categories/{category}")
    public Object[] getProductsByCategoryName(@RequestParam(value = "limit",required = false) Integer limit, @RequestParam(name = "skip", required = false) Integer skip, @RequestParam(name = "select", required = false) List<String> select, @PathVariable("category") String category) {
        List<Product> firstArrayProduct = new ArrayList<>();
        if (category !=null) {
            for (Product product : this.productRepository.findAll()) {
                if (product.getCategory().contains(category)) {
                    firstArrayProduct.add(product);
                }
            }
        }
        Iterable<Product> products = firstArrayProduct;
        int total = Untils.getSize(products);
        if (skip != null) products = Untils.sliceArray(products, skip);
        if (limit != null) products = Untils.limitArray(products,limit);
        if (select  != null) products = Untils.getMultiObjectSubset(products, select);
        Object[] arrays = {products, "total: " + total, "skip: " + skip, "limit: " + Untils.getSize(products)};
        return  arrays;
    }

    @PostMapping(value = "/products/add", consumes = "application/json")
    public ResponseEntity<Object> addProducts(@RequestBody List<Product> productList) {
        if (productList != null && !productList.isEmpty()) {
            for (Product product : productList) {
                this.productRepository.save(product);
            }
            return ResponseEntity.status(HttpStatus.OK).body("Request data processed successfully");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request data type");
        }
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable("id") Integer id, @RequestBody Product p) {
        Optional<Product> productToUpdateOptional = this.productRepository.findById(id);
        if(!productToUpdateOptional.isPresent()) return null;
        Product productToUpdate = productToUpdateOptional.get();
        if(p.getTitle() != null) productToUpdate.setTitle(p.getTitle());
        if(p.getDescription() != null) productToUpdate.setDescription(p.getDescription());
        if(p.getPrice() != null) productToUpdate.setPrice(p.getPrice());
        if(p.getDiscountpercentage() != null) productToUpdate.setDiscountpercentage(p.getDiscountpercentage());
        if(p.getRating() != null) productToUpdate.setPrice(p.getPrice());
        if (p.getStock() != null) productToUpdate.setStock(p.getStock());
        if (p.getBrand() != null) productToUpdate.setBrand(p.getBrand());
        if (p.getCategory() != null) productToUpdate.setCategory(p.getCategory());
        if (p.getThumbnail() != null) productToUpdate.setThumbnail(p.getThumbnail());
        if (p.getImages() != null) productToUpdate.setImages(p.getImages());
        Product updatedProduct = this.productRepository.save(productToUpdate);
        return updatedProduct;
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable("id") Integer id){
        Optional<Product> productToDeleteOptional = this.productRepository.findById(id);
        if (!productToDeleteOptional.isPresent()) return null;
        Product productToDelete = productToDeleteOptional.get();
        this.productRepository.delete(productToDelete);
        return productToDelete;
    }

}
