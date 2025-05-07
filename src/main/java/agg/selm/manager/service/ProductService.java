package agg.selm.manager.service;

import agg.selm.manager.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProducts();

    void createProduct(String name, String details);

    Optional<Product> findProduct(int productId);
}
