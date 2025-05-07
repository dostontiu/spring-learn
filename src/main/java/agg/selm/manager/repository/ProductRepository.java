package agg.selm.manager.repository;

import agg.selm.manager.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    public List<Product> findAll();

    public Product save(Product product);

    Optional<Product> findById(int productId);
}
