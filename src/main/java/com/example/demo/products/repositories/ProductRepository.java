package com.example.demo.products.repositories;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.example.demo.products.entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
    List<Product> findByTitle(String searchQuery);
    List<Product> findByDescription(String searchQuery);
    List<Product> findByCategory(String category);
}
